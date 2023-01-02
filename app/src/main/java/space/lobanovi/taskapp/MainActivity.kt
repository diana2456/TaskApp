package space.lobanovi.taskapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import space.lobanovi.taskapp.R.id.*
import space.lobanovi.taskapp.databinding.ActivityMainBinding
import space.lobanovi.taskapp.utils.Preferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(nav_host_fragment_activity_main).apply {
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                navigation_home, navigation_dashboard, navigation_notifications,
                navigation_profile,
                newTaskFragment,
                authFragment
            )
        )
              navController.navigate(authFragment)

        if(Preferences(applicationContext).isBoardingShowed())
            navController.navigate(navigation_home)
        else navController.navigate(onBoardFragment)

        val list = setOf(newTaskFragment, onBoardFragment,authFragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if(list.contains(destination.id)){
                navView.visibility = View.GONE
            }else navView.visibility = View.VISIBLE
            if (list.contains(destination.id)){
                supportActionBar?.hide()
            }
        }

    }
}