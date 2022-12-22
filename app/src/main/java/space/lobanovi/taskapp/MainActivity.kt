package space.lobanovi.taskapp

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import space.lobanovi.taskapp.R.id.*
import space.lobanovi.taskapp.databinding.ActivityMainBinding
import space.lobanovi.taskapp.ui.home.HomeFragment
import space.lobanovi.taskapp.ui.onBoard.OnBoardFragment
import space.lobanovi.taskapp.utils.Preferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(nav_host_fragment_activity_main).also {
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                navigation_home, navigation_dashboard, navigation_notifications,
                navigation_profile,
                newTaskFragment
            )
        )
        if(Preferences(applicationContext).isBoardingShowed())
            navController.navigate(navigation_home)
        else navController.navigate(onBoardFragment)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if(destination.id == newTaskFragment || destination.id == onBoardFragment){
                navView.visibility = View.GONE
            }else navView.visibility = View.VISIBLE
            if (destination.id == onBoardFragment){
                supportActionBar?.hide()
            }
        }

    }
}