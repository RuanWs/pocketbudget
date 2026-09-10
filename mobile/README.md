# PocketBudget Mobile

Um aplicativo móvel complementar ao PocketBudget, um rastreador de finanças pessoais. Desenvolvido com React Native e Expo, ele permite que os usuários adicionem transações de receitas e despesas, pesquisem e filtrem transações e acompanhem a atualização do saldo líquido em tempo real — tudo de forma offline, sem backend, autenticação ou banco de dados externo. A interface está em português brasileiro, com os valores monetários exibidos em Real Brasileiro (BRL).

Este aplicativo reproduz as funcionalidades e regras de negócio da aplicação web existente do PocketBudget, adaptadas para uma experiência mobile-first.

## Tecnologias Utilizadas

* React Native
* Expo (SDK 54, para compatibilidade com o aplicativo Expo Go)
* JavaScript (ES6+)
* React Hooks (`useState`, `useEffect`, `useRef`, `useMemo`)
* React Native `StyleSheet` (sem bibliotecas externas de UI/estilização)
* `@react-native-async-storage/async-storage` para persistência local e offline dos dados

## Requisitos

* Node.js 18 ou superior (qualquer versão LTS recente)
* npm (incluído com o Node.js)
* Aplicativo **Expo Go** instalado em um celular físico com iOS ou Android, **ou** um emulador Android (por meio do Android Studio), **ou** um simulador iOS (por meio do Xcode, somente no Mac), **ou** apenas um navegador web

Verifique as versões do Node.js e do npm antes de começar:

```bash
node -v
npm -v
```

## Estrutura do Projeto

```text
pocketbudget-mobile/
│
├── App.js                  # Componente raiz — estado, AsyncStorage e lógica de negócio
├── components/
│   ├── SummaryCards.js     # Cards de saldo líquido / receita total / despesas totais
│   ├── TransactionForm.js  # Formulário para adicionar transações
│   └── TransactionList.js  # Pesquisa, filtros e lista de transações
│
├── assets/
├── app.json
├── package.json
├── package-lock.json
├── babel.config.js
├── index.js
└── README.md
```

Não existe uma pasta `src` — o arquivo `App.js` fica na raiz do projeto, que é uma convenção comum em projetos Expo, diferente de alguns frameworks web que organizam os arquivos dentro de uma pasta `src/`.

## Instalação

A partir da pasta raiz do projeto:

```bash
npm install
```

Esse comando instala tudo o que está listado no `package.json`, incluindo:

**Principais dependências**

* `expo`
* `react`
* `react-native`

**Única dependência adicional deste projeto**

* `@react-native-async-storage/async-storage`

Se estiver configurando este projeto do zero, em vez de utilizar um `package.json` existente:

```bash
npx create-expo-app pocketbudget-mobile --template blank
cd pocketbudget-mobile
npx expo install @react-native-async-storage/async-storage
```

Ao configurar o projeto, utilize uma versão do SDK compatível com o Expo Go instalado no dispositivo.

Não escolha uma versão mais recente do SDK sem verificar sua compatibilidade com o Expo Go, pois versões incompatíveis podem causar erros de versão ao abrir o projeto.

## Executando o Aplicativo

Inicie o servidor de desenvolvimento:

```bash
npx expo start
```

Isso iniciará o servidor de desenvolvimento do Expo no terminal e exibirá um código QR. A partir daí, existem várias maneiras de visualizar o aplicativo:

### Opção 1 — Em um celular físico

1. Instale o aplicativo **Expo Go** pela App Store (iOS) ou Google Play Store (Android).
2. Certifique-se de que o celular e o computador estejam conectados à mesma rede Wi-Fi.
3. Escaneie o código QR exibido no terminal:

    * iOS: utilize o aplicativo de câmera do celular.
    * Android: utilize a opção "Scan QR code" dentro do Expo Go.

### Opção 2 — Em um navegador

Com o servidor de desenvolvimento em execução, pressione **`w`** no terminal.

O aplicativo será aberto em `http://localhost:8081` ou na porta exibida pelo Expo.

Na primeira vez, o Expo poderá solicitar a instalação de dois pacotes adicionais. Aceite a instalação ou execute manualmente:

```bash
npx expo install react-native-web react-dom
```

A visualização no navegador é conveniente para verificar rapidamente a lógica e o layout, mas não reproduz perfeitamente alguns comportamentos de dispositivos móveis, como gestos nativos, comportamento do teclado e áreas seguras da tela.

