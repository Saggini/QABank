import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DepositoMassivoTest {

    // 1. A nossa "Massa de Dados" (Tabela)
    // O primeiro valor é o que vamos depositar, o segundo é se esperamos sucesso (true/false)
    @DataProvider(name = "valoresDeposito")
    public Object[][] criarMassa() {
        return new Object[][] {
                { 100.0, true },
                { 0.50,  true },
                { -10.0, false },
                { 0.0,   false }
        };
    }

    // 2. O Teste que vai rodar repetidamente para cada linha da tabela acima
    @Test(dataProvider = "valoresDeposito")
    public void validarDiversosDepositos(double valor, boolean devePassar) {
        ContaBancaria conta = new ContaBancaria("João",500.0);

        if (devePassar) {
            conta.depositar(valor);
            Assert.assertEquals(conta.saldo, 500.0 + valor);
        } else {
            // Se não deve passar, verificamos se o código joga o erro
            Assert.assertThrows(IllegalArgumentException.class, () -> {
                conta.depositar(valor);
            });
        }
    }
}