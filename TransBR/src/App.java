import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import transbr.*; 


public class App {

    public static void cls(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static String viagens_arq = "viagens.dat";
    static String carretas_arq = "carretas.dat";
    static String caminhoes_arq = "caminhoes.dat";
    static String motoristas_arq = "motoristas.dat";
    static String cargas_perigosas_arq = "cargas_perigosas.dat";
    static String cargas_regulares_arq = "cargas_regulares.dat";


    public static ArrayList<Viagem> viagens = new ArrayList<>();
    public static ArrayList<Motorista> motoristas = new ArrayList<>();
    public static ArrayList<CaminhaoBau> caminhoes = new ArrayList<>();
    public static ArrayList<CarretaTanque> carretas = new ArrayList<>();
    public static ArrayList<CargaPerigosa> cargas_perigosas = new ArrayList<>();
    public static ArrayList<CargaRegular> cargas_regulares = new ArrayList<>();


    


    public static void criar_viagem(){
        System.out.println("Digite o destino da viagem: ");
        Scanner input = new Scanner(System.in);
        String destino = input.nextLine();
        
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(motoristas_arq))) {
           
                motoristas = (ArrayList<Motorista>) ois.readObject();
        } 
        catch (IOException | ClassNotFoundException ex) { 
            if (ex instanceof EOFException) {
            } else {
                ex.printStackTrace();
            }

        }

        System.out.println("Selecione o motorista para a viagem: ");
        for (int i = 0; i < motoristas.size(); i++) {
            System.out.println("[" + (i+1) + "] " + motoristas.get(i).getNome());
        }
        System.out.println("[0] Retornar");

        int opt_motorista = input.nextInt(); input.nextLine();
        if (opt_motorista == 0) {
            return;
        }

        Motorista motorista_selecionado = motoristas.get(opt_motorista - 1);

