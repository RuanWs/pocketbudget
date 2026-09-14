import { Component } from '@angular/core';
import { SummaryComponent } from './components/summary/summary.component';
import { ExpenseFormComponent } from './components/expense-form/expense-form.component';
import { ExpenseListComponent } from './components/expense-list/expense-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [SummaryComponent, ExpenseFormComponent, ExpenseListComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {}
