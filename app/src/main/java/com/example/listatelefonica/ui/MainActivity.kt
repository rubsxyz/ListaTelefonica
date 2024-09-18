package com.example.listatelefonica.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listatelefonica.adapter.ContactoListAdapter
import com.example.listatelefonica.databinding.ActivityMainBinding
import com.example.listatelefonica.model.Contacto
import com.example.listatelefonica.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var adapter: ContactoListAdapter
    private var listaContacto: List<Contacto> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.recyclerViewContactos.layoutManager = LinearLayoutManager(this)
        viewModel.getListaContacto()
        observe()

        binding.buttonNovoContacto.setOnClickListener {
            launcher.launch(Intent(applicationContext, NovoContactoActivity::class.java))
        }

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null && it.resultCode == 1) {
                viewModel.getListaContacto()
            }
        }
    }

    private fun placeAdapter() {
        adapter = ContactoListAdapter(listaContacto) { contacto ->
            val i = Intent(this, DetalhesContactoActivity::class.java)
            i.putExtra("id", contacto.id)
            launcher.launch(i)
        }
        binding.recyclerViewContactos.adapter = adapter
    }

    private fun observe() {
        viewModel.listaContactos().observe(this, Observer {
            listaContacto = it
            placeAdapter()
        })
    }
}
