package ru.btelepov.rickandmorty.epoxy

import ru.btelepov.rickandmorty.R
import ru.btelepov.rickandmorty.databinding.ModelLoadingBinding


class LoadingEpoxyModel : ViewBindingKotlinModel<ModelLoadingBinding>(R.layout.model_loading) {

    override fun ModelLoadingBinding.bind() {
        // nothing to do
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}