import DashboardPage from '../support/pages/DashboardPage';

describe('Testes E2E - Dashboard Bancário', () => {

    // O beforeEach roda ANTES de cada teste. 
    // Garante que todo teste comece com a página zerada (Saldo R$ 500)
    beforeEach(() => {
        DashboardPage.visitar();
    });

    it('Deve realizar um depósito com sucesso e atualizar saldo/extrato', () => {
        DashboardPage.preencherValor('150.50');
        DashboardPage.clicarDepositar();

        // Saldo inicial 500 + 150.50 = 650.50
        DashboardPage.validarMensagem('Depósito de R$ 150.50 realizado!', 'rgb(0, 128, 0)'); // rgb(0, 128, 0) é verde
        DashboardPage.validarSaldo('650.50');
        DashboardPage.validarUltimoExtrato('Depósito: + R$ 150.50');
    });

    it('Deve impedir saque com valor maior que o saldo disponível', () => {
        DashboardPage.preencherValor('1000');
        DashboardPage.clicarSacar();

        // Valida erro e garante que o saldo não mudou
        DashboardPage.validarMensagem(' Saldo insuficiente!', 'rgb(255, 0, 0)'); // rgb(255, 0, 0) é vermelho
        DashboardPage.validarSaldo('500.00'); 
    });

    it('Deve realizar uma transferência PIX com sucesso', () => {
        DashboardPage.preencherValor('200');
        DashboardPage.clicarPix();

        // Saldo inicial 500 - 200 = 300
        DashboardPage.validarMensagem('PIX enviado de R$ 200.00 realizado!', 'rgb(0, 128, 0)');
        DashboardPage.validarSaldo('300.00');
        DashboardPage.validarUltimoExtrato('PIX enviado: - R$ 200.00');
    });

});