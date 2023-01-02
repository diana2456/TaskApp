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
import space.lobanovi.taskapp.R
import space.lobanovi.taskapp.databinding.FragmentNewTaskBinding
import space.lobanovi.taskapp.extenssion.loadImage
import space.lobanovi.taskapp.ui.home.HomeFragment.Companion.EDIT_KEY
import space.lobanovi.taskapp.ui.home.TaskModel

@Suppress("unused")
class NewTaskFragment : Fragment(){
    private lateinit var binding: FragmentNewTaskBinding
    private lateinit var task: TaskModel
    private var imgUri : String = ""

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
        initViews()
        return binding.root
    }

    private fun updateDate(taskModel: TaskModel){
     taskModel.title = binding.etTitle.text.toString()
     taskModel.description = binding.etDesc.text.toString()
     taskModel.image = imgUri
     taskModel.data = binding.etData.text.toString()
 }
    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            if (arguments != null){
                updateData(task)
            }else{
                saveData()
            }
            findNavController().navigateUp()
        }
        binding.imageNewTask.setOnClickListener{
            mGetContent.launch("image/*")
        }
    }
    private fun saveData(){
        App.db.dao().insert(TaskModel(
            image =  imgUri,
            title =    binding.etTitle.text.toString(),
            description = binding.etDesc.text.toString(),
            data =  binding.etData.text.toString()
        )
        )
    }
    private fun updateData(task : TaskModel){
        task.title = binding.etTitle.text.toString()
        task.description = binding.etDesc.text.toString()
        task.data = binding.etData.text.toString()
        binding.imageNewTask.loadImage(task.image)
        App.db.dao().updateTask(task)
    }
    private fun initViews() {
        if(arguments != null){
            binding.btnSave.text = R.string.update.toString()
            task = arguments?.getSerializable(EDIT_KEY) as TaskModel
            binding.etTitle.setText(task.title)
            binding.etDesc.setText(task.description)
            binding.etData.setText(task.data)
            binding.imageNewTask.loadImage(task.image)
           // Glide.with(requireContext()).load(task.data).circleCrop().placeholder(R.drawable.b).into(binding.imageNewTask)
        }
        else{
            binding.btnSave.text = R.string.save.toString()
        }
    }
}