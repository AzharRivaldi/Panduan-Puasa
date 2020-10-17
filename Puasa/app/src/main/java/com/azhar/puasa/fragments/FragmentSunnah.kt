package com.azhar.puasa.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azhar.puasa.R
import com.azhar.puasa.adapter.AdapterSunnah
import com.azhar.puasa.model.ModelPuasa
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

/**
 * Created by Azhar Rivaldi on 16-10-2020
 */

class FragmentSunnah : Fragment() {

    private var rvSunnah: RecyclerView? = null
    private var adapterSunnah: AdapterSunnah? = null
    private val modelPuasaList: MutableList<ModelPuasa> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_sunnah, container, false)

        adapterSunnah = AdapterSunnah(modelPuasaList)
        rvSunnah = rootView.findViewById(R.id.rvSunnah)
        rvSunnah?.setLayoutManager(LinearLayoutManager(activity))
        rvSunnah?.setHasFixedSize(true)
        rvSunnah?.setAdapter(adapterSunnah)

        getListKategori()

        /*adapterSunnah?.setOnItemClickCallback {
            Intent intent = new Intent(getActivity(), DetailSunnahActivity.class);
                intent.putExtra(DetailSunnahActivity.DETAIL_RESTO, modelPuasa);
                startActivity(intent);
        }*/

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
                    val jsonArray = jsonObjectOne.getJSONArray("sub_sunnah")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObjectTwo = jsonArray.getJSONObject(i)
                        val dataApi = ModelPuasa()
                        dataApi.kategoriPuasa = jsonObjectTwo.getString("title")
                        modelPuasaList.add(dataApi)
                    }
                    adapterSunnah?.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(activity, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                }
            } catch (ignored: IOException) {
                Toast.makeText(activity, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show()
            }
        }
}