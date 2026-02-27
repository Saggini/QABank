#  QABank - Automação de Testes (Backend & Frontend)

Este é um projeto prático focado em engenharia de qualidade, simulando o core bancário e o dashboard de uma instituição financeira. O objetivo é demonstrar a aplicação de testes em diferentes camadas do software, garantindo que as regras de negócio e a interface do associado funcionem com perfeição.

##  O que foi testado?
O sistema simula operações financeiras reais, com testes cobrindo o **Caminho Feliz**, **Caminhos Alternativos** e **Testes Negativos** (prevenção de fraudes/erros).
- Depósitos (Validação de soma de saldo e registro no extrato).
- Saques (Prevenção de saque com saldo insuficiente).
- Transferências PIX (Validação de envio, dedução de saldo e atualização de extrato).

##  Tecnologias e Arquitetura
O projeto foi dividido em três frentes de automação:

**1. Backend (Regras de Negócio):**
- **Java 17+:** Linguagem base para a criação da lógica financeira.
- **JUnit 5:** Utilizado para testes unitários rápidos e validação de exceções.
- **TestNG:** Utilizado para testes robustos baseados em Data-Driven Testing (Massa de Dados).
- **Maven:** Gerenciamento de dependências e execução de testes via terminal (`pom.xml`).

**2. Frontend (Interface do Associado):**
- **HTML/CSS/JavaScript:** Construção de um Dashboard moderno e responsivo.
- **Cypress:** Automação E2E (End-to-End) simulando a navegação real do usuário.
- **Page Objects (PO):** Arquitetura utilizada no Cypress para mapear elementos da tela, garantindo que o código de teste seja limpo, escalável e de fácil manutenção.

**3. API (Integração Externa):**
- **Rest-Assured:** Automação de testes de API simulando a consulta de dados em sistemas externos (ex: validação de chaves PIX em provedores de terceiros).
- **Hamcrest:** Validação assertiva de retornos em formato JSON (Status Code e Body).

##  Como executar o projeto na sua máquina

### Rodando os Testes de Backend (Java & API)
Para validar o motor financeiro e a integração externa, abra o terminal na raiz do projeto e execute o comando do Maven:
```bash
mvn clean test