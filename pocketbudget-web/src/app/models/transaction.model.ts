export type TransactionType = 'income' | 'expense';

export type TransactionCategory =
  | 'Food'
  | 'Transport'
  | 'Utilities'
  | 'Entertainment'
  | 'Others';

export interface Transaction {
  id: number;
  description: string;
  amount: number;
  category: TransactionCategory;
  type: TransactionType;
  date: string;
}

export interface CategoryOption {
  value: TransactionCategory;
  label: string;
}

export const CATEGORIES: CategoryOption[] = [
  { value: 'Food', label: 'Alimentação' },
  { value: 'Transport', label: 'Transporte' },
  { value: 'Utilities', label: 'Contas' },
  { value: 'Entertainment', label: 'Entretenimento' },
  { value: 'Others', label: 'Outros' },
];

export const CATEGORY_LABELS: Record<TransactionCategory, string> = {
  Food: 'Alimentação',
  Transport: 'Transporte',
  Utilities: 'Contas',
  Entertainment: 'Entretenimento',
  Others: 'Outros',
};
