package transbr;
import java.io.Serializable;

public class Motorista implements Serializable {
    private String nome;
    private String cpf;
    private String carteira;
    private boolean tem_mopp;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCarteira() {
        return carteira;
    }
    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }
    public boolean getTem_mopp() {
        return tem_mopp;
    }
    public void setTem_mopp(boolean tem_mopp) {
        this.tem_mopp = tem_mopp;
    } 

    public Motorista(String nome, String cpf, String carteira, boolean tem_mopp) {

        if (cpf.isBlank()) {
            throw new CPFInvalidoException("Sem CPF; CPF é obrigatório para o motorista.");
        } else if (cpf.length() != 11) {
            throw new CPFInvalidoException("CPF inválido; deve conter exatamente 11 dígitos.");
        }
        
        if (!"ABCDE".contains(carteira.toUpperCase())) {
            throw new TipoCarteiraInvalidoException("Tipo de carteira inválida para o motorista.");
        }

        this.nome = nome;
        this.cpf = cpf;
        this.carteira = carteira;
        this.tem_mopp = tem_mopp;
    }
}
