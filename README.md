# To-Do List API – Desafio Zetta

Esta é uma API REST para gerenciamento de tarefas personalizadas, construída com as versões mais recentes do ecossistema Spring.  
O restante da documentação, com exemplos e script de criação pode ser encontrada em: 

https://docs.google.com/document/d/1AfqVkl5_L8GXXgYawDdh4EqTCVFklAqYdVkWWZbBBrc/edit?usp=sharing
---

## Tecnologias e Versões

- **Java 21**  
  Utilizando as últimas funcionalidades da linguagem, como Records e melhorias de performance.

- **Spring Boot 4.0.2**  
  Versão mais recente do framework, garantindo alta escalabilidade e produtividade.

- **Spring Security**  
  Controle de acesso e proteção contra vulnerabilidades comuns.

- **JSON Web Token (auth0 java-jwt 4.5.0)**  
  Autenticação stateless robusta e segura.

- **PostgreSQL**  
  Banco de dados relacional de alta confiabilidade, utilizado em produção.

- **Project Lombok**  
  Redução de código boilerplate como getters, setters e construtores.

- **Hibernate Validator 9.1.0**  
  Validação rigorosa dos dados de entrada da aplicação.

---

## Arquitetura de Segurança

A API implementa um modelo de segurança baseado em camadas:

- **Criptografia**  
  As senhas são processadas via BCrypt antes de serem armazenadas no banco de dados.

- **Tokens JWT**  
  Emissão de tokens para identificação de sessões sem estado (stateless).

---

## Principais Endpoints

### Autenticação

- `POST /auth/register`  
  Registro de novos usuários com definição de perfis (USER, ADMIN).

- `POST /auth/login`  
  Validação de credenciais e geração do token JWT.

---

### Tarefas (Tasks)

- `GET /tasks`  
  Listagem completa das tarefas vinculadas exclusivamente ao usuário autenticado.

- `GET /tasks/filter?realizada=true`  
  Filtro dinâmico para tarefas concluídas ou pendentes.

- `POST /tasks`  
  Cadastro de nova tarefa, vinculada automaticamente ao usuário logado.

- `PUT /tasks`  
  Atualização de dados com validação de posse da tarefa.

- `DELETE /tasks/{id}`  
  Remoção segura de tarefas pertencentes ao usuário.

---

### Como executar o projeto 

3. Ajuste as configurações em src/main/resources/application.properties.
4. Excecute a aplicação via Mavem: ./mvnw spring-boot:run. 
5. Ajuste as propriedades de conexão (URL, usuário e senha) no arquivo:
