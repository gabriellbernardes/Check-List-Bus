package com.example.oficinas.network.models.data.types

import com.example.oficinas.network.models.data.Option

data class MultiSelectType(
	val id:String?,
	val required:Boolean,
	val text:String?,
	val type:String?,
	val options:ArrayList<Option>?
)
