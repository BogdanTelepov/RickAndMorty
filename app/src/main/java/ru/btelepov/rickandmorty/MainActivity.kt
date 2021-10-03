package ru.btelepov.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import ru.btelepov.rickandmorty.databinding.ActivityMainBinding
import ru.btelepov.rickandmorty.epoxy.CharacterDetailsEpoxyController

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    private val epoxyController = CharacterDetailsEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", 1)

        viewModel.getCharacterByIdResponse.observe(this) { character ->
            if (character == null) {
                return@observe
            }
            epoxyController.character = character
        }

        viewModel.getCharacterById(id)
        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}