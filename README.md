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

-   **Linguagem:** Java
-   **Framework:** Spring (com foco no padrão MVC)
-   **Arquitetura:** Model-View-Controller (MVC)
-   **Modelagem:** UML (Diagramas de Casos de Uso, Classes, Pacotes, etc.)

## ✨ Funcionalidades Principais

O sistema é dividido em funcionalidades para dois tipos principais de usuários: Clientes e Agentes.

#### Para Clientes:
-   **Cadastro e Autenticação:** Permite que novos usuários se cadastrem e acessem o sistema.
-   **Gestão de Pedidos:**
    -   Submeter novos pedidos de aluguel.
    -   Modificar pedidos existentes (antes da aprovação).
    -   Consultar o status e os detalhes de seus pedidos.
    -   Cancelar solicitações em andamento.

#### Para Agentes (Empresas e Bancos):
-   **Análise de Pedidos:** Visualizar e analisar os pedidos submetidos pelos clientes do ponto de vista financeiro.
-   **Modificação de Pedidos:** Ajustar informações de um pedido durante a análise.
-   **Avaliação de Pedidos:** Aprovar ou recusar solicitações de aluguel.
-   **Gestão de Crédito (Bancos):** Associar um contrato de crédito a um pedido de aluguel aprovado.

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

*(Esta seção deve ser atualizada conforme o projeto avança)*

1.  **Pré-requisitos:**
    -   JDK (versão X)
    -   Maven (versão Y)
    -   Um ambiente de desenvolvimento (IDE) como IntelliJ IDEA ou Eclipse.

2.  **Clone o repositório:**
    ```bash
    git clone [URL-DO-SEU-REPOSITORIO]
    ```

3.  **Acesse o diretório do projeto:**
    ```bash
    cd laboratorio-de-desenvolvimento-de-software
    ```

4.  **Execute o projeto:**
    *(Instruções específicas de como compilar e rodar a aplicação Spring Boot)*

---