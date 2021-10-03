package ru.btelepov.rickandmorty


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import ru.btelepov.rickandmorty.Constants.Companion.ARG_CHARACTER_ID
import ru.btelepov.rickandmorty.Extensions.findNavController
import ru.btelepov.rickandmorty.databinding.FragmentCharacterListBinding


class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private val epoxyController = CharacterListPagingEpoxyController(::onCharacterSelected)

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this).get(CharactersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCharacterByIdResponsePagedListLiveData.observe(viewLifecycleOwner) { pagedList ->
            epoxyController.submitList(pagedList)
        }
        binding.epoxyRecyclerView.setController(epoxyController)
    }


    private fun onCharacterSelected(characterId: Int) {
        findNavController().navigate(
            R.id.action_characterListFragment_to_characterDetailsFragment, bundleOf(
                ARG_CHARACTER_ID to characterId
            )
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}