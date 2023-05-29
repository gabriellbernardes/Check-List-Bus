package com.example.oficinas.activities.check

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.oficinas.R.*
import com.example.oficinas.activities.MainActivity2
import com.example.oficinas.network.models.data.Check
import com.example.oficinas.network.models.data.TypeEnum
import com.example.oficinas.network.models.data.types.BoolSelectType
import com.example.oficinas.network.models.data.types.BoolTextType
import com.example.oficinas.network.models.data.types.BoolType
import com.example.oficinas.network.models.data.types.MultiSelectType
import com.example.oficinas.network.models.data.types.NumberType
import com.example.oficinas.network.models.data.types.SelectType
import com.example.oficinas.network.models.data.types.TextType
import com.example.oficinas.network.models.data.types.TitleType

class CheckViewAdapter(
	context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
	var mCheckData: MutableList<Check> = ArrayList()
	private val titles= HashMap<String, String>()
	val ctx = context

	// inflates the cell layout from xml when needed
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val inflater = LayoutInflater.from(parent.context)

		return when (viewType) {
			TypeEnum.text.ordinal -> {
			val view =  inflater.inflate(layout.item_check_text, parent, false)
			ViewHolderTextType(view)
			}
			TypeEnum.number.ordinal -> {
				val view =  inflater.inflate(layout.item_check_number, parent, false)
				ViewHolderNumberType(view)
			}
			TypeEnum.bool.ordinal -> {
				val view =  inflater.inflate(layout.item_check_bool, parent, false)
				ViewHolderBoolType(view)
			}
			TypeEnum.boolText.ordinal -> {
				val view =  inflater.inflate(layout.item_check_bool_text, parent, false)
				ViewHolderBoolTextType(view)
			}
			TypeEnum.boolSelect.ordinal -> {
				val view =  inflater.inflate(layout.item_check_bool_select, parent, false)
				ViewHolderBoolSelectType(view)
			}
			TypeEnum.multiSelect.ordinal -> {
				val view =  inflater.inflate(layout.item_check_multi_select, parent, false)
				ViewHolderMultiSelectType(view)
			}
			TypeEnum.select.ordinal -> {
				val view =  inflater.inflate(layout.item_check_select, parent, false)
				ViewHolderSelectType(view)
			}
			TypeEnum.title.ordinal -> {
				val view =  inflater.inflate(layout.loading_item, parent, false)
				ViewHolderTitleType(view)
			}
			else -> {
			val view = inflater.inflate(layout.loading_item, parent, false)
			object : RecyclerView.ViewHolder(view) {}
		}
		}

	}

	override fun getItemViewType(position: Int): Int {
		val item = mCheckData[position]

		val typeClass = item.type.run {
			when (this) {
				TypeEnum.title -> TitleType(item.id, item.type.name, item.text)
				TypeEnum.text -> TextType(item.id, item.required, item.text, item.type.name)
				TypeEnum.bool -> BoolType(item.id, item.required, item.text, item.type.name)
				TypeEnum.number -> NumberType(item.id, item.required, item.text, item.type.name)
				TypeEnum.select -> SelectType(item.id, item.required, item.text, item.type.name,  item.options)
				TypeEnum.multiSelect -> MultiSelectType(item.id, item.required, item.text, item.type.name,  item.options)
				TypeEnum.boolSelect -> BoolSelectType(item.id, item.required, item.text, item.type.name,item.commentText,  item.options)
				TypeEnum.boolText -> BoolTextType(item.id, item.required, item.text, item.type.name, item.commentText)
			}
		}

		// Determina o tipo do item com base na sua classe
		return when (typeClass) {
			is TitleType -> TypeEnum.title.ordinal
			is TextType -> TypeEnum.text.ordinal
			is BoolType -> TypeEnum.bool.ordinal
			is NumberType -> TypeEnum.number.ordinal
			is SelectType -> TypeEnum.select.ordinal
			is MultiSelectType -> TypeEnum.multiSelect.ordinal
			is BoolSelectType -> TypeEnum.boolSelect.ordinal
			is BoolTextType -> TypeEnum.boolText.ordinal
			else -> -1
		}
	}

	// binds the data to the TextView in each cell
	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		val item = mCheckData[position]
		// Faça o bind dos dados específicos do item com base no tipo
		when (holder) {
			is ViewHolderTextType -> {
				val response = TextType(item.id, item.required, item.text, item.type.name).apply {

					holder.titleText.run {
							this.text = titles[item.id?.substring(0, 2)]
					}
					holder.textCheck.text = this.text
					holder.requiredImgText.visibility = if ( this.required )  View.VISIBLE else View.GONE
				}

				val hashMap = LinkedHashMap<String, Any?>()
				holder.saveButton.setOnClickListener {
					if(holder.etTextCheck.text.trim().isEmpty() && response.required) {
						holder.etTextCheck.error = "Valor Obrigatorio"
						holder.etTextCheck.requestFocus()
					} else{
						hashMap["id"] = response.id
						hashMap["text"] = holder.etTextCheck.text.toString()
						MainActivity2.addListResponse(hashMap)
					}

				}

			}

			is  ViewHolderNumberType-> {
				val response = NumberType(item.id, item.required, item.text, item.type.name).apply {
					holder.titleText.run {
						this.text = titles[item.id?.substring(0, 2)]
					}
					holder.textCheck.text = this.text
					holder.requiredImgText.visibility = if ( this.required )  View.VISIBLE else View.GONE
				}

				val hashMap = LinkedHashMap<String, Any?>()
				holder.saveButton.setOnClickListener {
					if(holder.etTextCheck.text.trim().isEmpty() && response.required) {
							holder.etTextCheck.error = "Valor Obrigatorio"
							holder.etTextCheck.requestFocus()
					} else{
						hashMap["id"] = response.id
						hashMap["text"] = holder.etTextCheck.text.toString()
						MainActivity2.addListResponse(hashMap)
					}

				}

			}

			is ViewHolderBoolType -> {
				val response = BoolType(item.id, item.required, item.text, item.type.name).apply {
					holder.titleText.run {
						this.text = titles[item.id?.substring(0, 2)]
					}
					holder.textCheck.text = this.text
					holder.requiredImgText.visibility = if ( this.required )  View.VISIBLE else View.GONE
					holder.switchBool.setOnCheckedChangeListener{_, isChecked ->
						if( isChecked){
							holder.switchBool.text = "Sim"
						}else{
							holder.switchBool.text = "Não"
						}

					}
				}

				val hashMap = LinkedHashMap<String, Any?>()
				holder.saveButton.setOnClickListener {
					var switchAnswered = false
					holder.switchBool.setOnCheckedChangeListener { _, _ ->
						switchAnswered = true
					}

					if(!switchAnswered && response.required) {
						holder.switchBool.error = "Valor Obrigatorio"
						holder.switchBool.requestFocus()
					} else{
						hashMap["id"] = response.id
						hashMap["response"] = holder.switchBool.isChecked
						MainActivity2.addListResponse(hashMap)
					}

				}
			}

			is ViewHolderBoolTextType -> {
				val response = BoolTextType(
					item.id,
					item.required,
					item.text,
					item.type.name,
					item.commentText
				).apply {
					holder.titleText.run {
						this.text = titles[item.id?.substring(0, 2)]
					}
					holder.textCheck.text = this.text
					holder.etTextCheck.hint = this.commentText

					holder.requiredImgText.visibility =
						if (this.required) View.VISIBLE else View.GONE
					holder.etTextCheck.visibility = View.GONE

					holder.switchBoolText.setOnCheckedChangeListener { _, isChecked ->
						if (isChecked) {
							holder.switchBoolText.text = "Sim"
							holder.etTextCheck.visibility = View.GONE
						} else {
							holder.switchBoolText.text = "Não"
							holder.etTextCheck.visibility = View.VISIBLE
						}
					}

				}


				val hashMap = LinkedHashMap<String, Any?>()
				var switchAnswered = false
				holder.switchBoolText.setOnCheckedChangeListener { _, _ ->
					switchAnswered = true
				}

					holder.saveButton.setOnClickListener {

						if (!switchAnswered && response.required) {
							holder.switchBoolText.error = "Valor Obrigatorio"
							holder.switchBoolText.requestFocus()
						}
						else {
						if (holder.switchBoolText.isChecked) {
							hashMap["id"] = response.id
							hashMap["response"] = holder.switchBoolText.isChecked
							hashMap["commentText"] = ""
							MainActivity2.addListResponse(hashMap)
						} else {
							if (holder.etTextCheck.text.trim().isEmpty() && response.required) {
								holder.etTextCheck.error = "Valor Obrigatorio"
								holder.etTextCheck.requestFocus()
							} else {
								hashMap["id"] = response.id
								hashMap["response"] = holder.switchBoolText.isChecked
								hashMap["commentText"] = holder.etTextCheck.text.toString()
								MainActivity2.addListResponse(hashMap)
							}
						}

					}
				}
			}

			is ViewHolderBoolSelectType -> {
				 val response = BoolSelectType(item.id, item.required, item.commentText, item.text, item.type.name,  item.options).apply {
					 holder.titleText.run {
						 this.text = titles[item.id?.substring(0, 2)]
					 }
					 holder.textCheck.text = this.text
					 holder.requiredImgText.visibility = if ( this.required )  View.VISIBLE else View.GONE
					 holder.etTextCheck.hint = this.commentText
					 holder.etTextCheck.visibility = View.VISIBLE

					 holder.rgBoolSelect.removeAllViews()

					 val rbCopy1 = RadioButton(ctx)
					 rbCopy1.text = "Não Respondido"
					 holder.rgBoolSelect.addView(rbCopy1)

					 for(option in item.options!!) {
						val rbCopy = RadioButton(ctx)
						 rbCopy.text = option.text
						holder.rgBoolSelect.addView(rbCopy)
					 }
					 var select = false
					 rbCopy1.setOnCheckedChangeListener{ _, check ->
						 select = check
					 }

					 holder.rgBoolSelect.setOnCheckedChangeListener { _, _ ->
						 if(select) {
							 holder.etTextCheck.visibility = View.VISIBLE
						 }else {
							 holder.etTextCheck.visibility = View.GONE
						 }
					 }
				}



				val hashMap = LinkedHashMap<String, Any?>()
				holder.saveButton.setOnClickListener {
						var selectedValues = ""
						for (i in 0 until holder.rgBoolSelect.childCount) {
							val radioButton = holder.rgBoolSelect.getChildAt(i) as RadioButton
							if (radioButton.isChecked) {
								val value = radioButton.text.toString()
								selectedValues = value
							}
						}

						if ((selectedValues == "Não Respondido") || (selectedValues == "")) {
							if (holder.etTextCheck.text.trim().isEmpty() && response.required) {
								holder.etTextCheck.error = "Valor Obrigatorio"
								holder.etTextCheck.requestFocus()
							}else {
								hashMap["id"] = response.id
								hashMap["responseOption"] = selectedValues
								hashMap["commentText"] = holder.etTextCheck.text.toString()
							}
						}
						else {
								hashMap["id"] = response.id
								hashMap["responseOption"] = selectedValues
								hashMap["commentText"] = ""
								MainActivity2.addListResponse(hashMap)
						}
				}
			}


			is ViewHolderMultiSelectType -> {
				MultiSelectType(item.id, item.required, item.text, item.type.name,  item.options).apply {
					holder.titleText.run {
						this.text = titles[item.id?.substring(0, 2)]
					}
					holder.textCheck.text = this.text
					holder.requiredImgText.visibility = if ( this.required )  View.VISIBLE else View.GONE

					val newTextList= item.options!!.map { it.text }.toMutableList()
					holder.cbMultiSelect.removeAllViewsInLayout()
					for (string in newTextList) {
						val checkBox = CheckBox(ctx)
						checkBox.text = string
						holder.cbMultiSelect.addView(checkBox)
					}

				}


			}

			is ViewHolderSelectType -> {
				SelectType(item.id, item.required, item.text, item.type.name,  item.options).apply {
					holder.titleText.run {
						this.text = titles[item.id?.substring(0, 2)]
					}
					holder.textCheck.text = this.text
					holder.requiredImgText.visibility = if ( this.required )  View.VISIBLE else View.GONE
					val newTextList= item.options!!.map { it.text }.toMutableList()

					val adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item,newTextList )
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
					holder.spSelect.adapter = adapter
				}

			}

			is ViewHolderTitleType -> {
				titles[item.id!!] = item.text!!
				holder.title.text = item.text
			}

		}
	}


	@SuppressLint("NotifyDataSetChanged")
	fun add(userBean: Check) {
		mCheckData.add(userBean)
		notifyDataSetChanged()
	}

	@SuppressLint("NotifyDataSetChanged")
	fun addAll(checkList: ArrayList<*>?) {
		mCheckData.clear()
		if (checkList != null) {
			for (k in checkList) {
				mCheckData.add(k as Check)
			}
		}
		notifyDataSetChanged()
	}


	// total number of cells
	override fun getItemCount(): Int {
		return mCheckData.size
	}



	inner class ViewHolderTextType (itemView: View) : RecyclerView.ViewHolder(itemView),
		View.OnClickListener {

		val titleText: TextView = itemView.findViewById(id.titleText)
		val etTextCheck: EditText = itemView.findViewById(id.editTextCheck)
		val textCheck: TextView = itemView.findViewById(id.textCheck)
		val requiredImgText: ImageView = itemView.findViewById(id.requiredImgText)
		val saveButton:Button = itemView.findViewById(id.saveButton)


		@SuppressLint("NotifyDataSetChanged")
		override fun onClick(view: View) {}



		init {
			itemView.setOnClickListener(this)
		}
	}
	inner class ViewHolderNumberType (itemView: View) : RecyclerView.ViewHolder(itemView),
		View.OnClickListener {

		val titleText: TextView = itemView.findViewById(id.titleNumber)
		val etTextCheck: EditText = itemView.findViewById(id.editTextCheckNumber)
		val textCheck: TextView = itemView.findViewById(id.textCheckNumber)
		val requiredImgText: ImageView = itemView.findViewById(id.requiredImgNumber)
		val saveButton:Button = itemView.findViewById(id.saveButton)


		@SuppressLint("NotifyDataSetChanged")
		override fun onClick(view: View) {}



		init {
			itemView.setOnClickListener(this)
		}
	}
	inner class ViewHolderBoolType (itemView: View) : RecyclerView.ViewHolder(itemView),
		View.OnClickListener {

		val titleText: TextView = itemView.findViewById(id.titleBool)
		val switchBool: SwitchCompat = itemView.findViewById(id.scBool)
		val textCheck: TextView = itemView.findViewById(id.textCheckBool)
		val requiredImgText: ImageView = itemView.findViewById(id.requiredImgBool)
		val saveButton:Button = itemView.findViewById(id.saveButton)


		@SuppressLint("NotifyDataSetChanged")
		override fun onClick(view: View) {}



		init {
			itemView.setOnClickListener(this)
		}
	}
	inner class ViewHolderSelectType (itemView: View) : RecyclerView.ViewHolder(itemView),
		View.OnClickListener {

		val titleText: TextView = itemView.findViewById(id.titleSelect)
		val spSelect: Spinner = itemView.findViewById(id.sppinerCheckSelect)
		val textCheck: TextView = itemView.findViewById(id.textCheckSelect)
		val requiredImgText: ImageView = itemView.findViewById(id.requiredImgSelect)
		val saveButton:Button = itemView.findViewById(id.saveButton)


		@SuppressLint("NotifyDataSetChanged")
		override fun onClick(view: View) {}



		init {
			itemView.setOnClickListener(this)
		}
	}
	inner class ViewHolderBoolTextType (itemView: View) : RecyclerView.ViewHolder(itemView),
		View.OnClickListener {

		val titleText: TextView = itemView.findViewById(id.titleBoolText)
		val etTextCheck: EditText = itemView.findViewById(id.editBoolText)
		val switchBoolText: SwitchCompat = itemView.findViewById(id.scBoolText)
		val textCheck: TextView = itemView.findViewById(id.checkBoolText)
		val requiredImgText: ImageView = itemView.findViewById(id.requiredImgBoolText)
		val saveButton:Button = itemView.findViewById(id.saveButton)


		@SuppressLint("NotifyDataSetChanged")
		override fun onClick(view: View) {}



		init {
			itemView.setOnClickListener(this)
		}
	}
	inner class ViewHolderMultiSelectType (itemView: View) : RecyclerView.ViewHolder(itemView),
		View.OnClickListener {

		val titleText: TextView = itemView.findViewById(id.titleMultiSelect)
		val textCheck: TextView = itemView.findViewById(id.textMultiSelect)
		val requiredImgText: ImageView = itemView.findViewById(id.requiredImgMultiSelect)
		val cbMultiSelect: LinearLayout = itemView.findViewById(id.cbMultiSelect)
		val saveButton:Button = itemView.findViewById(id.saveButton)


		@SuppressLint("NotifyDataSetChanged")
		override fun onClick(view: View) {}



		init {
			itemView.setOnClickListener(this)
		}
	}
	inner class ViewHolderBoolSelectType (itemView: View) : RecyclerView.ViewHolder(itemView),
		View.OnClickListener {

		val titleText: TextView = itemView.findViewById(id.titleBoolSelect)
		val etTextCheck: EditText = itemView.findViewById(id.editBoolSelectText)
		val textCheck: TextView = itemView.findViewById(id.boolSelectText)
		val requiredImgText: ImageView = itemView.findViewById(id.requiredImgBoolSelect)
		val rgBoolSelect: RadioGroup = itemView.findViewById(id.radioGroupBoolSelect)
		val rbBoolSelect: RadioButton = itemView.findViewById(id.radioButtonBoolSelect)
		val saveButton:Button = itemView.findViewById(id.saveButton)



		@SuppressLint("NotifyDataSetChanged")
		override fun onClick(view: View) {}



		init {
			itemView.setOnClickListener(this)
		}
	}

	inner class ViewHolderTitleType (itemView: View) : RecyclerView.ViewHolder(itemView),
		View.OnClickListener {

		val title: TextView = itemView.findViewById(id.defaulText)


		@SuppressLint("NotifyDataSetChanged")
		override fun onClick(view: View) {}



		init {
			itemView.setOnClickListener(this)
		}
	}

}


