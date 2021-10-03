package ru.btelepov.rickandmorty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.btelepov.rickandmorty.Constants.Companion.ARG_CHARACTER_ID
import ru.btelepov.rickandmorty.databinding.FragmentCharacterDetailsBinding
import ru.btelepov.rickandmorty.epoxy.CharacterDetailsEpoxyController


class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }
    private val epoxyController = CharacterDetailsEpoxyController()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characterId = requireArguments().getInt(ARG_CHARACTER_ID)
        viewModel.getCharacterByIdResponse.observe(viewLifecycleOwner) { character ->
            if (character == null) {
                return@observe
            }
            epoxyController.character = character
        }

        viewModel.getCharacterById(characterId)
        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}