package com.example.oficinas.network.models.data

data class Check(
    val type: TypeEnum,
    val id: String?,
    val required: Boolean ,
    val text: String?,
    val commentText: String?,
    val options: ArrayList<Option>?
)

