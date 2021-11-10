package cl.felipecastillof.qrreader.data

import cl.felipecastillof.qrreader.MainApplication
import cl.felipecastillof.qrreader.MainApplication.Companion.applicationContext
import cl.felipecastillof.qrreader.common.Const
import cl.felipecastillof.qrreader.common.Utils
import cl.felipecastillof.qrreader.data.model.*
import cl.felipecastillof.qrreader.data.network.ValidarCodigoService
import java.io.File
import java.util.*


class ValidarRepository() {

    private val api = ValidarCodigoService()
    var app = applicationContext() as MainApplication


    suspend fun validacionCodigo(codigoAValidar: String): Boolean {
        val response = api.validarCodigo(codigoAValidar)
        if (response != null) {
            var idSolicitud:String=codigoAValidar.substring(0,15)
            Utils.escribeConf(Const.PREF_CODIGO_A_VALIDAR, idSolicitud)
            Utils.escribeConf(Const.PREF_RESULTADO_VALIDACION, response.data.encontrada.toString())
            return ValidacionProvider.getValidacionResultado()
        }
        return false
    }

    suspend fun getCodigoSolicitudFromSp(): CodigoSolicitudModel? {
        var codigoAValidar: String = Utils.leeConf(Const.PREF_CODIGO_A_VALIDAR, "")
        var encontrada: Boolean = if (Utils.escribeConf(Const.PREF_RESULTADO_VALIDACION, "false")
                .equals("false")
        ) false else true

        return CodigoSolicitudModel(codigoAValidar, encontrada)

    }


    suspend fun getParametros(idSolicitud: String): ParametrosVerificacionModel? {
        val response = api.getParametrosVerificacion(idSolicitud)
        if (response != null) {
            Utils.escribeConf(Const.PREF_VERIF_RUT, response.data.rut)
            Utils.escribeConf(Const.PREF_VERIF_NOMBRE, response.data.nombre)
            Utils.escribeConf(Const.PREF_VERIF_APELLIDOS, response.data.apellidos)
            Utils.escribeConf(Const.PREF_VERIF_EMAIL, response.data.email)
            Utils.escribeConf(Const.PREF_VERIF_CELULAR, response.data.celular)
            Utils.escribeConf(Const.PREF_VERIF_DIRECCION, response.data.direccion)
            Utils.escribeConf(Const.PREF_VERIF_LAT, response.data.lat.toString())
            Utils.escribeConf(Const.PREF_VERIF_LNG, response.data.lng.toString())

            Utils.escribeConf(Const.PREF_VERIF_ESTADO, response.data.estado.toString())
            Utils.escribeConf(Const.PREF_VERIF_IDSOLICITUD, response.data.idSolicitud)
            Utils.escribeConf(
                Const.PREF_VERIF_FECHACREACION,
                response.data.fechaCreacion.toString()
            )

            Utils.escribeConf(Const.PREF_VERIF_FOTOCALLE, response.data.fotoCalle.toString())
            Utils.escribeConf(
                Const.PREF_VERIF_FOTOCONDOMINIO,
                response.data.fotoCondominio.toString()
            )
            Utils.escribeConf(Const.PREF_VERIF_FOTOENTRADA, response.data.fotoEntrada.toString())
            Utils.escribeConf(Const.PREF_VERIF_FOTOINTERIOR, response.data.fotoInterior.toString())
            Utils.escribeConf(
                Const.PREF_VERIF_FOTOPANORAMICA,
                response.data.fotoPanoramica.toString()
            )
            Utils.escribeConf(Const.PREF_VERIF_FOTOCUENTA1, response.data.fotoCuenta1.toString())
            Utils.escribeConf(Const.PREF_VERIF_FOTOCUENTA2, response.data.fotoCuenta2.toString())
            Utils.escribeConf(
                Const.PREF_VERIF_FOTOPERSONALIZADA,
                response.data.fotoPersonalizada.toString()
            )
            Utils.escribeConf(
                Const.PREF_VERIF_FOTOPERSONALIZADAGLOSA,
                response.data.fotoPersonalizadaGlosa
            )
            Utils.escribeConf(Const.PREF_VERIF_TOLERANCIA, response.data.tolerancia.toString())
            Utils.escribeConf(Const.PREF_VERIF_TIPOVERIFICACION, response.data.tipoVerificacion)
//


        }
        return ParametrosVerificacionModel(
            Utils.leeConf(Const.PREF_VERIF_RUT, ""),
            Utils.leeConf(Const.PREF_VERIF_NOMBRE, ""),
            Utils.leeConf(Const.PREF_VERIF_APELLIDOS, ""),
            Utils.leeConf(Const.PREF_VERIF_EMAIL, ""),
            Utils.leeConf(Const.PREF_VERIF_CELULAR, ""),
            Utils.leeConf(Const.PREF_VERIF_DIRECCION, ""),
            Utils.leeConf(Const.PREF_VERIF_LAT, "0").toDouble(),
            Utils.leeConf(Const.PREF_VERIF_LNG, "0").toDouble(),

            Integer.parseInt(Utils.leeConf(Const.PREF_VERIF_ESTADO, "0")),
            Utils.leeConf(Const.PREF_VERIF_IDSOLICITUD, ""),
            "",
            Date(),

            Integer.parseInt(Utils.leeConf(Const.PREF_VERIF_FOTOCALLE, "0")),
            Integer.parseInt(Utils.leeConf(Const.PREF_VERIF_FOTOCONDOMINIO, "0")),
            Integer.parseInt(Utils.leeConf(Const.PREF_VERIF_FOTOENTRADA, "0")),
            Integer.parseInt(Utils.leeConf(Const.PREF_VERIF_FOTOINTERIOR, "0")),
            Integer.parseInt(Utils.leeConf(Const.PREF_VERIF_FOTOPANORAMICA, "0")),
            Integer.parseInt(Utils.leeConf(Const.PREF_VERIF_FOTOCUENTA1, "0")),
            Integer.parseInt(Utils.leeConf(Const.PREF_VERIF_FOTOCUENTA2, "0")),
            Integer.parseInt(Utils.leeConf(Const.PREF_VERIF_FOTOPERSONALIZADA, "0")),
            Utils.leeConf(Const.PREF_VERIF_FOTOPERSONALIZADAGLOSA, ""),
            Integer.parseInt(Utils.leeConf(Const.PREF_VERIF_TOLERANCIA, "0")),
            Utils.leeConf(Const.PREF_VERIF_TIPOVERIFICACION, "")
        )
    }

    suspend fun enviarResultados(dtoResultado:DTOResultados): DTOResultadosResponse? {
        val response=api.enviarResultados(dtoResultado)
        if(response!=null) {
            return response!!
        }
        return null
    }

    suspend fun enviarFoto(foto:File,nombreArchivo:String): DTOResultadosResponse? {
        val response=api.enviarFoto(foto,nombreArchivo)
        if(response!=null) {
            return response!!
        }
        return null
    }

}