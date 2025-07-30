package models

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name")
    val name: String,

    @SerializedName("sprites")
    val sprites: Sprites,

    @SerializedName("weight")
    val weight: Int,

    @SerializedName("height")
    val height: Int,

    @SerializedName("types")
    val types: List<TypeSlot>
)

data class Sprites(
    @SerializedName("front_default")
    val front_default: String
)

data class TypeSlot(
    @SerializedName("type")
    val type: Type
)

data class Type(
    @SerializedName("name")
    val name: String
)