package cl.felipecastillof.qrreader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.felipecastillof.qrreader.common.Const

class MainViewModel:ViewModel() {

    val _fragmentoActivo = MutableLiveData<String>()

    fun comienza(){
        _fragmentoActivo.postValue(Const.ABRE_MAIN)
    }

    fun abreEscanearQR(){
        _fragmentoActivo.postValue(Const.ABRE_ESCANEAR_QR)
    }



    fun abreMain(){
        _fragmentoActivo.postValue(Const.ABRE_MAIN)
    }
}