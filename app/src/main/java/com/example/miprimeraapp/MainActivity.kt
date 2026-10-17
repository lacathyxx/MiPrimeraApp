package com.example.miprimeraapp

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.LinkedList

class MainActivity : AppCompatActivity() {
    private val elementos = LinkedList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editElemento = findViewById<EditText>(R.id.editElemento)
        val btnAnadir = findViewById<Button>(R.id.btnAnadir)
        val listElementos = findViewById<ListView>(R.id.listElementos)

        val adapter = ArrayAdapter(this, R.layout.item_row, R.id.textItem, elementos)

        listElementos.adapter = adapter

        /* Add Elements */
        btnAnadir.setOnClickListener {
            val texto = editElemento.text.toString()
            if (texto.isNotBlank()) {
                if (elementos.contains(texto)) {
                    Toast.makeText(this, "The item already exists", Toast.LENGTH_SHORT).show()
                } else {
                    elementos.add(texto)
                    adapter.notifyDataSetChanged()
                    editElemento.text.clear()
                }
            }
        }
        /* Delete Elements */
        listElementos.setOnItemLongClickListener { _, _, position, _ ->
            MaterialAlertDialogBuilder(this)
                .setBackground(ContextCompat.getDrawable(this, R.drawable.dialog_bg))
                .setMessage("Do you want to delete this item?")
                .setPositiveButton("Yes") { _, _ ->
                    elementos.removeAt(position)
                    adapter.notifyDataSetChanged()
                }
                .setNegativeButton("No", null)
                .show()
            true
        }



    }
}