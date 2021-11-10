package cl.felipecastillof.qrreader.domain

import cl.felipecastillof.qrreader.data.ValidarRepository
import cl.felipecastillof.qrreader.data.model.ParametrosVerificacionModel

class BuscarParametrosAVerificarUseCase( ) {

    private val repository=ValidarRepository( )

    suspend operator fun invoke(idSolicitud:String): ParametrosVerificacionModel? {
        return repository.getParametros(idSolicitud)
    }
}