import java.math.BigDecimal;
import java.util.List;

public class ContaPoupanca extends Conta{
    public ContaPoupanca(Pessoa titular, int agencia, int numeroConta, int senha) {
        super(titular, agencia, numeroConta, senha);
    }

    public void aplicarPoupanca(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal rendimento = valor.multiply(new BigDecimal("0.01"));
            BigDecimal novoSaldo = getSaldo().add(valor).add(rendimento);
            setSaldo(novoSaldo);
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void resgateAplicacaoPoupanca(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) > 0 && getSaldo().compareTo(valor) > 0) {
            BigDecimal saldoResgatado = getSaldo().subtract(valor);
            setSaldo(saldoResgatado);
        } else {
            System.out.println("Transação não efetuada. Saldo insuficiente");
        }
    }
}
