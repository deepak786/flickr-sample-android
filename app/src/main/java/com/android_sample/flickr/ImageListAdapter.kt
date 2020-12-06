package com.android_sample.flickr

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android_sample.flickr.models.Photo

class ImageListAdapter(
    private val itemClickListener: RecyclerItemClickListener?,
) : ListAdapter<Photo, ImageListAdapter.Holder>(PhotoDiffCallback()) {


    // holder class
    inner class Holder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {

        // function to bind the data to binding layout
        fun bind(obj: Any?) {
            binding.setVariable(BR.obj, obj)
            binding.executePendingBindings()
        }

        init {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemClickListener?.onRecyclerItemClick(
                        adapterPosition,
                        getItem(adapterPosition)
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val obj: Any = getItem(holder.adapterPosition)
        holder.bind(obj)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.list_item_photo
    }
}

// diff callback for Photo
// this is help to determine if the item is changed or not to perform animation in recycler view
class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}

// item click listener
interface RecyclerItemClickListener {
    fun onRecyclerItemClick(position: Int, obj: Any?)
}