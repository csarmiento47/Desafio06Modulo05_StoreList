package com.desafiolatam.listadodetiendas.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafiolatam.listadodetiendas.adapter.StoreAdapter
import com.desafiolatam.listadodetiendas.data.StoreRepository
import com.desafiolatam.listadodetiendas.databinding.ActivityMainBinding
import com.desafiolatam.listadodetiendas.model.Store

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView(StoreRepository.listadoTiendas)
    }

    private fun initRecyclerView(list: List<Store>){
        adapter = StoreAdapter(list) { onItemSelected(it) }
        binding.recyclerViewStores.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewStores.adapter = adapter
    }

    private fun onItemSelected(store: Store){
        startActivity(Intent(applicationContext, StoreDetailActivity::class.java).apply {
            putExtra("BUNDLE", Bundle().apply {
                putParcelable("STORE",store)
                putInt("ID",store.id)
            })
        })
    }
}