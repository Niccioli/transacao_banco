import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        List<Conta> listaContas = new ArrayList<>();
        Random random = new Random();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean sair = false;

        while (!sair) {
            System.out.println("\nBEM VINDO! SELECIONE UMA DAS OPÇÕES ABAIXO:");
            System.out.println("1 - CADASTRAR CONTA");
            System.out.println("2 - CONSULTAR CONTA");
            System.out.println("3 - SAIR");
            int opcao = Integer.parseInt(br.readLine());

            switch (opcao) {
                case 1:
                    System.out.println("\nSELECIONE O TIPO DE CONTA");
                    System.out.println("1 - CONTA CORRENTE");
                    System.out.println("2 - CONTA POUPANÇA");
                    int tipoConta = Integer.parseInt(br.readLine());

                    System.out.print("INFORME O NOME DO TITULAR: ");
                    String nome = br.readLine();
                    System.out.print("INFORME O CPF DO TITULAR: ");
                    String cpf = br.readLine();
                    System.out.print("INFORME O ENDEREÇO DO TITULAR: ");
                    String endereco = br.readLine();
                    System.out.print("INFORME O TELEFONE DO TITULAR: ");
                    String telefone = br.readLine();
                    Pessoa pessoa = new Pessoa(nome, cpf, endereco, telefone);

                    int agencia = random.nextInt(9000) + 1000;
                    int numeroConta = random.nextInt(90000) + 10000;

                    System.out.print("CADSTRE UMA SENHA DE 6 DÍGITOS (APENAS NÚMEROS): ");
                    int senha = Integer.parseInt(br.readLine());

                    if (tipoConta == 1) {
                        System.out.print("CADASTRE UMA CHAVE PIX: ");
                        String chavePix = br.readLine();
                        ContaCorrente contaCorrente = new ContaCorrente(pessoa, agencia, numeroConta, senha, chavePix);
                        listaContas.add(contaCorrente);
                        System.out.println("Conta Corrente adicionada com suceso!");
                    } else {
                        ContaPoupanca contaPoupanca = new ContaPoupanca(pessoa, agencia, numeroConta, senha);
                        listaContas.add(contaPoupanca);
                        System.out.println("Conta Poupança adicionada com suceso!");
                    }
                    System.out.println("Conta cadastrada com sucesso! titular: " + nome + ", agência: " + agencia + " conta: " + numeroConta);
                    Thread.sleep(2000);
                    break;

                case 2:
                    System.out.print("AGÊNCIA: ");
                    int buscaAgencia = Integer.parseInt(br.readLine());
                    System.out.print("NÚMERO CONTA: ");
                    int buscaConta = Integer.parseInt(br.readLine());
                    System.out.print("SENHA: ");
                    int buscaSenha = Integer.parseInt(br.readLine());

                    Conta contaEncontrada = null;
                    for (Conta conta : listaContas) {
                        if (conta.getSenha() == buscaSenha &&
                                conta.getAgencia() == buscaAgencia &&
                                conta.getNumeroConta() == buscaConta) {
                            contaEncontrada = conta;
                            break;
                        }
                    }
                    if (contaEncontrada == null) {
                        System.out.println("Conta não localizada! Verifique os dados informados.");
                    } else {
                        System.out.println("\nBEM VINDO " + contaEncontrada.getTitular().getNome() + "!");
                        System.out.println("SALDO R$" + contaEncontrada.getSaldo());

                        if (contaEncontrada instanceof ContaCorrente) {
                            ContaCorrente contaCorrente = (ContaCorrente) contaEncontrada;
                            boolean exit = false;
                            while (!exit) {
                                System.out.println("1-DEPOSITAR   2-SACAR   3-TRANSFERIR   4-SALDO   5-ENCERRAR CONTA   6-VOLTAR");
                                int opcaoContaCorrente = Integer.parseInt(br.readLine());

                                switch (opcaoContaCorrente) {
                                    case 1:
                                        System.out.print("Valor depósito: R$");
                                        BigDecimal valorDeposito = BigDecimal.valueOf(Integer.parseInt(br.readLine()));
                                        contaCorrente.depositar(valorDeposito);
                                        System.out.println("Deposito realizado com sucesso.");
                                        break;
                                    case 2:
                                        System.out.print("Valor de saque: R$");
                                        BigDecimal valorSaque = BigDecimal.valueOf(Integer.parseInt(br.readLine()));
                                        contaCorrente.sacar(valorSaque);
                                        System.out.println("Saque realizado com sucesso.");
                                        break;
                                    case 3:
                                        System.out.print("Chave PIX conta destino: ");
                                        String chavePixContaDestino = br.readLine();
                                        System.out.print("Valor transferência: R$");
                                        BigDecimal valorTransferencia = BigDecimal.valueOf(Integer.parseInt(br.readLine()));
                                        System.out.println("Transferência realizada com sucesso!");

                                        contaCorrente.pixTransferencia(valorTransferencia, chavePixContaDestino, listaContas);
                                        break;
                                    case 4:
                                        System.out.println("Saldo atual: R$" + contaCorrente.getSaldo());
                                        break;
                                    case 5:
                                        listaContas.remove(contaCorrente);
                                        System.out.println("Conta encerrada.");
                                        exit = true;
                                        break;
                                    case 6:
                                        exit = true;
                                        break;
                                    default:
                                        System.out.println("Opção inválida.");
                                }
                            }
                        } else if (contaEncontrada instanceof ContaPoupanca) {
                            ContaPoupanca contaPoupanca = (ContaPoupanca) contaEncontrada;
                            boolean exit = false;
                            while (!exit) {
                                System.out.println("1-APLICAR   2-RESGATAR   3-SALDO   4-ENCERRAR CONTA   5-VOLTAR");
                                int opcaoContaPoupanca = Integer.parseInt(br.readLine());

                                switch (opcaoContaPoupanca) {
                                    case 1:
                                        System.out.print("Valor a depositar com rendimento: R$");
                                        BigDecimal valorDepositarComRendimento = new BigDecimal(br.readLine());
                                        contaPoupanca.depositarERender(valorDepositarComRendimento);
                                        System.out.println("Depósito com rendimento realizado com sucesso. Saldo aplicado: R$ " + valorDepositarComRendimento + ", valor total: R$ " + contaPoupanca.getSaldo());
                                        break;

                                    case 2:
                                        System.out.println("Valor para resgate de aplicação R$ ");
                                        BigDecimal valorResgatarComRendimento = new BigDecimal(br.readLine());
                                        contaPoupanca.resgatarRendimento(valorResgatarComRendimento);
                                        System.out.println("Valor aplicado resgatado com sucesso! Novo saldo: R$ " + contaPoupanca.getSaldo());
                                        break;
                                    case 3:
                                        System.out.println("Saldo atual: R$" + contaPoupanca.getSaldo());
                                        break;
                                    case 4:
                                        listaContas.remove(contaPoupanca);
                                        System.out.println("Conta encerrada.");
                                        exit = true;
                                        break;
                                    case 5:
                                        exit = true;
                                        break;
                                    default:
                                        System.out.println("Opção inválida");
                                }
                            }
                        }
                    }
                    break;
                case 3:
                    sair = true;
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
                    Thread.sleep(2000);
            }
        }
    }
}