package transbr;
import java.io.Serializable;


public class Viagem implements Serializable {
    private String destino;
    private Carga carga;
    private Veiculos veiculo;
    private Motorista motorista;
    private String status;
    private double valor_seguro;

    
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public Carga getCarga() {
        return carga;
    }
    public void setCarga(Carga carga) {
        this.carga = carga;
    }
    public Veiculos getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculos veiculo) {
        this.veiculo = veiculo;
    }
    public Motorista getMotorista() {
        return motorista;
    }
    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public double getValor_seguro() {
        return valor_seguro;
    }
    public void setValor_seguro(double valor_seguro) {
        this.valor_seguro = valor_seguro;
    }

    public Viagem(String destino, Carga carga, Veiculos veiculo, Motorista motorista) {
        this.destino = destino;
        this.carga = carga;
        this.veiculo = veiculo;
        this.motorista = motorista;
        this.status = "PENDENTE";
        this.valor_seguro = 0;
    }
}
