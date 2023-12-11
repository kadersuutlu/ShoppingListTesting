package com.kader.shoppinglisttesting.repositories

import androidx.lifecycle.LiveData
import com.kader.shoppinglisttesting.data.local.ShoppingItem
import com.kader.shoppinglisttesting.other.Resource
import com.kader.shoppinglisttesting.data.remote.responses.ImageResponse

interface ShoppingRepository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>
    fun observeTotalPrice(): LiveData<Float>
    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}