package com.azhar.puasa.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.azhar.puasa.R
import com.azhar.puasa.fragments.*
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Created by Azhar Rivaldi on 16-10-2020
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        val menuItems: MutableList<MenuItem> = ArrayList()
        menuItems.add(MenuItem("Syarat & Rukun Puasa", R.drawable.ic_syarat))
        menuItems.add(MenuItem("Adab-adab Puasa", R.drawable.ic_adab))
        menuItems.add(MenuItem("Hal yang Diperbolehkan", R.drawable.ic_dibolehkan))
        menuItems.add(MenuItem("Hal yang Dilarang", R.drawable.ic_dilarang))
        menuItems.add(MenuItem("Puasa Ramadhan", R.drawable.ic_ramadhan))
        menuItems.add(MenuItem("Puasa Sunnah", R.drawable.ic_sunnah))
        navigationDrawer.setMenuItemList(menuItems)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.frameLayout, FragmentSyaratRukun())
                    .commit()
        }

        navigationDrawer.setOnMenuItemClickListener(SNavigationDrawer.OnMenuItemClickListener { position ->
            when (position) {
                0 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, FragmentSyaratRukun()).commit()
                }
                1 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, FragmentAdab()).commit()
                }
                2 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, FragmentHalDibolehkan()).commit()
                }
                3 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, FragmentHalMembatalkan()).commit()
                }
                4 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, FragmentRamadhan()).commit()
                }
                5 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, FragmentSunnah()).commit()
                }
            }
        })
    }
}