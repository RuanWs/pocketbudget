package com.pocketbudget.app.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.pocketbudget.app.data.TransactionRepository
import com.pocketbudget.app.model.Transaction
import com.pocketbudget.app.util.nowAsIsoString
import kotlin.math.abs

class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TransactionRepository(application)

    private val _transactions = mutableStateOf<List<Transaction>>(emptyList())
    val transactions: State<List<Transaction>> = _transactions

    init {
        // Carrega os dados salvos assim que o ViewModel é criado.
        _transactions.value = repository.loadTransactions()
    }

    val totalIncome: Double
        get() = _transactions.value.filter { it.type == "income" }.sumOf { it.amount }

    val totalExpenses: Double
        get() = _transactions.value.filter { it.type == "expense" }.sumOf { it.amount }

    val netBalance: Double
        get() = totalIncome - totalExpenses

    fun addTransaction(description: String, amount: Double, category: String, type: String) {
        val newTransaction = Transaction(
            id = System.currentTimeMillis(),
            description = description.trim(),
            amount = abs(amount),
            category = category,
            type = type,
            date = nowAsIsoString()
        )

        _transactions.value = listOf(newTransaction) + _transactions.value
        repository.saveTransactions(_transactions.value)
    }

    fun deleteTransaction(id: Long) {
        _transactions.value = _transactions.value.filter { it.id != id }
        repository.saveTransactions(_transactions.value)
    }
}