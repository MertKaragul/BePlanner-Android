package com.mertkaragul.beplanner.Viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertkaragul.beplanner.Model.DatabaseModel.DatabasePlanModel
import com.mertkaragul.beplanner.Service.Database.DatabaseUtils.database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _plansLiveData = MutableLiveData<MutableList<DatabasePlanModel>>(mutableListOf())
    val plansLiveData = _plansLiveData

    fun getPlans(){
        viewModelScope.launch {
            _plansLiveData.value = database?.plannerDAO()?.getAll()
        }
    }

}