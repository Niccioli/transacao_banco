import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContaCorrenteTest {

    private ContaCorrente contaCorrente;

    @BeforeEach
//    public void setUp(){
//        contaCorrente = new ContaCorrente(1234, 12345, 123456, "123456");
//
//    }

    @Test
    public void depositoValido(){
        BigDecimal valorDeposito = new BigDecimal(100);
        contaCorrente.depositar(valorDeposito);
        assertEquals(new BigDecimal("100.00"), contaCorrente.getSaldo());
    }

    @Test
    public void depositoInvalido(){
        BigDecimal valorDeposito = new BigDecimal(-10);
        contaCorrente.depositar(valorDeposito);
        assertEquals(new BigDecimal("0.00"), contaCorrente.getSaldo());
    }
}
