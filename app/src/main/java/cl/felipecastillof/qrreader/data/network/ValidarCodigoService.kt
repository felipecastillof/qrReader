package cl.felipecastillof.qrreader.data.network

import android.util.Log
import cl.felipecastillof.qrreader.common.RetrofitHelperServicios
import cl.felipecastillof.qrreader.data.model.DTOParametrosVerificacion
import cl.felipecastillof.qrreader.data.model.DTOValidarCodigo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import cl.felipecastillof.qrreader.data.model.DTOResultados
import cl.felipecastillof.qrreader.data.model.DTOResultadosResponse

import org.json.JSONObject
import java.io.File

import okhttp3.MediaType

import okhttp3.RequestBody

import okhttp3.MultipartBody





class ValidarCodigoService( ) {
    private val retrofit= RetrofitHelperServicios.getRetrofitServicios()


    suspend fun validarCodigo(codigoAValidar:String): DTOValidarCodigo? {
        return withContext(Dispatchers.IO) {
            try {
                val response = retrofit.create(ValidarApiClient::class.java).validarCodigo(codigoAValidar)
                if(response.isSuccessful())
                    response.body()
                else null
            } catch (e: Exception){
                null
            }
        }
    }


    suspend fun getParametrosVerificacion(idSolicitud:String): DTOParametrosVerificacion? {
        return withContext(Dispatchers.IO) {
            try {
                val response = retrofit.create(ValidarApiClient::class.java).getParametrosVerificacion(idSolicitud)
                if(response.isSuccessful())
                    response.body()
                else {
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        Log.d("ppl",jObjError.getJSONObject("error").getString("message"))
                    } catch (e: Exception) {
                        Log.d("ppl",e.message!!)
                    }
                }
            } catch (e: Exception){
                Log.d("ppl",e.message!!)
                null
            } as DTOParametrosVerificacion?
        }
    }


    suspend fun enviarResultados(dtoResultados:DTOResultados): DTOResultadosResponse? {
        return withContext(Dispatchers.IO) {
            try {

                val response = retrofit.create(ValidarApiClient::class.java).enviarResultados(
                    //dtoResultados
                    dtoResultados
                )
                if(response.isSuccessful())
                    response.body()
                else null
            } catch (e: Exception){
                Log.d("ppl",e.message.toString())
                null
            }
        }
    }


    suspend fun enviarFoto(foto:File,nombreArchivo:String): DTOResultadosResponse? {
        return withContext(Dispatchers.IO) {
            try {
                //pass it like this
                //val file = File("/storage/emulated/0/Download/Corrections 6.jpg")
//                val file = File(dtoResultados.fotoCalle)
                val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), foto)

// MultipartBody.Part is used to send also the actual file name

// MultipartBody.Part is used to send also the actual file name
                val body = MultipartBody.Part.createFormData("image", nombreArchivo, requestFile)

// add another part within the multipart request

// add another part within the multipart request
                val fullName =
                    RequestBody.create(MediaType.parse("multipart/form-data"), "Your Name")


                val response = retrofit.create(ValidarApiClient::class.java).enviarFoto(
                    body
                )
                if(response.isSuccessful())
                    response.body()
                else null
            } catch (e: Exception){
                null
            }
        }
    }


}