import java.util.ArrayList;
import java.util.List;

public class ContaBancaria {
    public String titular;
    public double saldo;
    public List<String> extrato; // Uma lista para guardar o histórico do cliente

    // Construtor: O que acontece quando o cliente abre a conta
    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
        this.extrato = new ArrayList<>();
        this.extrato.add("Conta aberta com saldo de: R$ " + saldoInicial);
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser positivo!");
        }
        this.saldo += valor;
        this.extrato.add("Depósito: + R$ " + valor);
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque inválido!");
        }
        if (valor > this.saldo) {
            throw new IllegalStateException("Saldo insuficiente para saque!");
        }
        this.saldo -= valor;
        this.extrato.add("Saque: - R$ " + valor);
    }

    // O grande diferencial: Transferência entre duas contas diferentes
    public void transferirPix(double valor, ContaBancaria contaDestino) {
        if (valor > this.saldo) {
            throw new IllegalStateException("Saldo insuficiente para realizar o PIX!");
        }
        // Tira o dinheiro desta conta
        this.sacar(valor);
        // Coloca o dinheiro na conta destino
        contaDestino.depositar(valor);

        // Atualiza o extrato informando para quem foi o dinheiro
        this.extrato.add("PIX enviado para " + contaDestino.titular + ": - R$ " + valor);
    }
}