package space.lobanovi.taskapp.ui.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import space.lobanovi.taskapp.App
import space.lobanovi.taskapp.R
import space.lobanovi.taskapp.databinding.FragmentHomeBinding
import java.util.*

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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tulbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.atoz) {

                sortAlphabet()
            App.db.dao().sort()
            Toast.makeText(activity,"Cортировка по алфавиту",Toast.LENGTH_LONG).show()
        }

        if (id == R.id.data) {
                    everybodySort()
                    App.db.dao().everybodySort()
            Toast.makeText(activity, "Cортировка всего", Toast.LENGTH_LONG).show()
        }

        return super.onOptionsItemSelected(item)

    }

    private fun everybodySort() {
        Collections.sort(task,Comparator<TaskModel>{d, a ->
            d.title.compareTo(a.title)
            d.description.compareTo(a.description)
            d.data.compareTo(a.data)
        })
        taskAdapter.notifyDataSetChanged()
    }

    private fun sortAlphabet() {
        Collections.sort(task,Comparator <TaskModel>{ t, t2 ->
            t.title.compareTo(t2.title)
        })
        taskAdapter.notifyDataSetChanged()
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
