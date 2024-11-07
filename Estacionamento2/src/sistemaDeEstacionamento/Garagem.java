package sistemaDeEstacionamento;

import java.util.ArrayList;
import java.util.List;

public class Garagem {

    private List<Espaco> espacos = new ArrayList<>();
    private List<String> historico = new ArrayList<>();

    public void adicionarEspaco(int numero, String dimensao) {
        espacos.add(new Espaco(numero, dimensao));
    }

    public boolean registrarEntrada(String placa, String modelo, String dimensao, long horarioEntrada) {
        Veiculo veiculo = new Veiculo(placa, modelo, dimensao, horarioEntrada);
        for (Espaco espaco : espacos) {
            if (espaco.isDisponivel() && espaco.getDimensao().equals(dimensao)) {
                return espaco.ocupar(veiculo);
            }
        }
        return false;
    }

    public void registrarSaida(String placa, long horarioSaida) {
        for (Espaco espaco : espacos) {
            Veiculo veiculo = espaco.getVeiculo();
            if (veiculo != null && veiculo.getPlaca().equals(placa)) {
                veiculo.setHorarioSaida(horarioSaida);
                long horas = veiculo.getTempoPermanencia();
                double valor = calcularValor(horas);
                historico.add("Placa: " + veiculo.getPlaca() + ", Valor pago: R$ " + valor);
                espaco.liberar();
                System.out.println("Veículo " + placa + " saiu. Valor a pagar: R$ " + valor + ".");
                return;
            }
        }
        System.out.println("Veículo não encontrado.");
    }

    private double calcularValor(long horas) {
        if (horas <= 1) return 5.00;
        if (horas <= 3) return 10.00;
        return 15.00;
    }

    public void exibirEspacosOcupados() {
        System.out.println("ESPACES OCUPADOS:");
        for (Espaco espaco : espacos) {
            if (!espaco.isDisponivel()) {
                System.out.println("Espaço: " + espaco.getNumero() + ", Placa: " + espaco.getVeiculo().getPlaca() + ", Tamanho: " + espaco.getDimensao());
            }
        }
    }

    public void exibirRegistroHistorico() {
        System.out.println("HISTÓRICO DE VEÍCULOS:");
        for (String registro : historico) {
            System.out.println(registro);
        }
    }
}