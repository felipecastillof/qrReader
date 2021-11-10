package cl.felipecastillof.qrreader.data.model

import cl.felipecastillof.qrreader.common.Const
import cl.felipecastillof.qrreader.common.Utils

class ValidacionProvider {
    companion object{
        fun getValidacionResultado(): Boolean {
            var res:Boolean=if(Utils.leeConf(Const.PREF_RESULTADO_VALIDACION,"false").equals("false")) false else true
            return res
        }


    }
}