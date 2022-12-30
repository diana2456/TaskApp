package space.lobanovi.taskapp.ui.home
import android.app.AlertDialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.lobanovi.taskapp.App
import space.lobanovi.taskapp.databinding.TaskItemBinding
import space.lobanovi.taskapp.extenssion.loadImage

class TaskAdapter() : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    private var taskList = arrayListOf<TaskModel>()

    fun addTask(list: List<TaskModel>) {
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()
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
        holder.bind(taskList[position])
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
                val builder = AlertDialog.Builder(itemView.context)
                with(builder){
                    setTitle("Вы точно хотите удалить ${taskModel.title}")
                    setPositiveButton("Да") { o1, o2 ->
                        App.db.dao().deleteTask(taskModel)
                        taskList.clear()
                        taskList.addAll(App.db.dao().getAllTask())
                        notifyDataSetChanged()
                    }
                    setNeutralButton("Нет"){o1,o2 ->
                        o1.dismiss()
                    }
                }.show()
                return@setOnLongClickListener true
            }
        }
    }

    var mColors = arrayOf("#FFFFFFFF","#FF464242")
}