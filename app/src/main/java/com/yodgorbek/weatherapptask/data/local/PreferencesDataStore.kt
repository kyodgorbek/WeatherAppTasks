package com.yodgorbek.weatherapptask.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "weather_prefs")

open class PreferencesDataStore(private val context: Context?) {
    companion object {
        private val KEY_LAST_CITY = stringPreferencesKey("last_city")
    }

    open suspend fun saveLastCity(cityName: String) {
        context?.dataStore?.edit { preferences ->
            preferences[KEY_LAST_CITY] = cityName
        }
    }

    open val lastCity: Flow<String> =
        context?.dataStore?.data
            ?.map { preferences -> preferences[KEY_LAST_CITY] ?: "London" }
            ?: MutableStateFlow("London")
}
