package com.beibeilab.bubblesample.ui.bubble

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BubbleViewModel : ViewModel() {

    var apiEndpointItems = MutableLiveData<List<ApiEndpointItem>>()

    fun loadData() {
        viewModelScope.launch {
            apiEndpointItems.value = mutableListOf<ApiEndpointItem>().apply {
                add(ApiEndpointItem("category", 10))
                add(ApiEndpointItem("me", 10))
                add(ApiEndpointItem("feed", 10))
            }
        }
    }
}