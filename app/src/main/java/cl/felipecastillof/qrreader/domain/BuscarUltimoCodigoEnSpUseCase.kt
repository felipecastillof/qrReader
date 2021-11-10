package cl.felipecastillof.qrreader.domain

import cl.felipecastillof.qrreader.data.ValidarRepository
import cl.felipecastillof.qrreader.data.model.CodigoSolicitudModel

class BuscarUltimoCodigoEnSpUseCase( ) {

    private val repository=ValidarRepository( )

    suspend operator fun invoke(): CodigoSolicitudModel? {
        return repository.getCodigoSolicitudFromSp()
    }
}