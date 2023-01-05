package teeu.android.recyclerview_listadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import teeu.android.recyclerview_listadapter.databinding.ListItemBinding
import java.util.*

class MyAdapter(val viewModel: MainViewModel) :
    ListAdapter<MyData, RecyclerView.ViewHolder>(MyDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = MyHolder(
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ,viewModel
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyHolder) {
            val item = getItem(position)
            holder.bind(item)
        }
    }
    fun moveItem(fromPosition: Int, toPosition: Int) {
        val newList = currentList.toMutableList()
        Collections.swap(newList, fromPosition, toPosition)
        viewModel.dataList.value = newList
        submitList(viewModel.dataList.value)
    }

    fun removeItem(position: Int) {
        val newList = currentList.toMutableList()
        newList.removeAt(position)
        viewModel.dataList.value = newList
        submitList(viewModel.dataList.value)
    }
}

class MyHolder(val binding: ListItemBinding, val viewModel: MainViewModel) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MyData) {
        binding.item = item
        binding.viewModel = viewModel
//        binding.apply {
//            textViewTitle.text = item.title
//            textViewText.text = item.text
//            layoutViewholder.setOnClickListener { view ->
//                Snackbar.make(view, "Item $layoutPosition touched!", Snackbar.LENGTH_SHORT).show()
//            }
//        }
    }

    fun setAlpha(alpha: Float) {
        binding.apply {
            textViewTitle.alpha = alpha
            textViewText.alpha = alpha
        }
    }

}

class MyDiffCallback : DiffUtil.ItemCallback<MyData>() {
    override fun areItemsTheSame(oldItem: MyData, newItem: MyData): Boolean =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: MyData, newItem: MyData): Boolean =
        oldItem == newItem
}

class MyItemTouchHelperCallback(private val recyclerView: RecyclerView) :
    ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT
    ) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        (recyclerView.adapter as MyAdapter).moveItem(
            viewHolder.absoluteAdapterPosition,
            target.absoluteAdapterPosition
        )
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        (recyclerView.adapter as MyAdapter).removeItem(viewHolder.layoutPosition)
    }

    // 홀딩중인 ViewHolder 투명도를 변경
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when (actionState) {
            ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.ACTION_STATE_SWIPE -> {
                (viewHolder as MyHolder).setAlpha(0.5f)
            }
        }
    }

    // 홀딩중인 ViewHolder 투명도를 되돌림
    override fun clearView(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ) {
        super.clearView(recyclerView, viewHolder)
        (viewHolder as MyHolder).setAlpha(1.0f)
    }
}