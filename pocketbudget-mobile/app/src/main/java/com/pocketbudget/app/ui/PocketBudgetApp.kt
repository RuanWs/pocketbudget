package com.pocketbudget.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pocketbudget.app.ui.components.SummaryCards
import com.pocketbudget.app.ui.components.TransactionForm
import com.pocketbudget.app.ui.components.TransactionList
import com.pocketbudget.app.ui.theme.Slate100
import com.pocketbudget.app.ui.theme.Slate500
import com.pocketbudget.app.ui.theme.Slate900
import com.pocketbudget.app.viewmodel.TransactionViewModel

@Composable
fun PocketBudgetApp(viewModel: TransactionViewModel = viewModel()) {
    val transactions = viewModel.transactions.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate100)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text("PocketBudget", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Slate900)
        Text(
            "Acompanhe o que entra e o que sai.",
            fontSize = 14.sp,
            color = Slate500,
            modifier = Modifier.padding(top = 2.dp, bottom = 16.dp)
        )

        SummaryCards(
            netBalance = viewModel.netBalance,
            totalIncome = viewModel.totalIncome,
            totalExpenses = viewModel.totalExpenses
        )

        Spacer(Modifier.height(20.dp))

        TransactionForm(onAddTransaction = viewModel::addTransaction)

        Spacer(Modifier.height(20.dp))

        TransactionList(
            transactions = transactions,
            onDelete = viewModel::deleteTransaction
        )
    }
}