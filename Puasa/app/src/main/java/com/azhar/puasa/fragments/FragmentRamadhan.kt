package com.azhar.puasa.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azhar.puasa.R
import com.azhar.puasa.activities.DetailRamadhanActivity
import com.azhar.puasa.adapter.AdapterRamadhan
import com.azhar.puasa.model.ModelPuasa
import com.azhar.puasa.utils.OnItemClickCallback
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

/**
 * Created by Azhar Rivaldi on 16-10-2020
 */

class FragmentRamadhan : Fragment() {

    private var rvRamadhan: RecyclerView? = null
    private var adapterRamadhan: AdapterRamadhan? = null
    private val modelPuasaList: MutableList<ModelPuasa> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_ramadhan, container, false)

        adapterRamadhan = AdapterRamadhan(modelPuasaList)
        rvRamadhan = rootView.findViewById(R.id.rvRamadhan)
        rvRamadhan?.setLayoutManager(LinearLayoutManager(activity))
        rvRamadhan?.setHasFixedSize(true)
        rvRamadhan?.setAdapter(adapterRamadhan)

        getListKategori()

        adapterRamadhan?.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(modelPuasa: ModelPuasa?) {
                val intent = Intent(activity, DetailRamadhanActivity::class.java)
                intent.putExtra(DetailRamadhanActivity.DETAIL_PUASA, modelPuasa)
                startActivity(intent)
            }
        })

        return rootView
    }

    private fun getListKategori() {
            try {
                val stream = resources.assets.open("list_categories.json")
                val size = stream.available()
                val buffer = ByteArray(size)
                stream.read(buffer)
                stream.close()
                val strContents = String(buffer, StandardCharsets.UTF_8)
                try {
                    val jsonObjectOne = JSONObject(strContents)
                    val jsonArray = jsonObjectOne.getJSONArray("sub_ramadhan")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObjectTwo = jsonArray.getJSONObject(i)
                        val dataApi = ModelPuasa()
                        dataApi.idPuasa = jsonObjectTwo.getInt("id")
                        dataApi.kategoriPuasa = jsonObjectTwo.getString("title")
                        dataApi.descPuasa = jsonObjectTwo.getString("description")
                        modelPuasaList.add(dataApi)
                    }
                    adapterRamadhan?.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(activity, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                }
            } catch (ignored: IOException) {
                Toast.makeText(activity, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show()
            }
        }

}