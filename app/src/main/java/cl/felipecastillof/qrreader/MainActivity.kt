package cl.felipecastillof.qrreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import cl.felipecastillof.qrreader.common.Const
import cl.felipecastillof.qrreader.databinding.ActivityMainBinding
import cl.felipecastillof.qrreader.escanearqr.view.EscanearQrFragment
import cl.felipecastillof.qrreader.main.view.MainFragment

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    var fragmentoActivo=""
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel._fragmentoActivo.observe(this, Observer { currentFragment ->
            fragmentoActivo=currentFragment
            abreFragment(fragmentoActivo)
        })

        abreFragment(Const.ABRE_MAIN)
    }


    fun abreFragment(accion: String?) {
        /*
        if (getFragmentManager().findFragmentById(R.id.myfragment)!=null)
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.myfragment)).commitAllowingStateLoss();
*/

        var transaction =
            supportFragmentManager.beginTransaction()
        var newFragment: Fragment? = null
        when (accion) {
            Const.ABRE_MAIN -> {
                transaction = supportFragmentManager.beginTransaction()
                newFragment = MainFragment()
            }
            Const.ABRE_ESCANEAR_QR -> {
                transaction = supportFragmentManager.beginTransaction()
                newFragment = EscanearQrFragment()
            }
        }
        transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
        transaction.replace(R.id.myfragment, newFragment!!, accion)
        transaction.addToBackStack(accion)
        transaction.commitAllowingStateLoss()

    }

}