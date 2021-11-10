 package cl.felipecastillof.qrreader.main.view


 import android.os.Bundle
 import androidx.fragment.app.Fragment
 import android.view.LayoutInflater
 import android.view.View
 import android.view.ViewGroup
 import androidx.fragment.app.activityViewModels
 import cl.felipecastillof.qrreader.MainViewModel
 import cl.felipecastillof.qrreader.databinding.FragmentMainBinding


 class MainFragment : Fragment() {

     private val mainViewModel : MainViewModel by activityViewModels()

     private var _binding: FragmentMainBinding?=null
     private val binding get()=_binding!!

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

     }

     override fun onCreateView(
         inflater: LayoutInflater, container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         _binding= FragmentMainBinding.inflate(inflater, container, false)

         binding.btnEscanearQR.setOnClickListener { mainViewModel.abreEscanearQR() }



         return binding.root

     }

 }