package sistemaDeEstacionamento;

public class Espaco {
    
    private int numero;
    private String dimensao;
    private boolean disponivel;
    private Veiculo veiculo;

    public Espaco(int numero, String dimensao) {
        this.numero = numero;
        this.dimensao = dimensao;
        this.disponivel = true;
    }

    public boolean ocupar(Veiculo veiculo) {
        if (disponivel && this.dimensao.equals(veiculo.getDimensao())) {
            this.veiculo = veiculo;
            this.disponivel = false;
            return true;
        }
        return false;
    }

    public void liberar() {
        this.veiculo = null;
        this.disponivel = true;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public String getDimensao() {
        return dimensao;
    }

    public int getNumero() {
        return numero;
    }
}


