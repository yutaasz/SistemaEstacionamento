package sistemaDeEstacionamento;

public class Veiculo {
    
    private String placa;
    private String modelo;
    private String dimensao;
    private long horarioEntrada;
    private long horarioSaida;

    public Veiculo(String placa, String modelo, String dimensao, long horarioEntrada) {
        this.placa = placa;
        this.modelo = modelo;
        this.dimensao = dimensao;
        this.horarioEntrada = horarioEntrada;
    }

    public String getPlaca() {
        return placa;
    }

    public String getDimensao() {
        return dimensao;
    }

    public void setHorarioSaida(long horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public long getTempoPermanencia() {
        return (horarioSaida - horarioEntrada) / (1000 * 60 * 60);
    }
}
