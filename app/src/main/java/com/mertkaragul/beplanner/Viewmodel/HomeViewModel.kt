package com.mertkaragul.beplanner.Viewmodel

import androidx.lifecycle.LiveData
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
    val plansLiveData = MutableLiveData<MutableList<DatabasePlanModel>>(mutableListOf())

    init {
        getPlans()
    }

    fun getPlans(){
        viewModelScope.launch {
            plansLiveData.value?.clear()
            plansLiveData.value = database?.plannerDAO()?.getAll()
        }
    }

    fun deletePlan(uuid : Int){
        viewModelScope.launch {
            val dao = database?.plannerDAO()
            dao?.getById(uuid)?.let {
                dao.delete(it)
                plansLiveData.value?.remove(it)
            }
        }
    }
}