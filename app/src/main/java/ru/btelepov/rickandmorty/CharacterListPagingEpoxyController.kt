package ru.btelepov.rickandmorty

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.squareup.picasso.Picasso
import ru.btelepov.rickandmorty.databinding.ModelCharacterListItemBinding
import ru.btelepov.rickandmorty.databinding.ModelCharacterListTitleBinding
import ru.btelepov.rickandmorty.epoxy.LoadingEpoxyModel
import ru.btelepov.rickandmorty.epoxy.ViewBindingKotlinModel
import ru.btelepov.rickandmorty.models.responses.GetCharacterByIdResponse

class CharacterListPagingEpoxyController(private val onCharacterSelected: (Int) -> Unit) :
    PagedListEpoxyController<GetCharacterByIdResponse>() {

    override fun buildItemModel(
        currentPosition: Int,
        item: GetCharacterByIdResponse?
    ): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(
            id = item!!.id,
            image = item.image,

            name = item.name,
            onCharacterSelected = onCharacterSelected
        ).id(item.id)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        if (models.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        CharacterGridTitleEpoxyModel("Main Family").id("main_family_header").addTo(this)
        super.addModels(models.subList(0, 5))

        (models.subList(5, models.size) as List<CharacterGridItemEpoxyModel>).groupBy {
            it.name[0].toUpperCase()
        }.forEach {
            val character = it.key.toString().toUpperCase()
            CharacterGridTitleEpoxyModel(character).id(character).addTo(this)

            super.addModels(it.value)
        }

    }


    data class CharacterGridItemEpoxyModel(
        val id: Int,
        val image: String,
        val name: String,
        val onCharacterSelected: (Int) -> Unit
    ) : ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item) {
        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(image).into(characterImageView)
            characterNameTextView.text = name
            root.setOnClickListener {
                onCharacterSelected(id)
            }
        }
    }

    data class CharacterGridTitleEpoxyModel(
        val title: String
    ) : ViewBindingKotlinModel<ModelCharacterListTitleBinding>(R.layout.model_character_list_title) {


        override fun ModelCharacterListTitleBinding.bind() {
            textView.text = title
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }
}