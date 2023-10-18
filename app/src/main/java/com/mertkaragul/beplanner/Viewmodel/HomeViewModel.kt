package com.mertkaragul.beplanner.Viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertkaragul.beplanner.Model.DatabaseModel.DatabasePlanModel
import com.mertkaragul.beplanner.Service.Database.DatabaseUtils.database
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

    fun clearAllRemindedPlans(){
        if (plansLiveData.value.isNullOrEmpty()) return
        viewModelScope.launch {
            val dao = database?.plannerDAO()
            plansLiveData.value
                ?.filter { it.status }
                ?.map {
                    dao?.delete(it)
                }
        }
    }
}