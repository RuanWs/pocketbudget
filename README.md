# PocketBudget - Controle de Finanças Pessoais

O **PocketBudget** é uma aplicação de controle de finanças pessoais desenvolvida com uma abordagem **offline-first**, permitindo que o usuário registre e acompanhe suas receitas e despesas sem depender de conexão com a internet.

O projeto possui duas aplicações independentes:

* **Aplicação Web:** desenvolvida com React e Vite.
* **Aplicação Mobile:** desenvolvida com React Native e Expo.

Cada plataforma possui seu próprio mecanismo de armazenamento local. A aplicação Web utiliza **LocalStorage**, enquanto a aplicação Mobile utiliza **AsyncStorage**.

Este projeto foi desenvolvido como parte da disciplina de **Frameworks Front-End e Desenvolvimento Mobile**.

---

## Informações do Projeto


* **Arquitetura:** Monorepo com aplicações Web e Mobile
* **Aplicação Web:** React + Vite
* **Aplicação Mobile:** React Native + Expo
* **Armazenamento Web:** LocalStorage
* **Armazenamento Mobile:** AsyncStorage

---

## Equipe

| Nome | Matrícula |
| :--- | :--- |
| **[Nome do Integrante 1]** | [Matrícula] |
| **[Nome do Integrante 2]** | [Matrícula] |
| **[Nome do Integrante 3]** | [Matrícula] |
| **[Nome do Integrante 4]** | [Matrícula] |
| **[Nome do Integrante 5]** | [Matrícula] |
| **[Nome do Integrante 6]** | [Matrícula] |
| **[Nome do Integrante 7]** | [Matrícula] |
| **[Nome do Integrante 8]** | [Matrícula] |

---
---

## Conceito do Projeto

O PocketBudget foi desenvolvido para solucionar um problema simples: **permitir que o usuário controle suas finanças pessoais de maneira rápida, simples e independente de conexão com a internet.**

A aplicação permite:

* Cadastrar receitas e despesas;
* Editar transações;
* Excluir transações;
* Pesquisar transações;
* Filtrar transações;
* Visualizar o saldo financeiro;
* Visualizar um resumo das movimentações;
* Armazenar os dados localmente;
* Utilizar as funcionalidades principais sem conexão com a internet.

As versões Web e Mobile seguem o mesmo conceito de gerenciamento financeiro, mas possuem interfaces adaptadas para cada plataforma.

---

## Arquitetura do Sistema

O PocketBudget utiliza uma arquitetura **client-side e offline-first**.

Não existe um servidor backend nem um banco de dados remoto. Cada aplicação armazena seus próprios dados localmente.

```text
                         +---------------------------+
                         |        PocketBudget       |
                         |     Repositório Monorepo  |
                         +-------------+-------------+
                                       |
                    +------------------+------------------+
                    |                                     |
                    v                                     v
          +----------------------+              +----------------------+
          | pocketbudget-web/    |              | pocketbudget-mobile/ |
          |    React + Vite      |              | React Native + Expo  |
          +----------+-----------+              +----------+-----------+
                     |                                     |
                     v                                     v
          +----------------------+              +----------------------+
          |     LocalStorage     |              |     AsyncStorage      |
          | Armazenamento Web    |              | Armazenamento Mobile  |
          +----------------------+              +----------------------+
                     |                                     |
                     +------------------+------------------+
                                        |
                                        v
                         +---------------------------+
                         |        Sem Backend        |
                         |     Sem Banco de Dados    |
                         |      Funcionamento Offline|
                         +---------------------------+
```

### Persistência dos Dados

#### Aplicação Web

A aplicação Web utiliza a API **LocalStorage** fornecida pelo navegador.

```text
Aplicação React
      |
      v
LocalStorage
      |
      v
Armazenamento do navegador
```

As transações são armazenadas localmente no navegador do usuário.

#### Aplicação Mobile

A aplicação Mobile utiliza **AsyncStorage** para armazenar os dados localmente no dispositivo.

```text
Aplicação React Native
        |
        v
   AsyncStorage
        |
        v
Armazenamento do dispositivo
```

Dessa forma, as funcionalidades principais do sistema não dependem de um servidor externo ou de uma conexão com a internet.

---

## Funcionalidades

### Aplicação Web

* Dashboard financeiro;
* Cadastro de receitas e despesas;
* Edição de transações;
* Exclusão de transações;
* Pesquisa de transações;
* Filtros;
* Visualização do saldo;
* Resumo financeiro;
* Armazenamento local;
* Exportação de dados em JSON.

### Aplicação Mobile

* Cadastro de receitas e despesas;
* Visualização de transações;
* Pesquisa de transações;
* Filtros;
* Cards de resumo financeiro;
* Armazenamento local;
* Funcionamento offline.

---