Para uma representação mais fiel do aplicativo, recomenda-se testá-lo no Expo Go ou em um emulador/simulador antes de considerá-lo finalizado.

### Opção 3 — Emulador Android

É necessário ter o Android Studio instalado, com um dispositivo virtual (AVD) criado e em execução.

Com o servidor de desenvolvimento em execução, pressione **`a`** no terminal.

### Opção 4 — Simulador iOS

Disponível somente no Mac.

É necessário ter o Xcode instalado.

Com o servidor de desenvolvimento em execução, pressione **`i`** no terminal.

Pressione `Ctrl+C` no terminal a qualquer momento para interromper o servidor de desenvolvimento.

## Como os Dados São Armazenados

Todas as transações são armazenadas localmente utilizando `@react-native-async-storage/async-storage`, com a chave `"transactions"`.

Isso significa que:

* Os dados permanecem salvos após fechar e reabrir o aplicativo.
* Os dados ficam armazenados somente no dispositivo em que foram inseridos — não existe sincronização com a nuvem entre dispositivos.
* Desinstalar o aplicativo ou limpar os dados/armazenamento do aplicativo nas configurações do celular apagará todas as transações salvas.
* Na versão executada pelo navegador, o armazenamento é feito utilizando a implementação web do AsyncStorage, mantendo a persistência dos dados no navegador. Esse armazenamento é separado dos dados das instalações nativas.

O aplicativo foi desenvolvido para evitar um problema comum: as transações só são salvas novamente no armazenamento depois que o carregamento inicial dos dados é concluído. Dessa forma, uma nova inicialização do aplicativo não sobrescreve acidentalmente os dados existentes com uma lista vazia.

## Funcionalidades

* Adicionar receitas ou despesas com descrição, valor e categoria
* Excluir transações individualmente
* Pesquisar transações pela descrição, sem diferenciar letras maiúsculas e minúsculas
* Filtrar transações por categoria utilizando filtros horizontais
* Cards de resumo atualizados em tempo real:

    * Saldo líquido
    * Receita total
    * Despesas totais
* Validação dos campos do formulário com mensagens de erro em português
* Valores monetários formatados em Real Brasileiro: `R$ 1.000,00`
* Datas formatadas utilizando a localização `pt-BR`
* Funcionamento totalmente offline — sem requisições de rede, backend ou login
* Experiência mobile nativa, utilizando áreas seguras, `KeyboardAvoidingView`, `FlatList` para a lista de transações e botões e espaçamentos adequados para toque

## Idioma e Modelo de Dados

A interface do aplicativo está totalmente em português brasileiro, mas os valores utilizados internamente permanecem em inglês para manter as regras de negócio compatíveis com a versão web:

| Exibido (Português) | Armazenado internamente (Inglês) |
| :------------------ | :------------------------------- |
| Receita             | `income`                         |
| Despesa             | `expense`                        |
| Alimentação         | `Food`                           |
| Transporte          | `Transport`                      |
| Contas              | `Utilities`                      |
| Entretenimento      | `Entertainment`                  |
| Outros              | `Others`                         |



## Solução de Problemas

**O navegador/Expo Go mostra "Open up App.js to start working on your app!"**

Isso significa que o `App.js` ainda contém o template padrão do Expo em vez do código do PocketBudget.

Abra o `App.js` no editor e verifique se ele começa com:

```javascript
import React, { useState, useEffect, useRef } from "react";
```

Caso contrário, substitua o conteúdo do arquivo pelo código correto do `App.js` do projeto.

**O código QR não pode ser escaneado / o Expo Go informa que o aplicativo não pode ser aberto**

Isso geralmente está relacionado a uma incompatibilidade entre as versões do SDK do Expo e do Expo Go.

Verifique se a versão do SDK utilizada pelo projeto é compatível com a versão do Expo Go instalada no dispositivo.

**As alterações não aparecem no aplicativo**

O servidor de desenvolvimento normalmente atualiza o aplicativo automaticamente.

Se uma alteração não aparecer, tente recarregar manualmente:

* No Expo Go, agite o dispositivo ou pressione `r` no terminal.
* No navegador, atualize a página.

**Erros do AsyncStorage durante a inicialização**

Confirme se o pacote está instalado:

```bash
npx expo install @react-native-async-storage/async-storage
```

Se o problema continuar, limpe o cache do Metro Bundler:

```bash
npx expo start -c
```

**A porta já está sendo utilizada**

O Expo normalmente escolherá automaticamente a próxima porta disponível.

Utilize a URL e a porta exibidas no terminal, que podem ser diferentes de `8081`.

