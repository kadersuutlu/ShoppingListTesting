package com.kader.shoppinglisttesting.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kader.shoppinglisttesting.data.local.ShoppingItem
import com.kader.shoppinglisttesting.data.remote.responses.ImageResponse
import com.kader.shoppinglisttesting.other.Constants
import com.kader.shoppinglisttesting.other.Event
import com.kader.shoppinglisttesting.other.Resource
import com.kader.shoppinglisttesting.repositories.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val repository: ShoppingRepository
) : ViewModel() {

    val shoppingItems = repository.observeAllShoppingItems()

    val totalPrice = repository.observeTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> = _images

    private val _curImageUrl = MutableLiveData<String>()
    val curImageUrl: LiveData<String> = _curImageUrl

    private val _insertShoppingITemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingITemStatus: LiveData<Event<Resource<ShoppingItem>>> =
        _insertShoppingITemStatus

    fun setCurlImageUrl(url: String) {
        _curImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name: String, amountString: String, priceString: String) {
        if(name.isEmpty() || amountString.isEmpty() || priceString.isEmpty()) {
            _insertShoppingITemStatus.postValue(Event(Resource.error("The fields must not be empty", null)))
            return
        }
        if(name.length > Constants.MAX_NAME_LENGTH) {
            _insertShoppingITemStatus.postValue(Event(Resource.error("The name of the item" +
                    "must not exceed ${Constants.MAX_NAME_LENGTH} characters", null)))
            return
        }
        if(priceString.length > Constants.MAX_PRICE_LENGTH) {
            _insertShoppingITemStatus.postValue(Event(Resource.error("The price of the item" +
                    "must not exceed ${Constants.MAX_PRICE_LENGTH} characters", null)))
            return
        }
        val amount = try {
            amountString.toInt()
        } catch(e: Exception) {
            _insertShoppingITemStatus.postValue(Event(Resource.error("Please enter a valid amount", null)))
            return
        }
        val shoppingItem = ShoppingItem(name, amount, priceString.toFloat(), _curImageUrl.value ?: "")
        insertShoppingItemIntoDb(shoppingItem)
        setCurlImageUrl("")
        _insertShoppingITemStatus.postValue(Event(Resource.success(shoppingItem)))
    }

    fun searchForImage(imageQuery: String) {
        if(imageQuery.isEmpty()) {
            return
        }
        _images.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.searchForImage(imageQuery)
            _images.value = Event(response)
        }
    }
}