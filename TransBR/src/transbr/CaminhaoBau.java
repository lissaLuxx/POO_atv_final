package transbr;

public class CaminhaoBau extends Veiculos{

    private double peso_max;

    public double getPeso_max() {
        return peso_max;
    }
    public void setPeso_max(double peso_max) {
        this.peso_max = peso_max;
    }

    public CaminhaoBau(String placa, String tipo_carteira, double peso_max) {

        if (peso_max <= 0) {
            throw new PesoInvalidoException("Peso máximo inválido.");
        }

        super(placa, tipo_carteira);
        this.peso_max = peso_max;
    }
    

    
}
