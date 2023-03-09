package com.example.loginwithretrofitinmvvmarchitecture.data.network

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserPreferences(
    context: Context
) {

    private val applicationContext = context.applicationContext//getting the application context with the help of context
    private val dataStore = context.createDataStore(name = "my_data_store")

    val authToken : Flow<String?>//for retrieving the value from the data store
    get() = dataStore.data.map {  preferences ->
            preferences[KEY_AUTH]//to retrieve the value we need to provide the key
        }//this getter function will give us the saved auth token

    suspend fun saveAuthToken(authToken : String) {
        dataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken//our auth token is saved
        }

        }
        //this function will save our auth token to data store


        companion object {
            private val KEY_AUTH = preferencesKey<String>("key_auth")//as we r saving the auth token in key-value pairs so this is the key name as we r using preferences data store
        }

    }









