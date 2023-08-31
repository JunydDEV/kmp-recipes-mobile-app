package com.kmp.recipes.mobile.app.common.data

import kotlinx.serialization.Serializable


@Serializable
data class CategoriesData(
    val id: String,
    val name: String,
    val categoriesList: List<Category>
)

@Serializable
class Category {

}
