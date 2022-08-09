package com.example.pizzamax.model.relation

data class CategoryAndItems(

    var categoryId: Int = 0,
    var name: String? = null,
    var categoryItemsId: Int? = null,
    var id: Int = 0,
    var imgUrl: String? = null,
    var size: String? = null,
    var price: String? = null
)
