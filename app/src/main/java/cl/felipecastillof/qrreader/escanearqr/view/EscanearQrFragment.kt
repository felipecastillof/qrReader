package cl.felipecastillof.qrreader.escanearqr.view

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Size
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import cl.felipecastillof.qrreader.MainApplication
import cl.felipecastillof.qrreader.MainViewModel
import cl.felipecastillof.qrreader.databinding.FragmentEscanearQrBinding
import cl.felipecastillof.qrreader.escanearqr.QrCodeAnalyzer
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

class EscanearQrFragment : Fragment() {

    private val mainViewModel : MainViewModel by activityViewModels()
    private val escanearQRViewModel: EscanearQRViewModel by viewModels()

    private var _binding: FragmentEscanearQrBinding?=null
    private val binding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentEscanearQrBinding.inflate(inflater, container, false)
        if (isCameraPermissionGranted()) {
            binding.textureView.post { startCamera() }
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        }

        escanearQRViewModel._respuestaValidacion.observe(viewLifecycleOwner , Observer { respuestaValidacion ->

            Snackbar.make(requireView(), "QR ACCEPTED!. The text inside is " + respuestaValidacion, Snackbar.LENGTH_LONG)
                .setAction("¡Ok!") {

                }
                .show();
        })

        return binding.root
    }

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 10
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EscanearQrFragment().apply {
            }
    }



    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(MainApplication.applicationContext())
        val cameraSelector =
            CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
        val previewConfig = Preview.Builder()
            // We want to show input from back camera of the device
            .setTargetResolution(Size(400,400))
            .build()

        previewConfig.setSurfaceProvider(binding.textureView.getSurfaceProvider())


        val imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .setTargetResolution(Size(400,400))
            // We request aspect ratio but no resolution to match preview config, but letting
            // CameraX optimize for whatever specific resolution best fits requested capture mode
            // Set initial target rotation, we will have to call this again if rotation changes
            // during the lifecycle of this use case
            .build()
        val executor = ContextCompat.getMainExecutor(MainApplication.applicationContext())
        val imageAnalyzer = ImageAnalysis.Builder().build().also {
            it.setAnalyzer(executor, QrCodeAnalyzer { qrCodes ->
                qrCodes?.forEach {
                    escanearQRViewModel.makeWhatYouWantInsideThisMethod(it.rawValue)
                }
            })
        }

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            cameraProvider.unbindAll()
            val camera = cameraProvider.bindToLifecycle(
                this,
                cameraSelector,
                previewConfig,
                imageCapture,
                imageAnalyzer
            )

            //Handle flash
            camera.cameraControl.enableTorch(true)
        }, executor)
    }

    private fun isCameraPermissionGranted(): Boolean {
        val selfPermission =
            ContextCompat.checkSelfPermission(MainApplication.applicationContext(), android.Manifest.permission.CAMERA)
        return selfPermission == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (isCameraPermissionGranted()) {
                binding.textureView.post { startCamera() }
            } else {
                Toast.makeText(MainApplication.applicationContext(), "Camera permission is required.", Toast.LENGTH_SHORT).show()
                //finish()
            }
        }
    }

}
