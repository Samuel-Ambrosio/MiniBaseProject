package com.ghrisav.minibaseproject.common.extensions

import androidx.lifecycle.MutableLiveData
import com.ghrisav.minibaseproject.common.utils.Event
import kotlinx.coroutines.flow.*

suspend fun <T> Flow<T>.execute(
    data: MutableLiveData<T>,
    loadingMutableLiveData: MutableLiveData<Event<Boolean>>? = null,
    errorMutableLiveData: MutableLiveData<Event<String>>? = null
) {
    this.onStart { loadingMutableLiveData?.postValue(Event(true)) }
        .onCompletion { loadingMutableLiveData?.postValue(Event(false)) }
        .catch { t -> errorMutableLiveData?.postValue(Event(t.message)) }
        .collect { dataFlow -> data.postValue(dataFlow) }
}