package com.vipul.parentuneassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipul.parentuneassignment.models.PlansDataModel
import com.vipul.parentuneassignment.repository.PlansRepository
import com.vipul.parentuneassignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlansViewModel @Inject constructor(private val repository: PlansRepository) : ViewModel() {

    private val _plans = MutableLiveData<Resource<PlansDataModel>>()
    val plans: LiveData<Resource<PlansDataModel>>
        get() = _plans


    fun getPlans() = viewModelScope.launch {
        _plans.value = repository.getPlans()
    }


}