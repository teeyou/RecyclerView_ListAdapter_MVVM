package teeu.android.recyclerview_listadapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.*
import teeu.android.recyclerview_listadapter.databinding.FragmentMainBinding
import teeu.android.recyclerview_listadapter.databinding.ListItemBinding

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    val viewModel : MainViewModel by viewModels()
    val myAdapter : MyAdapter by lazy {
        MyAdapter(viewModel)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this@MainFragment

        binding.viewModel = viewModel

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = myAdapter
        }
//        myAdapter.submitList(viewModel.dataList)
        viewModel.dataList.observe(viewLifecycleOwner) { list ->
            myAdapter.submitList(list)
            Log.d("MYTAG", "observed!")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.fabAdd.setOnClickListener {
//            viewModel.dataList.reverse()
//            myAdapter.submitList(viewModel.dataList)
//        }

        val itemTouchHelper = ItemTouchHelper(MyItemTouchHelperCallback(binding.recyclerView))
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }
}