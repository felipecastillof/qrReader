package cl.felipecastillof.qrreader.data.model


data class DTOResultadosResponse(val estado:Int, val data:DTOResultadosResponseDeta)

data class DTOResultadosResponseDeta(val mensaje:String)

