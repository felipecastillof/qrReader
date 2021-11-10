package cl.felipecastillof.qrreader.escanearqr.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EscanearQRViewModel:ViewModel() {



    val _respuestaValidacion= MutableLiveData<String>()

    fun makeWhatYouWantInsideThisMethod(codigo:String){

        viewModelScope.launch {
            _respuestaValidacion.postValue(codigo)

        }
    }

}