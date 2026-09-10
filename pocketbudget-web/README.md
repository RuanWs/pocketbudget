# PocketBudget

A simple personal finance tracker built as a single-page React application. Add income and expense transactions, search and filter them, and see your net balance update in real time. All data is stored locally in the browser — there is no backend or database.

## Tech Stack

- React 18
- Vite 8
- Tailwind CSS v4
- Browser LocalStorage (no backend, no database, no API)

## Requirements

- Node.js 18 or later (any recent LTS version works)
- npm (comes bundled with Node.js)

Check your versions before starting:

```bash
node -v
npm -v
```

## Project Structure

```text
pocketbudget-web/
│
├── src/
│   ├── components/
│   │   ├── Summary.jsx        # Net balance / income / expense cards
│   │   ├── ExpenseForm.jsx    # Add-transaction form with validation
│   │   └── ExpenseList.jsx    # Transaction list, search, category filter
│   │
│   ├── App.jsx                # Main component, state, localStorage sync
│   ├── main.jsx                # React entry point
│   └── index.css               # Tailwind import + base styles
│
├── index.html
├── package.json
├── vite.config.js
├── postcss.config.js
└── README.md
```

Note: Tailwind v4 does not use a `tailwind.config.js` file — content scanning is automatic, so there is no separate config file to maintain.

## Installation

Clone or download the project, then from the project's root folder:

```bash
npm install
```

This installs everything listed in `package.json`, including:

**Dependencies**
- `react`
- `react-dom`

**Dev dependencies**
- `vite`
- `@vitejs/plugin-react`
- `tailwindcss`
- `@tailwindcss/postcss`
- `postcss`
- `autoprefixer`

If you're setting this project up from scratch rather than from an existing `package.json`, run these instead:

```bash
npm create vite@latest pocketbudget-web -- --template react
cd pocketbudget-web
npm install
npm install react react-dom
npm install -D tailwindcss @tailwindcss/postcss postcss autoprefixer
```

## Running the App (Development)

```bash
npm run dev
```

This starts the Vite dev server. Open the URL shown in the terminal: