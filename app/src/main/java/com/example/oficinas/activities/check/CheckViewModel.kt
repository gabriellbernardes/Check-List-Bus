package com.example.oficinas.activities.check

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oficinas.network.models.data.Check

class CheckViewModel: ViewModel() {
	val list: MutableLiveData<ArrayList<Check>> by lazy {
		MutableLiveData<ArrayList<Check>>()
	}
}