package cl.felipecastillof.qrreader.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import cl.felipecastillof.qrreader.MainApplication
import cl.felipecastillof.qrreader.R
import cl.felipecastillof.qrreader.databinding.FragmentLoadingBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoadingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoadingDialogFragment : DialogFragment() {


    public var _binding: FragmentLoadingBinding?=null
    public val binding get()=_binding!!

    companion object {
//         lateinit var resultados:ResultadosModel
    }

    // Use this instance of the interface to deliver action events
    internal lateinit var listener: LoadingDialogListener

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    interface LoadingDialogListener {
//        var resultados:ResultadosModel
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        //listener=context as LoadingDialogListener
        //listener=parentFragment as LoadingDialogListener
    }

    fun holi(mensaje:String){
        val builder = AlertDialog.Builder(MainApplication.applicationContext())
        builder.setMessage(mensaje)


    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Build the dialog and set up the button click handlers
            val builder = AlertDialog.Builder(it)

            //builder.setMessage("R.string.dialog_fire_missiles")




            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.fragment_loading, null))


//                .setPositiveButton(R.string.fire
//                ) { dialog, id ->
//                    // Send the positive button event back to the host activity
//                    listener.onDialogPositiveClick(this)
//                }
//                .setNegativeButton(R.string.cancel
//                ) { dialog, id ->
//                    // Send the negative button event back to the host activity
//                    listener.onDialogNegativeClick(this)
//                }
            builder.setCancelable(false)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }





}