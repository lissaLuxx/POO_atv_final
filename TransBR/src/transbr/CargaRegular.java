package transbr;


public class CargaRegular extends Carga implements Seguravel{

    public CargaRegular(double peso, double valor) {     
        super(peso, valor);     
    }

    @Override
    public double calcularSeguro() {
        if (valor > 1000000){
            return this.valor * 0.1;
        }
        return 0;
    }
}
