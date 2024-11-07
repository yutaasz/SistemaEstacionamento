package sistemaDeEstacionamento;

import java.util.Scanner;

public class SistemaControle {

    public static void main(String[] args) {
        Garagem garagem = new Garagem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("SISTEMA DE CONTROLE DE GARAGEM");

        garagem.adicionarEspaco(1, "pequeno");
        garagem.adicionarEspaco(2, "médio");
        garagem.adicionarEspaco(3, "grande");
        System.out.println();

        System.out.println("OS ESPAÇOS 1, 2 E 3 JÁ ESTÃO CADASTRADOS NO SISTEMA:");
        System.out.println("1- Pequeno");
        System.out.println("2- Médio");
        System.out.println("3- Grande");

        while (true) {
            System.out.println("\nESCOLHA UMA OPÇÃO: ");
            System.out.println("1. Registrar entrada de veículo");
            System.out.println("2. Registrar saída de veículo");
            System.out.println("3. Visualizar espaços ocupados");
            System.out.println("4. Visualizar histórico de veículos");
            System.out.println("0. Sair");
            System.out.println("DIGITE O NÚMERO DA OPÇÃO ESCOLHIDA:");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.print("\nPlaca do veículo: ");
                String placa = scanner.nextLine();
                System.out.print("Modelo do veículo: ");
                String modelo = scanner.nextLine();

                System.out.print("Digite o horário de entrada (formato HH:mm): ");
                String horarioEntradaStr = scanner.nextLine();
                long horarioEntrada = converterParaMilissegundos(horarioEntradaStr);

                System.out.println("Selecione o tamanho do veículo:");
                System.out.println("1. Pequeno");
                System.out.println("2. Médio");
                System.out.println("3. Grande");
                int tamanhoEscolhido = scanner.nextInt();
                String dimensaoVeiculo;
                switch (tamanhoEscolhido) {
                    case 1:
                        dimensaoVeiculo = "pequeno";
                        break;
                    case 2:
                        dimensaoVeiculo = "médio";
                        break;
                    case 3:
                        dimensaoVeiculo = "grande";
                        break;
                    default:
                        System.out.println("Opção inválida. Usando 'pequeno' como padrão.");
                        dimensaoVeiculo = "pequeno";
                }

                if (!garagem.registrarEntrada(placa, modelo, dimensaoVeiculo, horarioEntrada)) {
                    System.out.println("Não há espaço disponível para veículos do tamanho " + dimensaoVeiculo + ".");
                } else {
                    System.out.println("Entrada do veículo registrada com sucesso.");
                }

            } else if (opcao == 2) {
                System.out.print("\nPlaca do veículo: ");
                String placa = scanner.nextLine();

                System.out.print("Digite o horário de saída (formato HH:mm): ");
                String horarioSaidaStr = scanner.nextLine();
                long horarioSaida = converterParaMilissegundos(horarioSaidaStr);
                garagem.registrarSaida(placa, horarioSaida);
                System.out.println("Saída do veículo registrada com sucesso.");

            } else if (opcao == 3) {
                System.out.println();
                garagem.exibirEspacosOcupados();

            } else if (opcao == 4) {
                System.out.println();
                garagem.exibirRegistroHistorico();

            } else if (opcao == 0) {
                System.out.println("SISTEMA ENCERRADO");
                break;

            } else {
                System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE");
            }
        }
        scanner.close();
    }

    private static long converterParaMilissegundos(String horario) {
        String[] partes = horario.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        long agora = System.currentTimeMillis();
        long milissegundos = agora - (agora % (1000 * 60 * 60));

        return milissegundos + (horas * 3600000) + (minutos * 60000);
    }
}
