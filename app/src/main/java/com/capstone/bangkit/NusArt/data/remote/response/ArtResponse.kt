package com.capstone.bangkit.NusArt.data.remote.response

import com.google.gson.annotations.SerializedName

data class ArtResponse(

	@field:SerializedName("error_message")
	val errorMessage: String? = null,

	@field:SerializedName("data")
	val data: List<ListArtItem> = emptyList(),

	@field:SerializedName("error")
	val error: Boolean? = null


)

data class ListArtItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("artist")
	val artist: String? = null,

	@field:SerializedName("era")
	val era: String? = null,

	@field:SerializedName("genre")
	val genre: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)
