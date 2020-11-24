package com.ghrisav.minibaseproject.common.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.ghrisav.minibaseproject.common.extensions.toLiveData
import com.ghrisav.minibaseproject.common.utils.Event
import com.ghrisav.minibaseproject.common.utils.NavigationCommand
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel() {

    protected val fullScreenLoading = MutableLiveData<Event<Boolean>>()
    protected val snackBarError = MutableLiveData<Event<String>>()
    private val navigation = MutableLiveData<Event<NavigationCommand>>()
    private val requestsCompleted = MutableLiveData<List<Boolean>>()

    fun isLoadingFullScreen() = fullScreenLoading.toLiveData()
    fun isSnackBarError() = snackBarError.toLiveData()
    fun getNavigation() = navigation.toLiveData()
    fun getRequestsCompleted() = requestsCompleted.toLiveData()

    fun navigate(directions: NavDirections) {
        navigation.value = Event(NavigationCommand.To(directions))
    }

    fun navigateBack() {
        navigation.value = Event(NavigationCommand.Back)
    }

    fun setRequests(number: Int) {
        requestsCompleted.value  = List(number) { false }
    }

    fun setRequestCompleted(position: Int) {
        val listAux = requestsCompleted.value?.toMutableList()
        listAux?.let { listAux[position] = true }
        requestsCompleted.value = listAux?.toList()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}