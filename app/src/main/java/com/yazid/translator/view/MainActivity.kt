package com.yazid.translator.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.yazid.translator.R
import com.yazid.translator.viewModel.viewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModelInst:viewModel by viewModels();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val TextEditorInput= findViewById<EditText>(R.id.inputTextEditor);
        val outputTextEditor= findViewById<TextView>(R.id.outputTextEditor);
        val TranslateBtm= findViewById<Button>(R.id.button2);
        val drawerBtm= findViewById<ImageButton>(R.id.DrawerBtm);
        val drawerLayout= findViewById<DrawerLayout>(R.id.drawer_layout);
        val navViewLayout= findViewById<NavigationView>(R.id.nav_view);
        // Translate
        TranslateBtm.setOnClickListener {
            viewModelInst.viewModel_Translate(TextEditorInput.text.toString())
        }
        // observe
        viewModelInst.LastTranslatedWordLiveData.observe(this, Observer {

            outputTextEditor.text = it.toString();
        })
        // drawerBtm
        drawerBtm.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        navViewLayout.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.nav_item2 -> {
                    val linkedinUrl = "https://www.linkedin.com/in/mahmoudyazid/"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl))
                    startActivity(intent)
                    true
                }
                else -> {
                    false
                }
            }
        }




    }

    private fun startActivities(intent: Intent) {

    }
}