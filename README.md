# To-Do List API - Desafio Zetta
Esta é uma API REST para gerenciamento de tarefas personalizadas, construída com as versões mais recentes do ecossistema Spring. A aplicação foca em segurança e produtividade, permitindo que cada usuário gerencie sua própria lista de afazeres de forma isolada e segura.

Tecnologias e Versões
De acordo com as especificações do projeto:

Java 21: Utilizando as últimas funcionalidades da linguagem, como Records e melhorias de performance.

Spring Boot 4.0.2: Versão mais recente do framework para alta escalabilidade.

Spring Security: Controle de acesso e proteção contra vulnerabilidades.

JSON Web Token (auth0 java-jwt 4.5.0): Autenticação stateless robusta.

PostgreSQL: Banco de dados relacional de alta confiabilidade usado em produção.

Project Lombok: Redução de código boilerplate como Getters, Setters e Construtores.

Hibernate Validator 9.1.0: Validação rigorosa de dados de entrada.

Arquitetura de Segurança
A API implementa um modelo de segurança baseado em camadas:

Criptografia: As senhas são processadas via BCrypt antes de serem armazenadas.

Tokens: Emissão de tokens JWT com identificação de sessões sem estado.

Filtros Customizados: Um SecurityFilter intercepta cada requisição para validar a identidade do portador do token antes de permitir o acesso aos dados.

Isolamento de Proprietário: O sistema garante que um usuário não consiga acessar ou modificar as tarefas de terceiros através de verificações de ID em nível de serviço.

Principais Endpoints
Autenticação
POST /auth/register: Registro de novos usuários com definição de perfis (USER, ADMIN).

POST /auth/login: Validação de credenciais e geração de token.

Tarefas (Tasks)
GET /tasks: Listagem completa das tarefas vinculadas ao usuário logado.

GET /tasks/filter?realizada=true: Filtro dinâmico de status para tarefas concluídas ou pendentes.

POST /tasks: Cadastro de nova tarefa, vinculada automaticamente ao usuário autenticado.

PUT /tasks: Atualização de dados com validação de posse da tarefa.

DELETE /tasks/{id}: Remoção segura de registros específicos.

Usuários
GET /users: Listagem administrativa protegida por UserResponseDTO para ocultar senhas.

Configuração de Desenvolvimento
O projeto utiliza o Spring Boot DevTools, permitindo o reinício automático da aplicação durante o desenvolvimento ao detectar mudanças no código.

Para executar:

Certifique-se de ter um banco PostgreSQL configurado.

Ajuste as propriedades de conexão no arquivo application.properties.

Execute via Maven:

Bash

./mvnw spring-boot:run
