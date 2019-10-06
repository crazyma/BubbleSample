package com.beibeilab.bubblesample.ui.bubble

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BubbleViewModel : ViewModel() {

    var apiEndpointItems = MutableLiveData<List<ApiEndpointItem>>()
    var apiOperatorItems = MutableLiveData<List<ApiOperationItem>>()

    fun loadApiEndpoint() {
        viewModelScope.launch {
            apiEndpointItems.value = mutableListOf<ApiEndpointItem>().apply {
                add(ApiEndpointItem("category", 10))
                add(ApiEndpointItem("me", 10))
                add(ApiEndpointItem("feed", 10))
            }
        }
    }

    fun loadOperation(){
        viewModelScope.launch {
            apiOperatorItems.value = mutableListOf<ApiOperationItem>().apply {
                add(ApiOperationItem("category", "op 1",10))
                add(ApiOperationItem("category", "op 2",10))
                add(ApiOperationItem("category", "op 3",10))
                add(ApiOperationItem("category", "op 4",10))
            }
        }
    }
}