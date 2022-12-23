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
import com.bumptech.glide.Glide
import space.lobanovi.taskapp.R
import space.lobanovi.taskapp.databinding.FragmentProfileBinding
import space.lobanovi.taskapp.utils.Preferences


class ProfileFragment : Fragment(){
lateinit var preferences: Preferences

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    private var mGetContent = this.registerForActivityResult<String, Uri>(
        ActivityResultContracts.GetContent()
    ) { uri ->
        preferences.setProfile(uri.toString())
       Glide.with(requireContext()).load(uri).circleCrop().placeholder(R.drawable.b).into(binding.imageView)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        preferences = Preferences(requireContext())

        _binding = FragmentProfileBinding.inflate(LayoutInflater.from(context), container, false)
        imageChooser()
     Glide.with(requireContext()).load(preferences.isProfile()).circleCrop().placeholder(R.drawable.b).into(binding.imageView)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val sh: SharedPreferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val s1 = sh.getString("name", "")
        binding.editName.setText(s1)

    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences: SharedPreferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putString("name", binding.editName.text.toString())
        myEdit.apply()

    }

    private fun imageChooser() {
        binding.imageView.setOnClickListener {
            mGetContent.launch("image/*")
        }
    }
}


