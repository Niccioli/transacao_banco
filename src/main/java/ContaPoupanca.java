import java.math.BigDecimal;

public class ContaPoupanca extends Conta {

    private BigDecimal saldoRendimento;

    public ContaPoupanca(Pessoa titular, int agencia, int numeroConta, int senha) {
        super(titular, agencia, numeroConta, senha);
        this.saldoRendimento = BigDecimal.ZERO;
    }

    public void depositarERender(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) > 0) {
            // Calcule o rendimento (1% do valor) e adicione ao saldo.
            BigDecimal rendimento = valor.multiply(new BigDecimal("0.01"));

            // Adicione o valor e o rendimento ao saldo da conta poupança.
            BigDecimal novoSaldo = getSaldo().add(valor).add(rendimento);
            setSaldo(novoSaldo);
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void resgatarRendimento(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) > 0 && getSaldo().compareTo(valor) > 0) {
            BigDecimal saldoResgatado = getSaldo().subtract(valor);
            setSaldo(saldoResgatado);
        } else {
            System.out.println("Transação não efetuada. Saldo insuficiente");
        }
    }

}
