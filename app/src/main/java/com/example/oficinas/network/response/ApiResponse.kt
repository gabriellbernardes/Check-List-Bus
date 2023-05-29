package com.example.oficinas.network.response

data class ApiResponse(
	val id: String,
	val response: Boolean? = null,
	val responseOptions: ArrayList<String>? = null,
	val responseOption: String? = null,
	val text: String? = null,
	val commentText: String? = null
)