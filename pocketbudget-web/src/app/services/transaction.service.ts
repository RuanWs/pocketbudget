import { Injectable, computed, signal } from '@angular/core';
import {
  Transaction,
  TransactionCategory,
  TransactionType,
} from '../models/transaction.model';

const STORAGE_KEY = 'transactions';

@Injectable({ providedIn: 'root' })
export class TransactionService {
  private readonly transactionsSignal = signal<Transaction[]>(
    this.loadFromStorage()
  );

  readonly transactions = this.transactionsSignal.asReadonly();

  readonly totalIncome = computed(() =>
    this.transactionsSignal()
      .filter((t) => t.type === 'income')
      .reduce((sum, t) => sum + t.amount, 0)
  );

  readonly totalExpenses = computed(() =>
    this.transactionsSignal()
      .filter((t) => t.type === 'expense')
      .reduce((sum, t) => sum + t.amount, 0)
  );

  readonly netBalance = computed(
    () => this.totalIncome() - this.totalExpenses()
  );

  addTransaction(
    description: string,
    amount: number,
    category: TransactionCategory,
    type: TransactionType
  ): void {
    const newTransaction: Transaction = {
      id: Date.now(),
      description,
      amount: Math.abs(Number(amount)),
      category,
      type,
      date: new Date().toISOString(),
    };

    this.transactionsSignal.update((prev) => [newTransaction, ...prev]);
    this.saveToStorage();
  }

  deleteTransaction(id: number): void {
    this.transactionsSignal.update((prev) =>
      prev.filter((t) => t.id !== id)
    );
    this.saveToStorage();
  }

  private loadFromStorage(): Transaction[] {
    try {
      const stored = localStorage.getItem(STORAGE_KEY);

      if (stored) {
        const parsed = JSON.parse(stored);

        if (Array.isArray(parsed)) {
          return parsed;
        }
      }
    } catch (error) {
      console.error(
        'Falha ao carregar as transações do localStorage:',
        error
      );
    }

    return [];
  }

  private saveToStorage(): void {
    try {
      localStorage.setItem(
        STORAGE_KEY,
        JSON.stringify(this.transactionsSignal())
      );
    } catch (error) {
      console.error('Falha ao salvar as transações no localStorage:', error);
    }
  }
}
