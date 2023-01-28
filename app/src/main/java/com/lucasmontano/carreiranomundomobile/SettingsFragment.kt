package com.lucasmontano.carreiranomundomobile

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.lucasmontano.carreiranomundomobile.core.datastore.SettingsDataStore

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = SettingsDataStore(requireContext())
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)
    }
}