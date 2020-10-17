package com.azhar.puasa.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.azhar.puasa.R
import com.azhar.puasa.model.ModelPuasa
import kotlinx.android.synthetic.main.activity_detail_ramadhan.*

/**
 * Created by Azhar Rivaldi on 16-10-2020
 */

class DetailRamadhanActivity : AppCompatActivity() {

    var modelPuasa: ModelPuasa? = null
    var idPuasa = 0
    var descPuasa: String? = null
    var kategoriPuasa: String? = null

    @SuppressLint("Assert")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_ramadhan)

        toolbar.setTitle(null)
        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        modelPuasa = intent.getSerializableExtra(DETAIL_PUASA) as ModelPuasa
        if (modelPuasa != null) {
            idPuasa = modelPuasa!!.idPuasa
            descPuasa = modelPuasa?.descPuasa
            kategoriPuasa = modelPuasa?.kategoriPuasa

            tvTitle.setText(kategoriPuasa)
            webviewDetail.loadData(descPuasa, "text/html; charset=utf-8", "UTF-8")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val DETAIL_PUASA = "listDetail"
    }
}