package com.pocketbudget.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pocketbudget.app.ui.theme.*
import com.pocketbudget.app.util.formatCurrency

@Composable
fun SummaryCards(netBalance: Double, totalIncome: Double, totalExpenses: Double) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SummaryCard(
            label = "Saldo líquido",
            value = formatCurrency(netBalance),
            backgroundColor = Violet50,
            borderColor = Violet200,
            labelColor = Violet700,
            valueColor = if (netBalance >= 0) Slate900 else Rose600
        )

        SummaryCard(
            label = "Receita total",
            value = formatCurrency(totalIncome),
            backgroundColor = Emerald50,
            borderColor = Emerald200,
            labelColor = Emerald700,
            valueColor = Emerald700
        )

        SummaryCard(
            label = "Despesas totais",
            value = formatCurrency(totalExpenses),
            backgroundColor = Rose50,
            borderColor = Rose200,
            labelColor = Rose700,
            valueColor = Rose700
        )
    }
}

@Composable
private fun SummaryCard(
    label: String,
    value: String,
    backgroundColor: Color,
    borderColor: Color,
    labelColor: Color,
    valueColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(text = label, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = labelColor)
        Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = valueColor,
            modifier = Modifier.padding(top = 6.dp))
    }
}