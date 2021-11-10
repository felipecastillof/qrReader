package cl.felipecastillof.qrreader.data.model

class CodigoSolicitudModel {
    private var codigoAEncontrar: String = ""
    private var encontrada: Boolean = false
    private var idSolicitud: String = ""
    private var codValidacion: String = ""

    constructor(codigoAEncontrar: String, encontrada: Boolean) {
        this.codigoAEncontrar=codigoAEncontrar
        this.encontrada=encontrada
        this.idSolicitud=codigoAEncontrar.split("|").get(0)
        this.codValidacion=codigoAEncontrar.split("|").get(0)


    }


    fun getCodigoAEncontrar(): String {
        return codigoAEncontrar
    }

    fun getIdSolicitud(): String {
        return idSolicitud
    }

    fun getCodValidacion(): String {
        return codValidacion
    }
    fun getEncontrada(): Boolean {
        return encontrada
    }
}
