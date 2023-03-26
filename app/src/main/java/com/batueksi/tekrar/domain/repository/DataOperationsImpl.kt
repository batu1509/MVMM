package com.batueksi.tekrar.domain.repository

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.batueksi.tekrar.helper.Constants
import com.batueksi.tekrar.helper.Constants.UI_MODE_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject


class DataOperationsImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreOperations {


    private object PreferencesKey {
        val uiModeKey = intPreferencesKey(UI_MODE_KEY)
    }


    override suspend fun updateUIMode(uiMode: Int) {
        dataStore.edit {
            it[PreferencesKey.uiModeKey] = uiMode
        }
    }

    override fun getUIMode(): Flow<Int> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {
            val uiMode = it[PreferencesKey.uiModeKey] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            uiMode
        }
    }

    override suspend fun saveLoginInfo(uid: String) {
        val key = stringPreferencesKey(Constants.LOGIN_KEY)
        dataStore.edit {
            it[key] = uid
        }
    }

    override fun getLoginInfo(): Flow<String?> {
        val key = stringPreferencesKey(Constants.LOGIN_KEY)
        return dataStore.data.map {
            it[key]
        }
    }

    override suspend fun deleteLoginInfo() {
        val key = stringPreferencesKey(Constants.LOGIN_KEY)
        dataStore.edit {
            it[key] = ""
        }
    }


}

