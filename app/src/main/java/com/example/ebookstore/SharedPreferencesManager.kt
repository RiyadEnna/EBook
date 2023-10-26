package com.example.ebookstore

import android.content.Context
import android.util.Log
import com.google.gson.Gson

class SharedPreferencesManager {
    companion object {
        const val bookListKey = "bookListKey"
        const val searchKey = "searchKey"
        val preferencesFile = "preferencesFile"
    }

    fun saveSearchCriteria(search:String, context: Context) {
        val sharedPreferences = context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .putString(searchKey, search)
            .apply()
    }

    fun getSearchCriteria(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE)
        return sharedPreferences.getString(searchKey,"")
    }

    fun getLocalBookStorage(context: Context): LocalBookStorage {
        val sharedPreferences = context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE)
        val gson = Gson()
        val json: String? = sharedPreferences.getString(bookListKey, "")
        if (json.isNullOrEmpty()) {
            return LocalBookStorage(mutableListOf())
        }
        return gson.fromJson(json, LocalBookStorage::class.java)
    }

    fun saveBook(book: Items, context: Context):Boolean {
        var localBookStorage = getLocalBookStorage(context)
        if (localBookStorage.localBooks.contains(book)) {
            return false
        }
        localBookStorage.localBooks.add(book)
        Log.d("saveBook", " size "+localBookStorage.localBooks.size)
        val sharedPreferences = context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(localBookStorage)
        sharedPreferences.edit().putString(bookListKey, json).apply()
        return true
    }
    fun deleteBook(book: Items, context: Context) {
        var localBookStorage = getLocalBookStorage(context)
        localBookStorage.localBooks.remove(book)
        val sharedPreferences = context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(localBookStorage)
        sharedPreferences.edit().putString(bookListKey, json).apply()
    }
}