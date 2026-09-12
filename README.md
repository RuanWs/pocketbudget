# PocketBudget - Controle de Finanças Pessoais

O **PocketBudget** é uma aplicação de controle de finanças pessoais desenvolvida com uma abordagem **offline-first**, permitindo que o usuário registre e acompanhe suas receitas e despesas sem depender de conexão com a internet.

O projeto possui duas aplicações independentes:

* **Aplicação Web:** desenvolvida com Angular e TypeScript.
* **Aplicação Mobile:** desenvolvida nativamente em Kotlin, com Jetpack Compose.

Cada plataforma possui seu próprio mecanismo de armazenamento local. A aplicação Web utiliza **LocalStorage**, enquanto a aplicação Mobile utiliza **SharedPreferences** (com serialização via Gson).

Este projeto foi desenvolvido como parte da disciplina de **Frameworks Front-End e Desenvolvimento Mobile**.



---

## Informações do Projeto

* **Arquitetura:** Monorepo com aplicações Web e Mobile
* **Aplicação Web:** Angular + TypeScript
* **Aplicação Mobile:** Kotlin + Jetpack Compose
* **Armazenamento Web:** LocalStorage
* **Armazenamento Mobile:** SharedPreferences (JSON via Gson)

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
* Excluir transações;
* Pesquisar transações;
* Filtrar transações por categoria;
* Visualizar o saldo financeiro;
* Visualizar um resumo das movimentações (receita total, despesas totais, saldo líquido);
* Armazenar os dados localmente;
* Utilizar todas as funcionalidades sem conexão com a internet.

As versões Web e Mobile seguem o mesmo conceito de gerenciamento financeiro e as mesmas regras de negócio, mas possuem interfaces e tecnologias adaptadas para cada plataforma.

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
          |  Angular + TypeScript|              |  Kotlin + Compose    |
          +----------+-----------+              +----------+-----------+
                     |                                     |
                     v                                     v
          +----------------------+              +----------------------+
          |     LocalStorage     |              |  SharedPreferences   |
          | Armazenamento Web    |              | Armazenamento Mobile |
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

A aplicação Web utiliza a API **LocalStorage** fornecida pelo navegador, acessada por meio de um `TransactionService` do Angular baseado em Signals.

```text
Aplicação Angular
      |
      v
TransactionService
      |
      v
LocalStorage
      |
      v
Armazenamento do navegador
```

As transações são armazenadas localmente no navegador do usuário.

#### Aplicação Mobile

A aplicação Mobile utiliza **SharedPreferences** para armazenar os dados localmente no dispositivo, com as transações serializadas em JSON por meio da biblioteca **Gson**.

```text
Aplicação Kotlin (Compose)
        |
        v
TransactionViewModel
        |
        v
TransactionRepository
        |
        v
SharedPreferences (JSON via Gson)
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
* Exclusão de transações;
* Pesquisa de transações por descrição;
* Filtro por categoria;
* Visualização do saldo líquido;
* Resumo financeiro (receita total e despesas totais);
* Armazenamento local (LocalStorage).

### Aplicação Mobile

* Cadastro de receitas e despesas;
* Visualização de transações;
* Exclusão de transações;
* Pesquisa de transações por descrição;
* Filtro por categoria;
* Cards de resumo financeiro (saldo líquido, receita total, despesas totais);
* Armazenamento local (SharedPreferences);
* Funcionamento totalmente offline.

---

## Destaques Técnicos

### Front-end Web

A aplicação Web foi desenvolvida utilizando **Angular** com **TypeScript**.

A arquitetura baseada em componentes standalone permite dividir a interface em partes reutilizáveis, com a lógica de negócio e a persistência centralizadas em um service dedicado (`TransactionService`), facilitando a manutenção e a organização do código.

Principais tecnologias:

* Angular;
* TypeScript;
* HTML;
* SCSS;
* LocalStorage.

### Aplicação Mobile

A aplicação Mobile foi desenvolvida nativamente em **Kotlin**, utilizando **Jetpack Compose** para a interface declarativa.

A estrutura é organizada em pacotes (`model`, `data`, `viewmodel`, `ui`), separando claramente o modelo de dados, a persistência, o estado da aplicação e os componentes visuais — mantendo uma organização conceitualmente semelhante à aplicação Web.

Principais tecnologias:

* Kotlin;
* Jetpack Compose;
* Android ViewModel;
* SharedPreferences + Gson.

### Camada de Armazenamento

O sistema utiliza armazenamento local em ambas as plataformas:

| Plataforma | Tecnologia         | Local dos dados        |
| :--------- | :------------------ | :---------------------- |
| Web        | LocalStorage         | Navegador do usuário    |
| Mobile     | SharedPreferences    | Dispositivo do usuário  |

---

## Estrutura do Repositório

O projeto utiliza um **Monorepo**, ou seja, um único repositório contendo as duas aplicações, além da documentação acadêmica.

```text
pocketbudget/
│
├── README.md
│
├── docs/
│   └── pocketbudget.tex
│
├── pocketbudget-web/
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/
│   │   │   │   ├── summary/
│   │   │   │   ├── expense-form/
│   │   │   │   └── expense-list/
│   │   │   ├── models/
│   │   │   ├── services/
│   │   │   ├── utils/
│   │   │   ├── app.ts
│   │   │   ├── app.html
│   │   │   └── app.scss
│   │   ├── index.html
│   │   ├── main.ts
│   │   └── styles.scss
│   ├── angular.json
│   ├── package.json
│   └── tsconfig.json
│
└── pocketbudget-mobile/
    ├── app/
    │   ├── build.gradle.kts
    │   └── src/main/
    │       ├── java/com/pocketbudget/app/
    │       │   ├── MainActivity.kt
    │       │   ├── model/
    │       │   ├── data/
    │       │   ├── viewmodel/
    │       │   ├── util/
    │       │   └── ui/
    │       │       ├── PocketBudgetApp.kt
    │       │       ├── components/
    │       │       └── theme/
    │       ├── res/
    │       └── AndroidManifest.xml
    ├── build.gradle.kts
    └── settings.gradle.kts
