import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransactionService } from '../../services/transaction.service';
import { formatCurrency } from '../../utils/format.util';

@Component({
  selector: 'app-summary',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './summary.component.html',
  styleUrl: './summary.component.scss',
})
export class SummaryComponent {
  private readonly transactionService = inject(TransactionService);

  readonly netBalance = this.transactionService.netBalance;
  readonly totalIncome = this.transactionService.totalIncome;
  readonly totalExpenses = this.transactionService.totalExpenses;

  formatCurrency(value: number): string {
    return formatCurrency(value);
  }
}
