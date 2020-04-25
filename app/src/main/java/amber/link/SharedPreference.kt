package amber.link


/*
* Created by Amber Yiyao Zhou & Link Zhou Fang on December 02, 2019
*/

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(private val context: Context = TheApp.context) {

    // region Properties
    private val preferencesName = context.getString(R.string.app_name) // use the app name
    private val sharedPref: SharedPreferences = context.getSharedPreferences(preferencesName,
        Context.MODE_PRIVATE)
// endregion

    // region Methods
    fun contains(KEY_NAME: String): Boolean{
        return sharedPref.contains(KEY_NAME)
    }

    fun removeValue(KEY_NAME: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.remove(KEY_NAME)
        editor.apply()
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }

    fun getAll(): Map<String, *>{
        return sharedPref.all
    }

// endregion

    // region Set methods
    fun save(KEY_NAME: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME, text)
        editor.apply()
    }

    fun save(KEY_NAME: String, number: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME, number)
        editor.apply()
    }

    fun save(KEY_NAME: String, number: Long) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putLong(KEY_NAME, number)
        editor.apply()
    }

    fun save(KEY_NAME: String, number: Float) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putFloat(KEY_NAME, number)
        editor.apply()
    }

    fun save(KEY_NAME: String, boolean: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_NAME, boolean)
        editor.apply()
    }

    fun save(KEY_NAME: String, stringSet: Set<String>) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putStringSet(KEY_NAME, stringSet)
        editor.apply()
    }

    // **** you need to add set methods for Int, Long, Float, Boolean and Set<String>

// endregion

    // region Get methods
    fun getValueString(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, null)
    }

    fun getValueInt(KEY_NAME: String): Int? {
        return sharedPref.getInt(KEY_NAME, -1)
    }

    fun getValueLong(KEY_NAME: String): Long? {
        return sharedPref.getLong(KEY_NAME, -1)
    }

    fun getValueFloat(KEY_NAME: String): Float? {
        return sharedPref.getFloat(KEY_NAME, -1.0f)
    }

    fun getValueBoolean(KEY_NAME: String): Boolean? {
        return sharedPref.getBoolean(KEY_NAME, false)
    }

    fun getValueStringSet(KEY_NAME: String): Set<String>? {
        return sharedPref.getStringSet(KEY_NAME, null)
    }

    
    // **** you need to add get methods for Int?, Long?, Float?, Boolean? and Set<String>?

// endregion

}