package transbr;
import java.io.Serializable;

public abstract class Carga implements Serializable {
    protected double peso;
    protected double valor;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Carga(double peso, double valor) {

        if (peso <= 0){
            throw new SemCargaException("Peso deve ser maior que zero.");
        }

        if (valor < 0){
            throw new ValorInvalidoException("Valor deve ser maior ou igual a zero.");
        }

        this.peso = peso;
        this.valor = valor;
    }
}
