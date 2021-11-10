package cl.felipecastillof.qrreader.data.model

class DTOResultados {
    var idSolicitud:String=""
    var fotoCalle: String?=""
    var fotoCondominio: String?=""
    var fotoEntrada: String?=""
    var fotoInterior: String?=""
    var fotoPanoramica: String?=""
    var fotoCuenta1: String?=""
    var fotoCuenta2: String?=""
    var fotoPersonalizada: String?=""
    var latEncontrada:Double=0.0
    var lngEncontrada:Double=0.0

    constructor(resultadosModel: ResultadosModel){
        idSolicitud=resultadosModel.idSolicitud

        fotoCalle=resultadosModel.fotoCallePath?.let { it }?:""
        fotoCondominio=resultadosModel.fotoCondominioPath?.let { it }?:""
        fotoEntrada=resultadosModel.fotoEntradaPath?.let { it }?:""
        fotoInterior=resultadosModel.fotoInteriorPath?.let { it }?:""
        fotoPanoramica=resultadosModel.fotoPanoramicaPath?.let { it }?:""
        fotoCuenta1=resultadosModel.fotoCuenta1Path?.let { it }?:""
        fotoCuenta2=resultadosModel.fotoCuenta2Path?.let { it }?:""
        fotoPersonalizada=resultadosModel.fotoPersonalizadaPath?.let { it }?:""


//        fotoCalle= resultadosModel.fotoCalleDrawable?.let {
//            Utils.drawableToBase64(Utils.drawableToBitmap(it)!!)
//        }?:""
//        fotoCondominio= resultadosModel.fotoCondominioDrawable?.let {
//            Utils.drawableToBase64(Utils.drawableToBitmap(it)!!)
//        }?:""
//        fotoEntrada= resultadosModel.fotoEntradaDrawable?.let {
//            Utils.drawableToBase64(Utils.drawableToBitmap(it)!!)
//        }?:""
//        fotoInterior=  resultadosModel.fotoInteriorDrawable?.let {
//            Utils.drawableToBase64(Utils.drawableToBitmap(it)!!)
//        }
//        fotoPanoramica=  resultadosModel.fotoPanoramicaDrawable?.let {
//            Utils.drawableToBase64(Utils.drawableToBitmap(it)!!)
//        }?:""
//        fotoCuenta1=  resultadosModel.fotoCuenta1Drawable?.let {
//            Utils.drawableToBase64(Utils.drawableToBitmap(it)!!)
//        }?:""
//        fotoCuenta2=  resultadosModel.fotoCuenta2Drawable?.let{
//            Utils.drawableToBase64(Utils.drawableToBitmap(it)!!)
//        }?:""
//        fotoPersonalizada= resultadosModel.fotoPersonalizadaDrawable?.let{
//            Utils.drawableToBase64(Utils.drawableToBitmap(it)!!)
//        }?:""
        latEncontrada=resultadosModel.latEncontrada
        lngEncontrada=resultadosModel.lngEncontrada
    }
}