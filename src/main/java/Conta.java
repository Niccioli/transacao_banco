import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public abstract class Conta {

    @Getter @Setter Pessoa titular;
    @Getter @Setter private int agencia;
    @Getter @Setter private int numeroConta;
    @Getter @Setter private int senha;
    @Getter @Setter private BigDecimal saldo = BigDecimal.ZERO.setScale(2);

    public Conta(Pessoa titular, int agencia, int numeroConta, int senha) {
        this.titular = titular;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.senha = senha;
        //this.saldo = saldo;
    }
}
