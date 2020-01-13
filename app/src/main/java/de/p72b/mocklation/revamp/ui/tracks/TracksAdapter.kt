package de.p72b.mocklation.revamp.ui.tracks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import de.p72b.mocklation.R
import de.p72b.mocklation.databinding.RecyclerviewLocationItemBinding
import de.p72b.mocklation.revamp.arch.TracksViewModel
import de.p72b.mocklation.revamp.room.LocationItem
import de.p72b.mocklation.revamp.util.SwipeAndTouchHelper.ActionCompletionContract

class TracksAdapter(private val viewModel: TracksViewModel): RecyclerView.Adapter<TracksAdapter.RepositoryViewHolder>(), ActionCompletionContract {

    private var list: ArrayList<LocationItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RecyclerviewLocationItemBinding>(
                layoutInflater, R.layout.recyclerview_location_item, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = list[position]
        holder.binding.item = item
        holder.binding.handler = Handler(viewModel)
        (holder.binding.vItemRoot as MaterialCardView).isChecked = item.selected
    }

    override fun onViewMoved(oldPosition: Int, newPosition: Int) {
        // nothing to do here
    }

    override fun onViewSwiped(position: Int) {
        viewModel.delete(list[position])
    }

    fun setData(items: List<LocationItem>) {
        list.clear()
        list.addAll(items.sortedBy { it.title })
        notifyDataSetChanged()
    }

    inner class RepositoryViewHolder(val binding: RecyclerviewLocationItemBinding) : RecyclerView.ViewHolder(binding.root)
}