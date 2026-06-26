package transbr;
import java.io.Serializable;

public abstract class Veiculos implements Serializable {
    
    protected String placa;
    protected String tipo_carteira;


    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getTipo_carteira() {
        return tipo_carteira;
    }
    public void setTipo_carteira(String tipo_carteira) {
        this.tipo_carteira = tipo_carteira;
    }

    public Veiculos(String placa, String tipo_carteira) {
        if (placa.isBlank()) {
            throw new PlacaNulaException("A placa do veículo não pode ser nula ou em branco.");
        }
        if (!"ABCDE".contains(tipo_carteira.toUpperCase())) {
            throw new TipoCarteiraInvalidoException("O tipo de carteira do veículo é inválido");
        }

        this.placa = placa;
        this.tipo_carteira = tipo_carteira;
    }
}
