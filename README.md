# Sistema de Aluguel de Carros - Laboratório de Desenvolvimento de Software

## 📝 Descrição do Projeto

Este projeto consiste no desenvolvimento de um sistema de apoio à gestão de aluguéis de automóveis, permitindo a clientes e agentes (empresas e bancos) gerenciar todo o ciclo de vida de um pedido de aluguel pela internet. A plataforma permite que clientes submetam, modifiquem e consultem seus pedidos, enquanto os agentes podem analisar, avaliar e aprovar essas solicitações.

O sistema está sendo desenvolvido como parte da avaliação da disciplina de **Laboratório de Desenvolvimento de Software** do curso de **Engenharia de Software** da **PUC Minas**.

## 👥 Equipe

| Nome Completo           |
| ----------------------- |
| Arthur Araújo Mendonça  |
| Eddie Christian Pereira |
| Pedro Queiroz Rolim     |

### Professor
- João Paulo Carneiro Aramuni

## 🛠️ Tecnologias e Arquitetura

O sistema está sendo construído utilizando as seguintes tecnologias e padrões:

-   **Linguagem:** Java 17+
-   **Framework:** Spring Boot (Spring Web, Spring Security, Spring Data JPA)
-   **Arquitetura:** Model-View-Controller (MVC)
-   **Frontend:** Thymeleaf + Bootstrap
-   **Banco de Dados:** H2 Database (em memória para desenvolvimento)
-   **Build & Dependências:** Maven

## ✨ Funcionalidades Principais

O sistema é dividido em funcionalidades para dois tipos principais de usuários: Clientes e Agentes.

#### Para Clientes:
-   **Cadastro e Autenticação:** Permite que novos usuários se cadastrem e acessem o sistema.
-   **Gestão de Pedidos:**
    -   Submeter novos pedidos de aluguel.
    -   Consultar o status e os detalhes de seus pedidos.
    -   Cancelar solicitações que ainda estão em análise.
-   **Gestão de Perfil:** Editar seus próprios dados cadastrais e informações de rendimento.

#### Para Agentes (Empresas e Bancos):
-   **Análise de Pedidos:** Visualizar e analisar os pedidos submetidos pelos clientes.
-   **Avaliação de Pedidos:** Aprovar ou reprovar solicitações de aluguel.
-   **Gestão de Contratos:** Gerar contratos para pedidos aprovados.
-   **Gestão de Automóveis:** Cadastrar, visualizar e remover veículos da frota.
-   **Consulta de Clientes:** Visualizar os dados dos clientes cadastrados.

## 🚀 Estrutura de Sprints

O desenvolvimento do projeto está organizado em três sprints principais:

-   **Sprint 01 (Lab02S01):**
    -   **Foco:** Modelagem inicial do sistema.
    -   **Entregáveis:** Diagrama de Casos de Uso, Histórias de Usuário, Diagrama de Classes e Diagrama de Pacotes (Visão Lógica).

-   **Sprint 02 (Lab02S02):**
    -   **Foco:** Revisão da modelagem e implementação inicial.
    -   **Entregáveis:** Diagrama de Componentes e implementação do CRUD de Cliente em Java com arquitetura MVC.

-   **Sprint 03 (Lab02S03):**
    -   **Foco:** Implementação do protótipo funcional.
    -   **Entregáveis:** Diagrama de Implantação e um protótipo que permita ao usuário criar e visualizar o status de um pedido de aluguel.

## ⚙️ Como Executar o Projeto

1.  **Pré-requisitos:**
    -   JDK 17 ou superior.
    -   Apache Maven 3.8 ou superior.
    -   Um ambiente de desenvolvimento (IDE) como IntelliJ IDEA ou VS Code.

2.  **Clone o repositório:**
    ```bash
    git clone https://github.com/arthur-am/lab02_sistema_aluguel_carro
    ```

3.  **Acesse o diretório do projeto:**
    ```bash
    cd /Código/
    ```

4.  **Execute o projeto com Maven:**
    ```bash
    mvn spring-boot:run
    ```
    A aplicação estará disponível em `http://localhost:8080`.

5.  **Acesso ao Banco de Dados H2:**
    -   Acesse a URL: `http://localhost:8080/h2-console`
    -   **JDBC URL:** Verifique o valor no console na inicialização (geralmente `jdbc:h2:mem:testdb` ou similar).
    -   **User Name:** `sa`
    -   **Password:** (deixe em branco)

## 🔑 Credenciais de Teste

O sistema é inicializado com dois usuários padrão para facilitar os testes:

-   **Cliente:**
    -   **Email (login):** `joao@cliente.com`
    -   **Senha:** `4321`
-   **Agente:**
    -   **Email (login):** `admin@agente.com`
    -   **Senha:** `1234`

## 🧪 Cenários de Teste

#### Cenário 1: Jornada do Cliente

1.  Acesse `http://localhost:8080` e clique em "Cadastre-se".
2.  Preencha o formulário e salve para criar um novo cliente.
3.  Faça login com as credenciais do novo cliente.
4.  No painel do cliente, clique em "Novo Pedido de Aluguel", escolha um carro e envie.
5.  O novo pedido aparecerá na lista com status "EM_ANALISE".
6.  Clique no botão "Cancelar" para alterar o status do pedido para "CANCELADO".

#### Cenário 2: Jornada do Agente

1.  Acesse `http://localhost:8080` e faça login com as credenciais do agente.
2.  No painel do agente, localize o pedido criado pelo cliente (com status "EM_ANALISE").
3.  Use o seletor para "Aprovar" e clique em "Avaliar". O status do pedido mudará para "APROVADO".
4.  Um botão "Gerar Contrato" aparecerá. Clique nele para finalizar o processo.
5.  Navegue pelo menu para "Ver Clientes" ou "Gestão de Automóveis" para testar as outras funcionalidades.