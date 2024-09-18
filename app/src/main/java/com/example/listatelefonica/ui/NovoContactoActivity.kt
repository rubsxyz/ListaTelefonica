package com.example.listatelefonica.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.listatelefonica.databinding.ActivityNovoContactoBinding
import com.example.listatelefonica.viewmodel.NovoContactoViewModel

class NovoContactoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNovoContactoBinding
    private lateinit var viewModel: NovoContactoViewModel
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var i: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNovoContactoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        i = intent

        viewModel = ViewModelProvider(this)[NovoContactoViewModel::class.java]
        observe()

        binding.buttonGravar.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val email = binding.editEmail.text.toString()
            val telefone = binding.editTelefone.text.toString()

            // Check if name or phone number is empty
            if (nome.isEmpty()) {
                Toast.makeText(this, "Nome não pode ser vazio", Toast.LENGTH_SHORT).show()
            } else if (telefone.isEmpty()) {
                Toast.makeText(this, "Telefone não pode ser vazio", Toast.LENGTH_SHORT).show()
            } else {
                // Proceed with insertion if both fields are filled
                viewModel.insert(nome, email, telefone)
            }
        }

        binding.buttonCancelar.setOnClickListener {
            setResult(0, i)
            finish()
        }

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        }
    }

    private fun observe() {
        viewModel.novoContacto().observe(this, Observer {
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
            setResult(1, i)
            finish()
        })
    }
}
