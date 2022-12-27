package space.lobanovi.taskapp

import android.app.Application
import androidx.room.Room
import space.lobanovi.taskapp.data.local.room.TaskDatabase
import space.lobanovi.taskapp.ui.home.TaskModel




class App : Application() {
    override fun onCreate() {
       super.onCreate()
        db = Room.databaseBuilder(this, TaskDatabase::class.java,"database").allowMainThreadQueries().build()
    }

   companion object{
       lateinit var db: TaskDatabase
    }

 private val taskList: List<TaskModel> = ArrayList()
}