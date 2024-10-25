package com.desafiolatam.listadodetiendas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.desafiolatam.listadodetiendas.R
import com.desafiolatam.listadodetiendas.databinding.ItemStoreBinding
import com.desafiolatam.listadodetiendas.model.Store

class StoreAdapter(
    private val storeList: List<Store>, var onClick: ((Store) -> Unit)) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    lateinit var binding: ItemStoreBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        binding = ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }

    override fun getItemCount(): Int = storeList.size

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val item = storeList[position]
        holder.onBind(item,onClick)
    }

    inner class StoreViewHolder(private val binding: ItemStoreBinding) : ViewHolder(binding.root){
        fun onBind(store: Store,onClick: (Store) -> Unit){
            with(binding) {
                store.run {
                    textViewStoreName.text = store.name
                    textViewStoreAttention.text= store.officeHours
                    Glide.with(imageViewStore.context)
                        .load(store.photo)
                        .centerCrop()
                        .error(R.drawable.baseline_error_outline_24)
                        .into(binding.imageViewStore)
                }

            }

            binding.clStore.setOnClickListener {
                onClick?.invoke(store)
            }
        }
    }
}