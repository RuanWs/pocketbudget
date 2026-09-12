# PocketBudget Mobile

Aplicativo móvel nativo Android para controle de finanças pessoais, desenvolvido com **Kotlin** e **Jetpack Compose**.

O PocketBudget permite registrar receitas e despesas, consultar o saldo, pesquisar transações, filtrar por categoria e acompanhar os valores financeiros em tempo real.

A aplicação funciona de forma **offline**, armazenando os dados localmente no dispositivo. Não possui backend, autenticação ou banco de dados externo.

## Tecnologias

* Kotlin
* Jetpack Compose
* Material 3
* Android ViewModel
* SharedPreferences
* Gson

## Requisitos

Para executar o projeto, é necessário ter:

* Android Studio
* JDK compatível com a versão do Android Studio instalada
* Dispositivo Android físico ou emulador Android

O dispositivo físico pode ser conectado por USB com a **Depuração USB** ativada.

## Estrutura do projeto

```text
PocketBudgetMobile/
│
├── app/
│   ├── build.gradle.kts
│   │
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           │
│           ├── java/com/pocketbudget/app/
│           │   │
│           │   ├── MainActivity.kt
│           │   │
│           │   ├── model/
│           │   │   └── Transaction.kt
│           │   │
│           │   ├── data/
│           │   │   └── TransactionRepository.kt
│           │   │
│           │   ├── viewmodel/
│           │   │   └── TransactionViewModel.kt
│           │   │
│           │   ├── util/
│           │   │   └── Formatters.kt
│           │   │
│           │   └── ui/
│           │       ├── PocketBudgetApp.kt
│           │       │
│           │       ├── components/
│           │       │   ├── SummaryCards.kt
│           │       │   ├── TransactionForm.kt
│           │       │   └── TransactionList.kt
│           │       │
│           │       └── theme/
│           │           ├── Color.kt
│           │           ├── Theme.kt
│           │           └── Type.kt
│           │
│           └── res/
│               └── values/
│                   └── strings.xml
│
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

### Organização

**`model/`**
Contém os modelos de dados utilizados pela aplicação.

**`data/`**
Responsável pela persistência local das transações.

**`viewmodel/`**
Gerencia o estado da aplicação e as operações relacionadas às transações.

**`ui/`**
Contém a interface construída com Jetpack Compose.

**`ui/components/`**
Contém os principais componentes da interface, como formulário, lista de transações e cards de resumo.

**`ui/theme/`**
Contém as configurações de tema, cores e tipografia do aplicativo.

**`util/`**
Contém funções auxiliares utilizadas pela aplicação, como formatação de valores e datas.

## Instalação

Abra o projeto no **Android Studio**.

Aguarde o Android Studio concluir a sincronização do Gradle e o carregamento das dependências.

A dependência do Gson já está configurada no projeto por meio do `app/build.gradle.kts`.

Não é necessário instalar manualmente outras bibliotecas para executar a aplicação.

## Executando o aplicativo

### Dispositivo físico

1. Ative as **Opções do desenvolvedor** no dispositivo Android.
2. Ative a **Depuração USB**.
3. Conecte o dispositivo ao computador por USB.
4. Autorize a depuração quando o Android solicitar.
5. Selecione o dispositivo no Android Studio.
6. Clique em **Run 'app'** ou pressione `Shift + F10`.

### Emulador

1. Abra o **Device Manager** no Android Studio.
2. Crie um dispositivo virtual Android.
3. Inicie o emulador.
4. Selecione o dispositivo no Android Studio.
5. Execute o projeto com **Run 'app'**.

## Funcionalidades

* Adicionar receitas e despesas
* Informar descrição, valor e categoria
* Excluir transações
* Pesquisar transações por descrição
* Filtrar transações por categoria
* Visualizar o saldo líquido
* Visualizar o total de receitas
* Visualizar o total de despesas
* Atualização dos valores em tempo real
* Validação dos campos do formulário
* Mensagens de erro em português
* Formatação monetária em Real Brasileiro (BRL)
* Formatação de datas no padrão brasileiro
* Funcionamento totalmente offline
* Interface construída com Jetpack Compose e Material 3

### Categorias

* Alimentação
* Transporte
* Contas
* Entretenimento
* Outros

## Armazenamento dos dados

As transações são armazenadas localmente no dispositivo utilizando **SharedPreferences**.

Os dados são serializados em JSON utilizando a biblioteca **Gson** e armazenados sob a chave:

```text
transactions
```

Como o armazenamento é local:

* Os dados permanecem disponíveis após fechar e reabrir o aplicativo.
* Os dados não são sincronizados entre dispositivos.
* Não existe armazenamento em nuvem.
* Desinstalar o aplicativo remove os dados armazenados localmente.
* Limpar os dados do aplicativo nas configurações do Android também remove as transações.

## Modelo de dados

A interface utiliza português brasileiro, enquanto os valores internos utilizados pelo aplicativo seguem identificadores em inglês.

| Exibido        | Valor interno   |
| -------------- | --------------- |
| Receita        | `income`        |
| Despesa        | `expense`       |
| Alimentação    | `Food`          |
| Transporte     | `Transport`     |
| Contas         | `Utilities`     |
| Entretenimento | `Entertainment` |
| Outros         | `Others`        |

## Solução de problemas

### O Gradle não sincroniza

Verifique a mensagem apresentada pelo Android Studio e execute novamente a sincronização do projeto.

Também confirme se o arquivo `app/build.gradle.kts` está configurado corretamente.

### `Unresolved reference`

Verifique se o arquivo referenciado existe no pacote correto e se a declaração `package` no início do arquivo corresponde à estrutura de diretórios.

Por exemplo:

```kotlin
package com.pocketbudget.app.viewmodel
```

deve estar dentro do diretório:

```text
app/src/main/java/com/pocketbudget/app/viewmodel/
```

### O aplicativo fecha ao iniciar

Abra o **Logcat** no Android Studio e procure pela mensagem de erro correspondente ao aplicativo.

Erros de inicialização geralmente indicam problemas em imports, configurações ou componentes utilizados pela interface.

### As transações não permanecem após fechar o aplicativo

Verifique se as operações de adicionar e excluir transações estão salvando os dados por meio do `TransactionRepository`.

## Licença

Projeto desenvolvido para fins acadêmicos e educacionais.
