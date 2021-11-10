package cl.felipecastillof.qrreader.data.model

import android.location.Location
import java.util.*

class ParametrosVerificacionModel {

    var rut:String=""
    var nombre:String=""
    var apellidos:String=""
    var email:String=""
    var celular:String=""
    var direccion:String=""
    var lat:Double=0.0
    var lng:Double= 0.0

    var estado:Int=0
    var idSolicitud:String=""
    var codValidacion:String=""
    var fechaCreacion: Date = Date()

    var fotoCalle:Int=0
    var fotoCondominio:Int=0
    var fotoEntrada:Int=0
    var fotoInterior:Int=0
    var fotoPanoramica:Int=0
    var fotoCuenta1:Int=0
    var fotoCuenta2:Int=0
    var fotoPersonalizada:Int=0
    var fotoPersonalizadaGlosa:String=""
    var tolerancia:Int=0
    var tipoVerificacion:String=""
    
    constructor(

        rut:String,
        nombre:String,
        apellidos:String,
        email:String,
        celular:String,
        direccion:String,
        lat:Double,
        lng:Double,

        estado:Int,
        idSolicitud:String,
        codValidacion:String,
        fechaCreacion:Date,

        fotoCalle:Int,
        fotoCondominio:Int,
        fotoEntrada:Int,
        fotoInterior:Int,
        fotoPanoramica:Int,
        fotoCuenta1:Int,
        fotoCuenta2:Int,
        fotoPersonalizada:Int,
        fotoPersonalizadaGlosa:String,
        tolerancia:Int,
        tipoVerificacion:String
    
    ) {
            this.rut=rut
            this.nombre=nombre
            this.apellidos=apellidos
            this.email=email
            this.celular=celular
            this.direccion=direccion
            this.lat=lat
            this.lng=lng
            this.estado=estado
            this.idSolicitud=idSolicitud
            this.codValidacion=codValidacion
            this.fechaCreacion=fechaCreacion
            this.fotoCalle=fotoCalle
            this.fotoCondominio=fotoCondominio
            this.fotoEntrada=fotoEntrada
            this.fotoInterior=fotoInterior
            this.fotoPanoramica=fotoPanoramica
            this.fotoCuenta1=fotoCuenta1
            this.fotoCuenta2=fotoCuenta2
            this.fotoPersonalizada=fotoPersonalizada
            this.fotoPersonalizadaGlosa=fotoPersonalizadaGlosa
            this.tolerancia=tolerancia
            this.tipoVerificacion=tipoVerificacion
        
    }

    fun getLocation():Location{
        var ubicacion=Location("")
        ubicacion.longitude=this.lng
        ubicacion.latitude=this.lat
        return ubicacion
    }

 
}
