import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class ContaCorrente extends Conta{
    @Getter private String chavePix;

    public ContaCorrente(Pessoa titular, int agencia, int numeroConta, int senha, String chavePix) {
        super(titular, agencia, numeroConta, senha);
        this.chavePix = chavePix;
    }

    public void depositar(BigDecimal valor){
        if (valor.compareTo(BigDecimal.ZERO) > 0){
            BigDecimal novoSaldo = getSaldo().add(valor);
            setSaldo(novoSaldo);
        } else {
            System.out.println("Valor para depósito inváldo.");
        }
    }

    public void sacar(BigDecimal valor){
        if (valor.compareTo(BigDecimal.ZERO) > 0 && getSaldo().compareTo(valor) >= 0){
            BigDecimal novoSaldo = getSaldo().subtract(valor);
            setSaldo(novoSaldo);
        }else {
            System.out.println("Transação não efetuada. Saldo insuficiente");
        }
    }

    public void pixTransferencia(BigDecimal valor, String chavePixContaDestino, List<Conta> contas){
        for (Conta conta : contas){
            if (conta instanceof ContaCorrente){
                ContaCorrente contaDestino = (ContaCorrente) conta;
                if (contaDestino.getChavePix().equals(chavePixContaDestino)){
                    if (getSaldo().compareTo(valor) >= 0){
                        sacar(valor);
                        contaDestino.depositar(valor);
                        return;
                    }else {
                        System.out.println("Saldo insuficiente.");
                        return;
                    }
                }
            }
        }
        System.out.println("Conta não informada não identificada");
    }
}
