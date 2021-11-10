package cl.felipecastillof.qrreader.domain

import cl.felipecastillof.qrreader.data.ValidarRepository

class ChequearSiCodigoExisteEnServerUseCase( ) {

    private val repository=ValidarRepository( )

    suspend operator fun invoke(codigoAValidar:String): Boolean {
        return repository.validacionCodigo(codigoAValidar)
    }
}