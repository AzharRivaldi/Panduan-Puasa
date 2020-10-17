package com.azhar.puasa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.azhar.puasa.R
import com.azhar.puasa.model.ModelPuasa
import com.azhar.puasa.utils.OnItemClickCallback
import kotlinx.android.synthetic.main.list_kategori_puasa.view.*

/**
 * Created by Azhar Rivaldi on 15-10-2020
 */

class AdapterRamadhan(private val modelPuasa: List<ModelPuasa>) : RecyclerView.Adapter<AdapterRamadhan.ViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback?) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_kategori_puasa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelPuasa[position]
        holder.btnKategori.text = data.kategoriPuasa
        holder.btnKategori.setOnClickListener {
            onItemClickCallback?.onItemClicked(data)
        }
    }

    override fun getItemCount(): Int {
        return modelPuasa.size
    }

    //Class Holder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var listKategori: LinearLayout
        var btnKategori: Button

        init {
            listKategori = itemView.listKategori
            btnKategori = itemView.btnKategori
        }
    }
}