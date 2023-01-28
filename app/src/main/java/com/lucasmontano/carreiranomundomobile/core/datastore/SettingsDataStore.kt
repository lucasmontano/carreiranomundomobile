package com.lucasmontano.carreiranomundomobile.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsDataStore(context: Context) : PreferenceDataStore() {

    private val dataStore = context.dataStore
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun putString(key: String?, value: String?) {
        scope.launch {
            dataStore.edit { prefs ->
                prefs[stringPreferencesKey(key!!)] = value.toString()
            }
        }
    }

    override fun getString(key: String?, defValue: String?): String {
        return runBlocking { dataStore.data.first()[stringPreferencesKey(key!!)] ?: "" as String }
    }

    override fun putBoolean(key: String?, value: Boolean) {
        scope.launch {
            dataStore.edit { prefs ->
                prefs[booleanPreferencesKey(key!!)] = value
            }
        }
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return runBlocking {
            dataStore.data.first()[booleanPreferencesKey(key!!)] ?: defValue as Boolean
        }
    }
}