## Destaques Técnicos

### Front-end Web

A aplicação Web foi desenvolvida utilizando **React** com **Vite**.

A arquitetura baseada em componentes permite dividir a interface em partes reutilizáveis, facilitando a manutenção e a organização do código.

Principais tecnologias:

* React;
* Vite;
* JavaScript;
* LocalStorage;
* HTML;
* CSS.

### Aplicação Mobile

A aplicação Mobile foi desenvolvida utilizando **React Native** e **Expo**.

A estrutura baseada em componentes permite criar uma interface adaptada para dispositivos móveis, mantendo uma organização semelhante à aplicação Web.

Principais tecnologias:

* React Native;
* Expo;
* JavaScript;
* AsyncStorage.

### Camada de Armazenamento

O sistema utiliza armazenamento local em ambas as plataformas:

| Plataforma | Tecnologia   | Local dos dados        |
| :--------- | :----------- | :--------------------- |
| Web        | LocalStorage | Navegador do usuário   |
| Mobile     | AsyncStorage | Dispositivo do usuário |

---

## Estrutura do Repositório

O projeto utiliza um **Monorepo**, ou seja, um único repositório contendo as duas aplicações, além da documentação acadêmica.

```text
pocketbudget/
│
├── README.md
│
├── docs/
│   └── relatorio.tex
│
├── pocketbudget-web/
│   ├── src/
│   │   ├── components/
│   │   │   ├── Summary/
│   │   │   ├── ExpenseForm/
│   │   │   └── ExpenseList/
│   │   │
│   │   └── App.jsx
│   │
│   ├── package.json
│   └── ...
│
└── pocketbudget-mobile/
    ├── components/
    │   ├── SummaryCards/
    │   └── TransactionForm/
    │
    ├── App.js
    ├── package.json
    └── ...
```

### Descrição das Pastas

| Pasta / Arquivo                    | Descrição                                     |
| :--------------------------------- | :-------------------------------------------- |
| `README.md`                        | Documentação principal do projeto             |
| `docs/`                            | Documentação acadêmica                        |
| `relatorio.tex`                    | Relatório desenvolvido em LaTeX               |
| `pocketbudget-web/`                | Aplicação Web                                 |
| `pocketbudget-web/src/`            | Código-fonte da aplicação Web                 |
| `pocketbudget-web/src/components/` | Componentes reutilizáveis da aplicação Web    |
| `pocketbudget-mobile/`             | Aplicação Mobile                              |
| `pocketbudget-mobile/components/`  | Componentes reutilizáveis da aplicação Mobile |

---

## Instalação e Execução

### Pré-requisitos

Para executar o projeto, é necessário ter instalado:

* **Node.js**
* **npm**
* **Expo**, para desenvolvimento da aplicação Mobile

Para testar a aplicação Mobile em um dispositivo físico, também pode ser utilizado o **Expo Go**.

---

## Executando a Aplicação Web

Primeiro, entre na pasta da aplicação Web:

```bash
cd pocketbudget-web
```

Instale as dependências:

```bash
npm install
```

Execute o servidor de desenvolvimento:

```bash
npm run dev
```

O Vite exibirá no terminal o endereço local para acessar a aplicação.

---

## Executando a Aplicação Mobile

Entre na pasta da aplicação Mobile:

```bash
cd pocketbudget-mobile
```

Instale as dependências:

```bash
npm install
```

Inicie o servidor do Expo:

```bash
npx expo start
```

Após iniciar o Expo, utilize uma das opções disponibilizadas para executar a aplicação em um dispositivo ou ambiente de desenvolvimento compatível.

---

## Vantagens da Arquitetura

A arquitetura adotada pelo PocketBudget oferece algumas vantagens:

* **Funcionamento offline:** as principais funcionalidades não dependem de internet.
* **Armazenamento local:** os dados permanecem armazenados na plataforma utilizada pelo usuário.
* **Simplicidade:** não existe necessidade de configurar um servidor backend.
* **Baixo custo de infraestrutura:** não há servidor ou banco de dados remoto para manter.
* **Separação das aplicações:** Web e Mobile possuem estruturas e dependências próprias.
* **Adaptação por plataforma:** cada aplicação pode ter uma interface adequada ao seu ambiente.

---

## Tecnologias Utilizadas

### Web

* React
* Vite
* JavaScript
* LocalStorage
* HTML
* CSS

### Mobile

* React Native
* Expo
* JavaScript
* AsyncStorage

### Documentação e Versionamento

* Markdown
* LaTeX
* Git
* GitHub

---

## Status do Projeto

**Status:** Em desenvolvimento.

O PocketBudget atualmente possui duas aplicações independentes, Web e Mobile, que implementam o gerenciamento de finanças pessoais utilizando armazenamento local e funcionamento offline.

---
