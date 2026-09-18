package com.pocketbudget.app.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pocketbudget.app.model.Transaction

private const val PREFS_NAME = "pocketbudget_prefs"
private const val STORAGE_KEY = "transactions"

class TransactionRepository(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun loadTransactions(): List<Transaction> {
        val json = prefs.getString(STORAGE_KEY, null) ?: return emptyList()

        return try {
            val listType = object : TypeToken<List<Transaction>>() {}.type
            val parsed: List<Transaction>? = gson.fromJson(json, listType)
            parsed ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun saveTransactions(transactions: List<Transaction>) {
        try {
            val json = gson.toJson(transactions)
            prefs.edit().putString(STORAGE_KEY, json).apply()
        } catch (e: Exception) {
            // Falha ao salvar é tratada silenciosamente, sem derrubar o app,
            // assim como no tratamento defensivo da versão original.
        }
    }
}