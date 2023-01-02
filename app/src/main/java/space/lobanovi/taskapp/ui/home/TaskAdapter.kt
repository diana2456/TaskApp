package space.lobanovi.taskapp.ui.home
import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.lobanovi.taskapp.databinding.TaskItemBinding
import space.lobanovi.taskapp.extenssion.loadImage

open class TaskAdapter(private var onClick:(Int) -> Unit,
                       private var onLongClick : (Int) -> Unit)
    : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    private var taskList = arrayListOf<TaskModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun addTask(list: List<TaskModel>) {
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()
    }
  fun getTask(pos:Int):TaskModel{
        return taskList[pos]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskModel = taskList[position])
        holder.itemView.setBackgroundColor(Color.parseColor(mColors[position % 2]))
    }
    override fun getItemCount(): Int = taskList.size

    inner class ViewHolder(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(taskModel: TaskModel) {
            binding.tvTitle.text = taskModel.title
            binding.tvDesc.text = taskModel.description
            binding.image.loadImage(taskModel.image)
            binding.data.text = taskModel.data
            itemView.setOnLongClickListener {
                onLongClick(adapterPosition)
                return@setOnLongClickListener true
            }
            itemView.setOnClickListener { onClick(adapterPosition) }
        }
    }

    private var mColors = arrayOf("#FFFFFFFF","#464242")
}