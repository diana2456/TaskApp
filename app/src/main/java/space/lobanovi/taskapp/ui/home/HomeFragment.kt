package space.lobanovi.taskapp.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import space.lobanovi.taskapp.App
import space.lobanovi.taskapp.R
import space.lobanovi.taskapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(){
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter
    private var task = arrayListOf<TaskModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initViews()
        initListeners()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort){
            val item = arrayOf("По дате","По алфавиту")
            val builder = AlertDialog.Builder(requireContext())
            with (builder){
                setTitle("Cортировать по:")
                setItems(item){o1,o2->
               when(o2){
                   0->{
                       taskAdapter.addTask(App.db.dao().getListByDate())
                   }
                   1->{
                       taskAdapter.addTask(App.db.dao().getListByAlphabet())
                   }
               }
                }
            }.show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tulbar,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initListeners() {
        binding.fabHome.setOnClickListener {
            findNavController().navigate(R.id.newTaskFragment)
        }
    }
    private fun initViews() {
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }

        val listOfTask = App.db.dao().getAllTask()
        taskAdapter.addTask(listOfTask)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}