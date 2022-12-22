package space.lobanovi.taskapp.ui.onBoard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import space.lobanovi.taskapp.databinding.FragmentOnBoardBinding


class OnBoardFragment : Fragment() {

    private var _binding: FragmentOnBoardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardBinding.inflate(LayoutInflater.from(context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val adapter = BoardAdapter(childFragmentManager,this::onSkipClick,this::onNextClick)
        adapter.also { it.also { binding.vpBoard.adapter = it } }

        binding.dotsIndicator.attachTo(binding.vpBoard)
    }
    private fun onSkipClick(){
        binding.vpBoard.currentItem = 2
    }
    private fun onNextClick(){
           binding.vpBoard.setCurrentItem((+1).initClick(),true)
    }
    private fun Int.initClick(): Int {
        return binding.vpBoard.currentItem + this
    }
}