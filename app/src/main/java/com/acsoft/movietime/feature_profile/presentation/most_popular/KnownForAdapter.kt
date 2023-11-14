package com.acsoft.movietime.feature_profile.presentation.most_popular

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acsoft.movietime.R
import com.acsoft.movietime.core.BaseViewHolder
import com.acsoft.movietime.databinding.KnownForItemBinding
import com.acsoft.movietime.feature_profile.domain.entities.KnownFor
import com.acsoft.movietime.utils.AppConstants
import com.bumptech.glide.Glide

class KnownForAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var knownForList = listOf<KnownFor>()

    fun setKnownForList(knownForList: List<KnownFor>) {
        this.knownForList = knownForList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = KnownForItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KnownForViewHolder(itemBinding, parent.context)
    }

    override fun getItemCount(): Int {
        return knownForList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is KnownForViewHolder -> {
                holder.bind(knownForList[position])
            }
        }
    }

    private inner class KnownForViewHolder(val binding : KnownForItemBinding, val context: Context) :
        BaseViewHolder<KnownFor>(binding.root) {
        override fun bind(item: KnownFor) {
            Glide.with(context)
                .load(AppConstants.IMAGE_URL.plus(item.backdropPath))
                .centerCrop()
                .into(binding.ivBackdropPath)
            binding.tvTitle.text = item.title.takeUnless { it.isNullOrEmpty() } ?: context.getString(
                R.string.without_title)
        }
    }
}