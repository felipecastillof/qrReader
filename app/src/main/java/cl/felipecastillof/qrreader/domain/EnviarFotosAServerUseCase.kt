package cl.felipecastillof.qrreader.domain

import cl.felipecastillof.qrreader.data.ValidarRepository
import cl.felipecastillof.qrreader.data.model.DTOResultadosResponse
import java.io.File

class EnviarFotosAServerUseCase( ) {

    private val repository=ValidarRepository( )

    suspend operator fun invoke(foto:File,nombreArchivo:String): DTOResultadosResponse? {
        return repository.enviarFoto(foto,nombreArchivo)
    }
}