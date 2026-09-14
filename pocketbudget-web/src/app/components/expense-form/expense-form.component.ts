import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TransactionService } from '../../services/transaction.service';
import {
  CATEGORIES,
  CategoryOption,
  TransactionCategory,
  TransactionType,
} from '../../models/transaction.model';

interface FormErrors {
  description?: string;
  amount?: string;
  category?: string;
  type?: string;
}

@Component({
  selector: 'app-expense-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './expense-form.component.html',
  styleUrl: './expense-form.component.scss',
})
export class ExpenseFormComponent {
  private readonly transactionService = inject(TransactionService);

  readonly categories: CategoryOption[] = CATEGORIES;

  description = '';
  amount = '';
  category: TransactionCategory | '' = '';
  type: TransactionType | '' = '';
  errors: FormErrors = {};

  onSubmit(): void {
    if (!this.validate()) {
      return;
    }

    this.transactionService.addTransaction(
      this.description.trim(),
      Number(this.amount),
      this.category as TransactionCategory,
      this.type as TransactionType
    );

    this.resetForm();
  }

  private validate(): boolean {
    const nextErrors: FormErrors = {};

    if (!this.description.trim()) {
      nextErrors.description = 'A descrição é obrigatória.';
    }

    const numericAmount = Number(this.amount);
    if (!this.amount || Number.isNaN(numericAmount) || numericAmount <= 0) {
      nextErrors.amount = 'O valor deve ser maior que zero.';
    }

    if (!this.category) {
      nextErrors.category = 'Selecione uma categoria.';
    }

    if (!this.type) {
      nextErrors.type = 'Selecione um tipo.';
    }

    this.errors = nextErrors;
    return Object.keys(nextErrors).length === 0;
  }

  private resetForm(): void {
    this.description = '';
    this.amount = '';
    this.category = '';
    this.type = '';
    this.errors = {};
  }
}
