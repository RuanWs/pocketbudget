# PocketBudget

Aplicação web de controle de finanças pessoais desenvolvida com Angular.

O PocketBudget permite registrar receitas e despesas, consultar o saldo atual, pesquisar transações e filtrá-las por categoria.

A aplicação funciona como uma SPA (Single Page Application) e utiliza o LocalStorage do navegador para armazenar os dados. Não possui backend, API ou banco de dados.

## Tecnologias

* Angular
* TypeScript
* HTML
* SCSS
* LocalStorage

## Requisitos

Para executar o projeto, é necessário ter instalado:

* Node.js 18 ou superior
* npm
* Angular CLI

Verifique as versões instaladas:

```bash
node -v
npm -v
ng version
```

Caso o Angular CLI não esteja instalado:

```bash
npm install -g @angular/cli
```

## Instalação

Clone o repositório e entre na pasta do projeto:

```bash
git clone https://github.com/RuanWs/pocketbudget.git
cd pocketbudget-web
```

Instale as dependências:

```bash
npm install
```

## Executando o projeto

Inicie o servidor de desenvolvimento:

```bash
ng serve
```

Depois, acesse:

```text
http://localhost:4200
```

A aplicação será atualizada automaticamente sempre que os arquivos forem alterados.

Para encerrar o servidor:

```text
Ctrl + C
```

## Funcionalidades

* Cadastro de receitas e despesas
* Exclusão de transações
* Pesquisa por descrição
* Filtro por categoria
* Cálculo automático do saldo líquido
* Exibição do total de receitas
* Exibição do total de despesas
* Validação dos campos do formulário
* Formatação de valores em Real (BRL)
* Formatação de datas no padrão brasileiro
* Interface responsiva para desktop e dispositivos móveis

### Categorias

As transações podem ser classificadas nas seguintes categorias:

* Alimentação
* Transporte
* Contas
* Entretenimento
* Outros

## Armazenamento dos dados

As transações são armazenadas no LocalStorage do navegador utilizando a chave:

```text
transactions
```

Isso significa que:

* Os dados continuam disponíveis após recarregar a página.
* Os dados ficam armazenados apenas no navegador e dispositivo utilizados.
* Não existe sincronização entre dispositivos.
* Limpar os dados do site no navegador remove as transações armazenadas.
* O modo de navegação anônima/privada possui armazenamento separado.

## Estrutura do projeto

```text
pocketbudget-web/
│
├── src/
│   ├── app/
│   │   ├── components/
│   │   │   ├── summary/
│   │   │   ├── expense-form/
│   │   │   └── expense-list/
│   │   │
│   │   ├── models/
│   │   │   └── transaction.model.ts
│   │   │
│   │   ├── services/
│   │   │   └── transaction.service.ts
│   │   │
│   │   ├── utils/
│   │   │   └── format.util.ts
│   │   │
│   │   ├── app.ts
│   │   ├── app.html
│   │   ├── app.scss
│   │   └── app.config.ts
│   │
│   ├── index.html
│   ├── main.ts
│   └── styles.scss
│
├── angular.json
├── package.json
├── tsconfig.json
└── README.md
```

### Organização

**components/**
Contém os componentes responsáveis pela interface da aplicação.

**models/**
Define os modelos e tipos utilizados pelo sistema, como `Transaction` e suas categorias.

**services/**
Contém o `TransactionService`, responsável pelo gerenciamento das transações, cálculos e armazenamento no LocalStorage.

**utils/**
Contém funções auxiliares, como formatação de moeda e data.

## Build de produção

Para gerar a versão de produção:

```bash
ng build
```

Os arquivos gerados ficarão dentro da pasta:

```text
dist/
```

A aplicação pode ser publicada em serviços de hospedagem de aplicações estáticas, pois não depende de um backend.

## Solução de problemas

### `Cannot find module '../../services/transaction.service'`

Verifique se o arquivo existe em:

```text
src/app/services/transaction.service.ts
```

Também confirme se o nome do arquivo e sua capitalização estão corretos.

### `Could not resolve "./app/app"`

Verifique se o componente raiz está utilizando a estrutura atual do Angular:

```text
app.ts
app.html
app.scss
```

Também confirme se o `main.ts` está importando corretamente o componente `App`.

### As alterações não aparecem no navegador

O `ng serve` normalmente atualiza a aplicação automaticamente.

Caso isso não aconteça:

1. Salve o arquivo novamente.
2. Atualize o navegador com `Ctrl + Shift + R`.
3. Se necessário, reinicie o servidor:

```bash
ng serve
```

## Licença

Projeto desenvolvido para fins acadêmicos e educacionais.
