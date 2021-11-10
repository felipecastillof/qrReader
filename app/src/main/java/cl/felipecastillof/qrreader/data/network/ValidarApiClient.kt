package cl.felipecastillof.qrreader.data.network

import cl.felipecastillof.qrreader.data.model.DTOParametrosVerificacion
import cl.felipecastillof.qrreader.data.model.DTOResultados
import cl.felipecastillof.qrreader.data.model.DTOResultadosResponse
import cl.felipecastillof.qrreader.data.model.DTOValidarCodigo
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*


interface ValidarApiClient {

    @GET("/verificacion/validarcodigo/{codigo}")
    suspend fun validarCodigo(@Path("codigo") codigoAValidar:String ): Response<DTOValidarCodigo>

    @GET("/solicitud/{idSolicitud}")
    suspend fun getParametrosVerificacion(@Path("idSolicitud") codigo:String ): Response<DTOParametrosVerificacion>

    @POST("/verificacion/")
    suspend fun enviarResultados(
        @Body dtoResultados: DTOResultados
    ): Response<DTOResultadosResponse>

    @Multipart
    @POST("/verificacion/fotos/")
    suspend fun enviarFoto(
//        @Part("idsolicitud") idSolicitud: RequestBody?
//        ,@Part("file\"; filename=\"fotocalle.jpg\" ") file: RequestBody?
        @Part fotocalle: MultipartBody.Part?

    ): Response<DTOResultadosResponse>




}