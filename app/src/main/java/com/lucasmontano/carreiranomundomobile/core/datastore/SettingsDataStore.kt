package com.lucasmontano.carreiranomundomobile.core.datastore

import androidx.datastore.core.DataStore
import androidx.preference.Preference
import androidx.preference.PreferenceDataStore

class SettingsDataStore(dataStore: DataStore<Preference>) : PreferenceDataStore() {

    override fun putString(key: String?, value: String?) = when (key) {
        else -> {}
    }

    override fun getString(key: String?, defValue: String?): String? {
        throw NotImplementedError()
    }

    override fun putBoolean(key: String?, value: Boolean) {
        //TODO
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        throw NotImplementedError()
    }
}