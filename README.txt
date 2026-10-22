# PROJETO 05 - Sistema CRUD de Cadastro de Usuários (Android SQLite)

Projeto desenvolvido como parte da disciplina de Banco de Dados, focado na criação de uma aplicação móvel completa no Android Studio utilizando Kotlin e o banco de dados nativo SQLite.

---

## 📱 Sobre o Projeto

A aplicação consiste num sistema de cadastro de usuários com suporte a todas as operações essenciais de um CRUD (**C**reate, **R**ead, **U**pdate, **D**elete):

* **Cadastrar (Gravar):** Regista um novo usuário com Nome e Idade no banco de dados SQLite.
* **Consultar:** Busca um usuário existente pelo Nome e exibe a sua Idade no ecrã.
* **Atualizar:** Permite alterar a Idade de um usuário previamente cadastrado.
* **Deletar:** Remove o registo do usuário do banco de dados a partir do Nome.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Kotlin
* **Banco de Dados:** SQLite (`SQLiteOpenHelper`)
* **Interface de Usuário:** XML / Material Design (`TextInputLayout`, `TextInputEditText`, `Button`)
* **IDE:** Android Studio

---

## 📂 Estrutura de Arquivos

* `app/src/main/res/layout/activity_main.xml`: Define a interface visual com os campos de texto e os 4 botões da aplicação.
* `app/src/main/java/com/example/projetocrud/BancoHelper.kt`: Classe responsável pela criação do banco de dados `usuarios.db`, tabela e métodos de banco de dados (inserir, consultar, atualizar e eliminar).
* `app/src/main/java/com/example/projetocrud/MainActivity.kt`: Lógica de controlo das ações dos botões e integração com o banco de dados.

---

## 🚀 Como Executar o Projeto

1. Clone este repositório para a sua máquina local:
   ```bash
   git clone [https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git](https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git)
