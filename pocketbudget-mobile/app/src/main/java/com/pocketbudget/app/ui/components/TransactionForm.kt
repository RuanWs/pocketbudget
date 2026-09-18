package com.pocketbudget.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pocketbudget.app.model.Categories
import com.pocketbudget.app.ui.theme.*
import androidx.compose.foundation.clickable

@Composable
fun TransactionForm(onAddTransaction: (String, Double, String, String) -> Unit) {
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var errors by remember { mutableStateOf(mapOf<String, String>()) }

    fun validate(): Boolean {
        val newErrors = mutableMapOf<String, String>()

        if (description.trim().isEmpty()) {
            newErrors["description"] = "A descrição é obrigatória."
        }

        val normalizedAmount = amount.replace(",", ".")
        val numericAmount = normalizedAmount.toDoubleOrNull()
        if (amount.isEmpty() || numericAmount == null || numericAmount <= 0.0) {
            newErrors["amount"] = "O valor deve ser maior que zero."
        }

        if (category.isEmpty()) {
            newErrors["category"] = "Selecione uma categoria."
        }

        if (type.isEmpty()) {
            newErrors["type"] = "Selecione um tipo."
        }

        errors = newErrors
        return newErrors.isEmpty()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(White)
            .border(1.dp, Slate200, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text("Adicionar transação", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Slate900)
        Spacer(Modifier.height(14.dp))

        FormField(label = "Descrição", error = errors["description"]) {
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                placeholder = { Text("Compras, salário, aluguel...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }

        Spacer(Modifier.height(14.dp))

        FormField(label = "Valor", error = errors["amount"]) {
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                placeholder = { Text("0,00") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }

        Spacer(Modifier.height(14.dp))

        FormField(label = "Categoria", error = errors["category"]) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(Categories.options) { option ->
                    ChoiceChip(
                        label = option.label,
                        selected = category == option.value,
                        selectedBackground = Violet600,
                        selectedBorder = Violet600,
                        onClick = { category = option.value }
                    )
                }
            }
        }

        Spacer(Modifier.height(14.dp))

        FormField(label = "Tipo", error = errors["type"]) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                TypeButton(
                    label = "Receita",
                    selected = type == "income",
                    activeBackground = Emerald50,
                    activeBorder = Emerald500,
                    modifier = Modifier.weight(1f),
                    onClick = { type = "income" }
                )
                TypeButton(
                    label = "Despesa",
                    selected = type == "expense",
                    activeBackground = Rose50,
                    activeBorder = Rose500,
                    modifier = Modifier.weight(1f),
                    onClick = { type = "expense" }
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        Button(
            onClick = {
                if (validate()) {
                    val numericAmount = amount.replace(",", ".").toDoubleOrNull() ?: 0.0
                    onAddTransaction(description, numericAmount, category, type)

                    description = ""
                    amount = ""
                    category = ""
                    type = ""
                    errors = emptyMap()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Violet600)
        ) {
            Text("Adicionar transação", color = White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun FormField(label: String, error: String?, content: @Composable () -> Unit) {
    Column {
        Text(label, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Slate700,
            modifier = Modifier.padding(bottom = 6.dp))
        content()
        if (error != null) {
            Text(error, color = Rose600, fontSize = 13.sp, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Composable
private fun ChoiceChip(
    label: String,
    selected: Boolean,
    selectedBackground: Color,
    selectedBorder: Color,
    onClick: () -> Unit
) {
    val background = if (selected) selectedBackground else White
    val border = if (selected) selectedBorder else Slate300
    val textColor = if (selected) White else Slate700

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(background)
            .border(1.dp, border, RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 9.dp)
    ) {
        Text(label, fontSize = 13.sp, fontWeight = FontWeight.Medium, color = textColor)
    }
}

@Composable
private fun TypeButton(
    label: String,
    selected: Boolean,
    activeBackground: Color,
    activeBorder: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val background = if (selected) activeBackground else White
    val border = if (selected) activeBorder else Slate300

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(background)
            .border(1.dp, border, RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(label, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Slate900)
    }
}