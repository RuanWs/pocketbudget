package com.pocketbudget.app.model

data class Transaction(
    val id: Long,
    val description: String,
    val amount: Double,
    val category: String,
    val type: String,
    val date: String
)

data class CategoryOption(
    val value: String,
    val label: String
)

object Categories {
    val options = listOf(
        CategoryOption("Food", "Alimentação"),
        CategoryOption("Transport", "Transporte"),
        CategoryOption("Utilities", "Contas"),
        CategoryOption("Entertainment", "Entretenimento"),
        CategoryOption("Others", "Outros")
    )

    fun labelFor(value: String): String {
        return options.find { it.value == value }?.label ?: value
    }
}