        System.out.println("Selecione o veículo para a viagem: ");

        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoes_arq))) {
           caminhoes = (ArrayList<CaminhaoBau>) ois.readObject();
        } 
        catch (IOException | ClassNotFoundException ex) { 

        }

        System.out.println("Caminhões-baú disponíveis: ");
        for (int i = 0; i < caminhoes.size(); i++) {
            System.out.println("[" + (i+1) + "] " + caminhoes.get(i).getPlaca());
        }

        
        ArrayList<CarretaTanque> carretas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(carretas_arq))) {
            carretas = (ArrayList<CarretaTanque>) ois.readObject();
        } 
        catch (IOException | ClassNotFoundException ex) { 
            if (ex instanceof EOFException) {
            } else {

            }
        }

        System.out.println("Carretas-tanque disponíveis: ");
        for (int i = caminhoes.size(); i < carretas.size(); i++) {
            System.out.println("[" + (i+1) + "] " + carretas.get(i).toString());
        }    
        System.out.println("[0] Retornar");

        int opt_veiculo = input.nextInt(); input.nextLine();
        if (opt_veiculo == 0) {
            return;
        }

        Veiculos veiculo_selecionado;
        if (opt_veiculo <= caminhoes.size()) {
            veiculo_selecionado = caminhoes.get(opt_veiculo - 1);
        } else {
            veiculo_selecionado = carretas.get(opt_veiculo - caminhoes.size() - 1);
        }

        

        System.out.println("Cargas regulares disponíveis: ");
        for (int i = 0; i < cargas_regulares.size(); i++) { 
            System.out.println("[" + (i+1) + "] " + cargas_regulares.get(i).toString());
        }


        System.out.println("Cargas perigosas disponíveis: ");
        for (int i = cargas_regulares.size(); i < cargas_perigosas.size(); i++) {
            System.out.println("[" + (i+1) + "] " + cargas_perigosas.get(i).toString());
        }

        System.out.println("[0] Retornar");

        int opt_carga = input.nextInt(); input.nextLine();
        if (opt_carga == 0) {
            return;
        }

        Carga carga_selecionada;
        if (opt_carga <= cargas_regulares.size()) {
            carga_selecionada = cargas_regulares.get(opt_carga - 1);
        } else {
            carga_selecionada = cargas_perigosas.get(opt_carga - cargas_regulares.size() - 1);
        }

        Viagem viagem = new Viagem(destino, carga_selecionada, veiculo_selecionado, motorista_selecionado);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(viagens_arq))) { 
            viagens.add(viagem);
            oos.writeObject(viagens); 
        } 
        catch (IOException ex) { 
        }
    }

    public static void criar_veiculo(){
        System.out.println("Digite a placa do veículo: ");
        Scanner input = new Scanner(System.in);
        String placa = input.nextLine();
        System.out.println("Digite o tipo da carteira do veículo: ");
        String tipo_carteira = input.nextLine();

        System.out.println("Digite o tipo do veículo: ");
        System.out.println("""
                [1] Caminhão-baú
                [2] Carreta-tanque
                """);
        int opt = input.nextInt(); input.nextLine();
        switch (opt) {
            case 1:
                System.out.println("Digite a capacidade de peso do caminhão-baú (em toneladas): ");
                double peso_max = input.nextDouble(); input.nextLine();
            
                CaminhaoBau caminhao = new CaminhaoBau(placa, tipo_carteira, peso_max);

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoes_arq))) { 
                    
                    caminhoes.add(caminhao);
                    oos.writeObject(caminhoes); 
                } 
                catch (IOException ex) { 
                }

                System.out.println("Caminhão-baú cadastrado com sucesso! Pressione Enter para continuar.");
                break;
            case 2:
                System.out.println("Digite o status da inspeção do carreta-tanque (APROVADO/REPROVADO): ");
                String status_inspecao = input.nextLine();
                status_inspecao = status_inspecao.toUpperCase();
                System.err.println(status_inspecao);
                CarretaTanque carreta = new CarretaTanque(placa, tipo_carteira, status_inspecao.equals("APROVADO"));


                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(carretas_arq))) { 
                
                    carretas.add(carreta);
                    oos.writeObject(carretas);
                } 
                catch (IOException ex) { 
                }

                System.out.println("Carreta-tanque cadastrada com sucesso!");
                break;
            default:
                throw new AssertionError("Opção inválida");
        }        
    }

    
    public static void criar_carga(){
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o valor da carga: ");
        double valor_carga = input.nextDouble();
        System.out.println("Digite o peso da carga (em toneladas): ");
        double peso_carga = input.nextDouble(); input.nextLine();

        System.out.println("Digite o tipo da carga (PERIGOSA/REGULAR): ");
        String tipo_carga = input.nextLine();

        switch (tipo_carga.toUpperCase()) {
            case "PERIGOSA":
                System.out.println("Digite o número ONU da carga: ");
                String numero_onu = input.nextLine();

                System.out.println("A carga possui licença? (S/N): ");
                String possui_licenca = input.nextLine();

                if (possui_licenca.isBlank() || (!possui_licenca.equalsIgnoreCase("S") && !possui_licenca.equalsIgnoreCase("N"))) {
                    throw new LicencaInvalidaException("Licença inválida; deve ser S ou N.");
                }

                CargaPerigosa carga_perigosa = new CargaPerigosa(peso_carga, valor_carga, numero_onu, possui_licenca.equalsIgnoreCase("S"));

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cargas_perigosas_arq))) { 
                
                    cargas_perigosas.add(carga_perigosa);
                    oos.writeObject(cargas_perigosas);
                    System.out.println("Carga perigosa cadastrada com sucesso!");
                } 
                catch (IOException ex) { 
                    ex.printStackTrace(); 
                }
                break;

            case "REGULAR":
                CargaRegular carga_regular = new CargaRegular(peso_carga, valor_carga);

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cargas_regulares_arq))) { 
                
                    cargas_regulares.add(carga_regular);
                    oos.writeObject(cargas_regulares);
                    System.out.println("Carga regular cadastrada com sucesso!");
                } 
                catch (IOException ex) { 
                    ex.printStackTrace(); 
                }           
                break;

            default:
                throw new AssertionError("Tipo de carga inválido.");

        }
    }

    public static void criar_motorista(){
        System.out.println("Digite o nome do motorista: ");
        Scanner input = new Scanner(System.in);
        String nome = input.nextLine();
        System.out.println("Digite o CPF do motorista (apenas números): ");
        String cpf = input.nextLine();
        System.out.println("Digite o tipo da carteira do motorista: ");
        String tipo_carteira = input.nextLine();
        System.out.println("O motorista possui certificação MOPP? (S/N): ");
        String tem_mopp = input.nextLine();

        Motorista motorista = new Motorista(nome, cpf, tipo_carteira, tem_mopp.equalsIgnoreCase("S"));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(motoristas_arq))) { 
                
            motoristas.add(motorista);
            oos.writeObject(motoristas);
            oos.flush(); 
            System.out.println("Motorista cadastrado com sucesso!");
        } 
        catch (IOException ex) { 
            ex.printStackTrace(); 
        }
    }

    public static void listar_viagens(){
        System.out.println("Lista de viagens cadastradas: ");
        for (Viagem viagem : viagens) {
            System.out.println(viagem);
        }
    }

    public static void listar_motoristas(){
        System.out.println("Lista de motoristas cadastrados: ");
        for (Motorista motorista : motoristas) {
            System.out.println(motorista);
        }
    }

    public static void listar_veiculos(){
        System.out.println("Lista de veículos cadastrados: ");
        for (CaminhaoBau caminhao : caminhoes) {
            System.out.println(caminhao);
        }
        for (CarretaTanque carreta : carretas) {
            System.out.println(carreta);
        }
    }

    public static void listar_cargas(){
        System.out.println("Lista de cargas cadastradas: ");
        for (CargaPerigosa carga_perigosa : cargas_perigosas) {
            System.out.println(carga_perigosa);
        }
        for (CargaRegular carga_regular : cargas_regulares) {
            System.out.println(carga_regular);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int opt = 1;

        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(viagens_arq))) {
            viagens = (ArrayList<Viagem>) ois.readObject();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Arquivo de viagens não encontrado. Criando um novo arquivo.");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(viagens_arq))) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException | ClassNotFoundException ex) { 
            if (ex instanceof EOFException) {
            } else {
                ex.printStackTrace();
            }
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(motoristas_arq))) {
            motoristas = (ArrayList<Motorista>) ois.readObject();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Arquivo de motoristas não encontrado. Criando um novo arquivo.");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(motoristas_arq))) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException | ClassNotFoundException ex) { 
            if (ex instanceof EOFException) {
            } else {
                ex.printStackTrace();
            }
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoes_arq))) {
            caminhoes = (ArrayList<CaminhaoBau>) ois.readObject();
        } 
        catch (FileNotFoundException ex) {
            System.out.println("Arquivo de caminhoes não encontrado. Criando um novo arquivo.");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoes_arq))) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException | ClassNotFoundException ex) { 
            if (ex instanceof EOFException) {
            } else {
                ex.printStackTrace();
            }
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(carretas_arq))) {
            carretas = (ArrayList<CarretaTanque>) ois.readObject();
        } 
        catch (FileNotFoundException ex) {
            System.out.println("Arquivo de carretas não encontrado. Criando um novo arquivo.");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(carretas_arq))) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException | ClassNotFoundException ex) { 
            if (ex instanceof EOFException) {
            } else {
                ex.printStackTrace();
            }
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cargas_perigosas_arq))) {
            cargas_perigosas = (ArrayList<CargaPerigosa>) ois.readObject();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Arquivo de cargas perigosas não encontrado. Criando um novo arquivo.");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cargas_perigosas_arq))) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException | ClassNotFoundException ex) { 
            if (ex instanceof EOFException) {
            } else {
                ex.printStackTrace();
            }
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cargas_regulares_arq))) {
            while(true) {
                CargaRegular item = (CargaRegular) ois.readObject(); 
                if (item == null) {
                    break;
                }  
                cargas_regulares.add(item);
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("Arquivo de cargas regulares não encontrado. Criando um novo arquivo.");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cargas_regulares_arq))) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException | ClassNotFoundException ex) { 
            if (ex instanceof EOFException) {
            } else {
                ex.printStackTrace();
            }
        }


        while(opt != 0){
            cls();
            System.out.println("Olá usuário! Escolha uma opção:");
            System.out.println("""
                    [1] Criar viagem
                    [2] Criar veículo
                    [3] Criar motorista
                    [4] Criar carga
                    [5] Listar viagens
                    [6] Listar veículos
                    [7] Listar motoristas
                    [8] Listar cargas
                    [0] Sair
                    """);
            opt = input.nextInt();input.nextLine();
            
            cls();
            switch (opt) {
                case 1:
                    try {
                        criar_viagem();} 
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        criar_veiculo();} 
                    catch (PlacaNulaException | TipoCarteiraInvalidoException | InspecaoInvalidaException | AssertionError e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        criar_motorista();} 
                    catch (CPFInvalidoException | TipoCarteiraInvalidoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {criar_carga();} 
                    catch (SemNumeroONUException | LicencaInvalidaException | AssertionError e) {
                        System.out.println(e.getMessage());
                        
                    }
                    break;
                case 5:
                    try {
                        listar_viagens();
                    } 
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        listar_veiculos();} 
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        listar_motoristas();} 
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        listar_cargas();} 
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    throw new AssertionError();
            }
            System.out.println("Pressione Enter para continuar...");
            input.nextLine();
        }
    }
}
