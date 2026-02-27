import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContaTest {

    @Test
    @DisplayName("Deve depositar corretamente e registrar no extrato")
    public void validarDeposito() {
        ContaBancaria conta = new ContaBancaria("João", 100.0);
        conta.depositar(50.0);

        Assertions.assertEquals(150.0, conta.saldo);
        Assertions.assertTrue(conta.extrato.get(1).contains("Depósito"));
    }

    @Test
    @DisplayName("Deve impedir saque maior que o saldo (Prevenção de Fraude)")
    public void validarSaqueSemSaldo() {
        ContaBancaria conta = new ContaBancaria("Maria", 100.0);

        // Tenta sacar 150 tendo apenas 100. Esperamos um erro!
        assertThrows(IllegalStateException.class, () -> {
            conta.sacar(150.0);
        });
    }

    @Test
    @DisplayName("Deve realizar um PIX com sucesso entre duas contas")
    public void validarTransferenciaPix() {
        // 1. Arrange: Criamos dois clientes do banco
        ContaBancaria contaOrigem = new ContaBancaria("Maria", 500.0);
        ContaBancaria contaDestino = new ContaBancaria("João", 100.0);

        // 2. Act: Maria manda 200 reais para João
        contaOrigem.transferirPix(200.0, contaDestino);

        // 3. Assert: Validamos os saldos de ambos
        Assertions.assertEquals(300.0, contaOrigem.saldo, "Saldo da Maria deve diminuir");
        Assertions.assertEquals(300.0, contaDestino.saldo, "Saldo do João deve aumentar");

        // Validação extra: O extrato da Maria diz que foi enviado para o João ?
        Assertions.assertTrue(contaOrigem.extrato.get(2).contains("PIX enviado para João"));
    }
}