```

### Descrição das Pastas

| Pasta / Arquivo                              | Descrição                                          |
| :--------------------------------------------- | :--------------------------------------------------- |
| `README.md`                                    | Documentação principal do projeto                    |
| `docs/`                                        | Documentação acadêmica                               |
| `pocketbudget.tex`                             | Relatório/proposta desenvolvido em LaTeX             |
| `pocketbudget-web/`                            | Aplicação Web (Angular)                              |
| `pocketbudget-web/src/app/`                    | Código-fonte principal da aplicação Web              |
| `pocketbudget-web/src/app/components/`         | Componentes reutilizáveis da aplicação Web           |
| `pocketbudget-web/src/app/services/`           | Lógica de negócio e persistência (LocalStorage)      |
| `pocketbudget-mobile/`                         | Aplicação Mobile (Kotlin)                            |
| `pocketbudget-mobile/app/src/main/java/.../ui/components/` | Componentes reutilizáveis (Jetpack Compose) |
| `pocketbudget-mobile/app/src/main/java/.../viewmodel/`     | Estado e lógica de negócio da aplicação Mobile |
| `pocketbudget-mobile/app/src/main/java/.../data/`          | Persistência local (SharedPreferences) |

---

## Instalação e Execução

### Pré-requisitos

Para executar o projeto, é necessário ter instalado:

* **Node.js** e **npm**, para a aplicação Web (Angular)
* **Angular CLI** (`npm install -g @angular/cli`)
* **Android Studio**, para a aplicação Mobile (Kotlin)

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
ng serve
```

O Angular exibirá no terminal o endereço local para acessar a aplicação (`http://localhost:4200/`).

---

## Executando a Aplicação Mobile

A aplicação Mobile não utiliza terminal/npm — é um projeto Android nativo.

1. Abra o **Android Studio**.
2. Selecione **Open** e aponte para a pasta `pocketbudget-mobile/`.
3. Aguarde o Gradle sincronizar.
4. Conecte um celular Android via USB (com depuração ativada) ou inicie um emulador pelo **Device Manager**.
5. Clique em **▶️ Run 'app'**.

---

## Vantagens da Arquitetura

A arquitetura adotada pelo PocketBudget oferece algumas vantagens:

* **Funcionamento offline:** as funcionalidades não dependem de internet.
* **Armazenamento local:** os dados permanecem armazenados na plataforma utilizada pelo usuário.
* **Simplicidade:** não existe necessidade de configurar um servidor backend.
* **Baixo custo de infraestrutura:** não há servidor ou banco de dados remoto para manter.
* **Separação das aplicações:** Web e Mobile possuem estruturas, tecnologias e dependências próprias.
* **Tecnologias nativas por plataforma:** Angular para Web e Kotlin nativo para Mobile, aproveitando as ferramentas e práticas recomendadas de cada ecossistema.

---

## Tecnologias Utilizadas

### Web

* Angular
* TypeScript
* HTML
* SCSS
* LocalStorage

### Mobile

* Kotlin
* Jetpack Compose
* Android ViewModel
* SharedPreferences + Gson

### Documentação e Versionamento

* Markdown
* LaTeX
* Git
* GitHub

---

## Status do Projeto

**Status:** Em desenvolvimento.

O PocketBudget atualmente possui duas aplicações independentes, Web (Angular) e Mobile (Kotlin), que implementam o gerenciamento de finanças pessoais utilizando armazenamento local e funcionamento offline. Ambas as aplicações foram migradas de suas stacks originais (React/Vite e React Native/Expo) para atender aos requisitos atualizados do projeto, preservando as funcionalidades e o comportamento definidos inicialmente.

---
