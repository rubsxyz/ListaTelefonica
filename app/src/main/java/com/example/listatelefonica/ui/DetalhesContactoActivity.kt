package com.example.listatelefonica.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.listatelefonica.databinding.ActivityDetalhesContactoBinding
import com.example.listatelefonica.model.Contacto
import com.example.listatelefonica.viewmodel.DetalheContactoViewModel

class DetalhesContactoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesContactoBinding
    private lateinit var viewModel: DetalheContactoViewModel
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var i: Intent
    private lateinit var contacto: Contacto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesContactoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        i = intent

        val id = i.getIntExtra("id", 0)
        if (id <= 0) {
            setResult(0, i)
            finish()
        }

        viewModel = ViewModelProvider(this)[DetalheContactoViewModel::class.java]
        observe()

        viewModel.getContacto(id)

        binding.buttonCancelar.setOnClickListener {
            changeEditable(false)
            binding.layoutEditar.visibility = View.VISIBLE
            binding.layoutEditarEliminar.visibility = View.GONE
        }

        binding.buttonEditar.setOnClickListener {
            changeEditable(true)
            binding.layoutEditar.visibility = View.GONE
            binding.layoutEditarEliminar.visibility = View.VISIBLE
        }

        binding.buttonEliminar.setOnClickListener {
            viewModel.delete(contacto)
            setResult(1, i)
            finish()
        }

        binding.buttonGravar.setOnClickListener {
            viewModel.update(
                Contacto(
                    id = contacto.id,
                    nome = binding.editNome.text.toString(),
                    email = binding.editEmail.text.toString(),
                    telefone = binding.editTelefone.text.toString(),
                )
            )
            setResult(1, i)
            finish()
        }

        binding.buttonVoltar.setOnClickListener { finish() }

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        }
        changeEditable(false)
    }

    private fun changeEditable(status: Boolean) {
        binding.editNome.isEnabled = status
        binding.editEmail.isEnabled = status
        binding.editTelefone.isEnabled = status
    }

    private fun observe() {
        viewModel.contacto().observe(this, Observer {
            contacto = it
            populate()
        })

        viewModel.delete().observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.update().observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun populate() {
        binding.editNome.setText(contacto.nome)
        binding.editEmail.setText(contacto.email)
        binding.editTelefone.setText(contacto.telefone)
    }
}
