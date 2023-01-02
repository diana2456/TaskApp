package space.lobanovi.taskapp.ui.onBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import space.lobanovi.taskapp.R
import space.lobanovi.taskapp.databinding.FragmentOnBoardPageBinding
import space.lobanovi.taskapp.utils.Preferences

class OnBoardPageFragment(private var listenerSkip:() -> Unit,
                          private var listenerNext:() -> Unit  ) : Fragment() {

    private var _binding: FragmentOnBoardPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardPageBinding.inflate(LayoutInflater.from(context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }
    private fun initViews() {
        arguments.let {
            val data = it?.getSerializable(ON_BOARD) as BoardModel
            binding.tvTitleBoard.text = data.title
            binding.tvDescBoard.text = data.description
            data.img.let { it1 ->
                if (it1 != null) {
                    binding.imageBoard.setImageResource(/* resId = */ it1)
                }
            }
            binding.btnSkip.isVisible = data.isLast == false
            binding.btnNext.isVisible = data.isLast == false
            binding.btnStart.isVisible = data.isLast == true
        }
    }
    private fun initListeners() {

        binding.btnNext.setOnClickListener{
             listenerNext.invoke()
        }

        binding.btnSkip.setOnClickListener{
             listenerSkip.invoke()
        }

        binding.btnStart.setOnClickListener{
           findNavController().navigate(R.id.navigation_home)
            Preferences(requireContext()).setBoardingShowed(true)
        }
    }
    companion object{
        const val ON_BOARD = "onBoard"
    }
}