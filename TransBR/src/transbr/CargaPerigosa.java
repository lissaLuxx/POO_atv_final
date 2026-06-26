package transbr;

public class CargaPerigosa extends Carga implements Seguravel {
    private String numero_onu;
    private boolean tem_licenca;
    public String getNumero_onu() {
        return numero_onu;
    }
    public void setNumero_onu(String numero_onu) {
        this.numero_onu = numero_onu;
    }
    public boolean isTem_licenca() {
        return tem_licenca;
    }
    public void setTem_licenca(boolean tem_licenca) {
        this.tem_licenca = tem_licenca;
    }

    public CargaPerigosa(double peso, double valor, String numero_onu, boolean tem_licenca) {

        if (numero_onu.isBlank()) {
            throw new SemNumeroONUException("Número ONU é obrigatório para cargas perigosas.");
        }

        if (!tem_licenca) {
            throw new LicencaInvalidaException("Licença Inválida.");
        }

        super(peso, valor);


        this.numero_onu = numero_onu;
        this.tem_licenca = tem_licenca;
    }

    @Override
    public double calcularSeguro() {
        return valor * 0.2;
    }
}
