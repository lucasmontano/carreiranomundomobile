package com.lucasmontano.carreiranomundomobile.core.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

data class SettingsPreferences(
    val allowNotification: Boolean,
    val showCompleted: Boolean,
    val sendEmailReminder: Boolean,
    val reminderEmailAddress: String
)

class SettingsPreferencesRepository(private val dataStore: DataStore<Preferences>) {

    private val TAG: String = "SettingsPreferencesRepo"

    object SettingsKeys {
        val ALLOW_NOTIFICATION = booleanPreferencesKey("allow_notification")
        val SHOW_COMPLETED = booleanPreferencesKey("show_completed")
        val SEND_EMAIL_REMINDER = booleanPreferencesKey("send_email_reminder")
        val REMINDER_EMAIL_ADDDR = stringPreferencesKey("reminder_email_addr")
    }

    val settingsFlow: Flow<SettingsPreferences> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading settings.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences -> mapSettings(preferences) }

    suspend fun fetchInitialSettings() =
        mapSettings(dataStore.data.first().toPreferences())

    private fun mapSettings(preferences: Preferences): SettingsPreferences {
        val allowNotification = preferences[SettingsKeys.ALLOW_NOTIFICATION] ?: false
        val showCompleted = preferences[SettingsKeys.SHOW_COMPLETED] ?: false
        val sendEmailReminder = preferences[SettingsKeys.SEND_EMAIL_REMINDER] ?: false
        val reminderEmailAddress = preferences[SettingsKeys.REMINDER_EMAIL_ADDDR] ?: ""

        return SettingsPreferences(
            allowNotification,
            showCompleted,
            sendEmailReminder,
            reminderEmailAddress
        )
    }
}