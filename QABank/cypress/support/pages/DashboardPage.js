// cypress/support/pages/DashboardPage.js

class DashboardPage {
    // 1. Mapeamento dos novos elementos
    seletorValor = '#valor-operacao';
    seletorBtnDeposito = '#btn-depositar';
    seletorBtnSaque = '#btn-sacar';
    seletorBtnPix = '#btn-pix';
    seletorSaldo = '#saldo-valor';
    seletorMensagem = '#mensagem-feedback';
    seletorExtrato = '#lista-extrato li'; // Pega todos os itens da lista de extrato

    // 2. Ações na tela
    visitar() {
        cy.visit('./index.html');
    }

    preencherValor(valor) {
        cy.get(this.seletorValor).type(valor);
    }

    clicarDepositar() {
        cy.get(this.seletorBtnDeposito).click();
    }

    clicarSacar() {
        cy.get(this.seletorBtnSaque).click();
    }

    clicarPix() {
        cy.get(this.seletorBtnPix).click();
    }

    // 3. Validações (Asserts)
    validarSaldo(valorEsperado) {
        cy.get(this.seletorSaldo).should('have.text', valorEsperado);
    }

    validarMensagem(textoEsperado, corEsperada) {
        cy.get(this.seletorMensagem)
            .should('be.visible')
            .and('contain', textoEsperado)
            .and('have.css', 'color', corEsperada);
    }

    validarUltimoExtrato(textoEsperado) {
        // Pega o ÚLTIMO item da lista de extrato e verifica o texto
        cy.get(this.seletorExtrato).last().should('contain', textoEsperado);
    }
}

export default new DashboardPage();