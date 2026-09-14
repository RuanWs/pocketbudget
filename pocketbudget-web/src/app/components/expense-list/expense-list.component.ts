import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TransactionService } from '../../services/transaction.service';
import {
  CATEGORIES,
  CATEGORY_LABELS,
  CategoryOption,
  Transaction,
  TransactionCategory,
} from '../../models/transaction.model';
import { formatCurrency, formatDate } from '../../utils/format.util';

@Component({
  selector: 'app-expense-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './expense-list.component.html',
  styleUrl: './expense-list.component.scss',
})
export class ExpenseListComponent {
  private readonly transactionService = inject(TransactionService);

  readonly categories: CategoryOption[] = CATEGORIES;

  searchTerm = '';
  categoryFilter: TransactionCategory | '' = '';

  get allTransactions(): Transaction[] {
    return this.transactionService.transactions();
  }

  get filteredTransactions(): Transaction[] {
    return this.allTransactions.filter((t) => {
      const matchesSearch = t.description
        .toLowerCase()
        .includes(this.searchTerm.toLowerCase());

      const matchesCategory = this.categoryFilter
        ? t.category === this.categoryFilter
        : true;

      return matchesSearch && matchesCategory;
    });
  }

  get emptyMessage(): string {
    return this.allTransactions.length === 0
      ? 'Nenhuma transação ainda. Adicione sua primeira transação para começar.'
      : 'Nenhuma transação corresponde à sua pesquisa ou filtro.';
  }

  getCategoryLabel(category: TransactionCategory): string {
    return CATEGORY_LABELS[category] ?? category;
  }

  formatCurrency(value: number): string {
    return formatCurrency(value);
  }

  formatDate(isoString: string): string {
    return formatDate(isoString);
  }

  onDelete(id: number): void {
    this.transactionService.deleteTransaction(id);
  }
}
