package space.lobanovi.taskapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import space.lobanovi.taskapp.ui.home.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class TaskDatabase : RoomDatabase(){
    abstract fun dao():TaskDao
}


