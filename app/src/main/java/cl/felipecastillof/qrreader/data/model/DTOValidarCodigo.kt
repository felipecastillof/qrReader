package cl.felipecastillof.qrreader.data.model


data class DTOValidarCodigo(val estado:Int, val data:DTOValidarCodigoDeta)

data class DTOValidarCodigoDeta(val encontrada:Boolean)


