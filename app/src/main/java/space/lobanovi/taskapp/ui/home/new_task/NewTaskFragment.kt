package space.lobanovi.taskapp.ui.home.new_task

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import space.lobanovi.taskapp.App
import space.lobanovi.taskapp.databinding.FragmentNewTaskBinding
import space.lobanovi.taskapp.ui.home.TaskModel

class NewTaskFragment : Fragment(){
    private lateinit var binding: FragmentNewTaskBinding
    var imgUri : String = ""

    private var mGetContent = this.registerForActivityResult<String, Uri>(
        ActivityResultContracts.GetContent()
    ){ uri ->
        binding.imageNewTask.setImageURI(uri)
        this.imgUri = uri.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskBinding.inflate(LayoutInflater.from(context),container,false)

        initListeners()
        return binding.root
    }
    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            App.db.dao().insert(TaskModel(
               image =  imgUri,
               title =    binding.etTitle.text.toString(),
               description = binding.etDesc.text.toString(),
                data =  binding.etData.text.toString()
            )
            )
            findNavController().navigateUp()
        }
        binding.imageNewTask.setOnClickListener{
            mGetContent.launch("image/*")
        }
    }
}