package com.example.miprimeraapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textRecibido = findViewById<TextView>(R.id.textRecibido)
        val btnCompartirDeNuevo = findViewById<Button>(R.id.btnCompartirDeNuevo)

        if (intent?.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            val textoCompartido = intent.getStringExtra(Intent.EXTRA_TEXT)
            if (textoCompartido != null) {
                textRecibido.text = textoCompartido
            }
        }

        btnCompartirDeNuevo.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, textRecibido.text.toString())
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sendIntent, null))
        }
    }
}