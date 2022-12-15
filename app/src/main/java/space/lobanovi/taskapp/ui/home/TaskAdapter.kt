package space.lobanovi.taskapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.lobanovi.taskapp.databinding.TaskItemBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    fun addTask(taskModel: TaskModel){
        taskList.add(0,taskModel)
        notifyItemChanged(0)
    }

    private var taskList = arrayListOf<TaskModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(taskList[position])
    }

    override fun getItemCount(): Int = taskList.size

    inner class ViewHolder(private val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(taskModel: TaskModel) {
            binding.tvTitle.text = taskModel.title
            binding.tvDesc.text = taskModel.description
        }

    }

}