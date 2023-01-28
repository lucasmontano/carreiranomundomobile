package com.lucasmontano.carreiranomundomobile

import android.content.Context
import android.os.Bundle
import androidx.datastore.preferences.preferencesDataStore
import androidx.preference.PreferenceFragmentCompat
import com.lucasmontano.carreiranomundomobile.core.datastore.SettingsDataStore

private val Context.dataStore by preferencesDataStore(name = "settings`")

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        preferenceManager.preferenceDataStore = SettingsDataStore(Context.dataStore)
    }
}