package com.azhar.puasa.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.azhar.puasa.R
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets

/**
 * Created by Azhar Rivaldi on 16-10-2020
 */

class FragmentHalDibolehkan : Fragment() {

    private var webviewDetail: WebView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_puasa, container, false)

        webviewDetail = rootView.findViewById(R.id.webviewDetail)

        loadJSON()

        return rootView
    }

    private fun loadJSON() {
        try {
            val stream = resources.assets.open("list_categories.json")
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            val strContents = String(buffer, StandardCharsets.UTF_8)
            try {
                val jsonObjectOne = JSONObject(strContents)
                val jsonObjectTwo = jsonObjectOne.getJSONObject("yang_dibolehkan")
                val HalYangDibolehkan = jsonObjectTwo.getString("description")
                webviewDetail?.loadData(HalYangDibolehkan, "text/html; charset=utf-8", "UTF-8")
            } catch (e: JSONException) {
                e.printStackTrace()
                Toast.makeText(activity, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
            }
        } catch (ignored: IOException) {
            Toast.makeText(activity, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show()
        }
    }
}