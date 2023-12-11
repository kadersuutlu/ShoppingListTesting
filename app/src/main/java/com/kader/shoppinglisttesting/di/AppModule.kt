package com.kader.shoppinglisttesting.di

import android.content.Context
import androidx.room.Room
import com.kader.shoppinglisttesting.data.local.ShoppingDao
import com.kader.shoppinglisttesting.data.local.ShoppingItemDatabase
import com.kader.shoppinglisttesting.other.Constants.BASE_URL
import com.kader.shoppinglisttesting.other.Constants.DATABASE_NAME
import com.kader.shoppinglisttesting.data.remote.PixabayAPI
import com.kader.shoppinglisttesting.repositories.DefaultShoppingRepository
import com.kader.shoppinglisttesting.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
//@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
        api: PixabayAPI
    ) = DefaultShoppingRepository(dao, api) as ShoppingRepository

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }
}