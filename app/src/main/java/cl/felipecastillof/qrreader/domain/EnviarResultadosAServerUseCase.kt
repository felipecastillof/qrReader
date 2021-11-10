package cl.felipecastillof.qrreader.domain

import cl.felipecastillof.qrreader.data.ValidarRepository
import cl.felipecastillof.qrreader.data.model.DTOResultados
import cl.felipecastillof.qrreader.data.model.DTOResultadosResponse

class EnviarResultadosAServerUseCase( ) {

    private val repository=ValidarRepository( )

    suspend operator fun invoke(dtoResultados: DTOResultados): DTOResultadosResponse? {
        return repository.enviarResultados(dtoResultados)
    }
}