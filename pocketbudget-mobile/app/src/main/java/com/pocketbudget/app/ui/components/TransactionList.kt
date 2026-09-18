package com.pocketbudget.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pocketbudget.app.model.Categories
import com.pocketbudget.app.model.Transaction
import com.pocketbudget.app.ui.theme.*
import com.pocketbudget.app.util.formatCurrency
import com.pocketbudget.app.util.formatDate

@Composable
fun TransactionList(transactions: List<Transaction>, onDelete: (Long) -> Unit) {
    var searchTerm by remember { mutableStateOf("") }
    var categoryFilter by remember { mutableStateOf("") }

    val filteredTransactions = remember(transactions, searchTerm, categoryFilter) {
        transactions.filter { t ->
            val matchesSearch = t.description.lowercase().contains(searchTerm.lowercase())
            val matchesCategory = categoryFilter.isEmpty() || t.category == categoryFilter
            matchesSearch && matchesCategory
        }
    }

    val emptyMessage = if (transactions.isEmpty()) {
        "Nenhuma transação ainda. Adicione sua primeira transação para começar."
    } else {
        "Nenhuma transação corresponde à sua pesquisa ou filtro."
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(White)
            .border(1.dp, Slate200, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text("Transações", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Slate900)
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = searchTerm,
            onValueChange = { searchTerm = it },
            placeholder = { Text("Pesquisar por descrição") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                FilterChip(
                    label = "Todas as categorias",
                    selected = categoryFilter.isEmpty(),
                    onClick = { categoryFilter = "" }
                )
            }
            items(Categories.options) { option ->
                FilterChip(
                    label = option.label,
                    selected = categoryFilter == option.value,
                    onClick = { categoryFilter = option.value }
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        if (filteredTransactions.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Slate300, RoundedCornerShape(12.dp))
                    .padding(vertical = 32.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(emptyMessage, color = Slate500, fontSize = 14.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
        } else {
            Column {
                filteredTransactions.forEachIndexed { index, transaction ->
                    TransactionRow(transaction = transaction, onDelete = onDelete)
                    if (index != filteredTransactions.lastIndex) {
                        Divider(color = Slate200, thickness = 1.dp)
                    }
                }
            }
        }
    }
}

@Composable
private fun FilterChip(label: String, selected: Boolean, onClick: () -> Unit) {
    val background = if (selected) Violet600 else White
    val border = if (selected) Violet600 else Slate300
    val textColor = if (selected) White else Slate700

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(background)
            .border(1.dp, border, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(label, fontSize = 13.sp, fontWeight = FontWeight.Medium, color = textColor)
    }
}

@Composable
private fun TransactionRow(transaction: Transaction, onDelete: (Long) -> Unit) {
    val isIncome = transaction.type == "income"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f).padding(end = 10.dp)) {
            Text(
                transaction.description,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Slate900,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "${Categories.labelFor(transaction.category)} · ${formatDate(transaction.date)}",
                fontSize = 12.sp,
                color = Slate500,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = (if (isIncome) "+ " else "- ") + formatCurrency(transaction.amount),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (isIncome) Emerald700 else Rose700
            )
            Spacer(Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Slate200, RoundedCornerShape(8.dp))
                    .clickable { onDelete(transaction.id) }
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text("Excluir", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Slate500)
            }
        }
    }
}