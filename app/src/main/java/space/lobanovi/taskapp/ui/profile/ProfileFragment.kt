package space.lobanovi.taskapp.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import space.lobanovi.taskapp.databinding.FragmentProfileBinding
import space.lobanovi.taskapp.utils.Preferences


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var imgUri: String = ""

    private var mGetContent = this.registerForActivityResult<String, Uri>(
        ActivityResultContracts.GetContent()
    ) { uri ->
        binding.imageView.setImageURI(uri)
        imgUri = uri.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(LayoutInflater.from(context), container, false)
        imageChooser()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val sh: SharedPreferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val s1 = sh.getString("name", "")
        binding.editName.setText(s1)
            binding.btn.setOnClickListener {
              Preferences(requireContext()).isProfile(imgUri)
                Preferences(requireContext()).setProfile(imgUri)
        }
    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences: SharedPreferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putString("name", binding.editName.text.toString())
        myEdit.apply()
            binding.btn.setOnClickListener {
                Preferences(requireContext()).isProfile(imgUri)
                Preferences(requireContext()).setProfile(imgUri)
        }
    }

    private fun imageChooser() {
        binding.imageView.setOnClickListener {
            mGetContent.launch("image/*")
        }
    }
}


