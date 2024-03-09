package com.yazid.translator.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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
        // Translate
        TranslateBtm.setOnClickListener {
            viewModelInst.viewModel_Translate(TextEditorInput.text.toString())
        }
        // observe
        viewModelInst.LastTranslatedWordLiveData.observe(this, Observer {

            outputTextEditor.text = it.toString();
        })


    }
}