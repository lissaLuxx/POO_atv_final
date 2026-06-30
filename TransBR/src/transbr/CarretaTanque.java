package transbr;

public class CarretaTanque extends Veiculos {

    private boolean inspecao_ativa;
    
    public boolean isInspecao_ativa() {
        return inspecao_ativa;
    }

    public void setInspecao_ativa(boolean inspecao_ativa) {
        this.inspecao_ativa = inspecao_ativa;
    }

    public CarretaTanque(String placa, String tipo_carteira, boolean inspecao_ativa) {

        if (inspecao_ativa == true) {
            this.inspecao_ativa = inspecao_ativa;
        } else {
            throw new InspecaoInvalidaException("Sem aprovação na inspeção.");
        }

        super(placa, tipo_carteira);
        this.inspecao_ativa = inspecao_ativa;
    }
    
    @Override
    public String toString() {
        return "CarretaTanque[" +
                "placa:'" + placa + '\'' +
                ", tipo de carteira:'" + tipo_carteira + '\'' +
                ", status de inspeção:'" + (inspecao_ativa?"sim":"não") + '\'' +
                ']';
    }
}
