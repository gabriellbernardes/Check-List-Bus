package com.example.oficinas.network.models.data.types

import com.example.oficinas.network.models.data.Option

data class BoolSelectType(
	val id:String?,
	val required:Boolean,
	val commentText:String?,
	val text:String?,
	val type:String?,
	val options:ArrayList<Option>?
)
