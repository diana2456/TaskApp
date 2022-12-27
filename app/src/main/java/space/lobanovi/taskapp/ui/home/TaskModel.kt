package space.lobanovi.taskapp.ui.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskModel(
    var image: String = "",
    var title: String = "",
    var description: String = "",
    var data: String = ""
):java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}




