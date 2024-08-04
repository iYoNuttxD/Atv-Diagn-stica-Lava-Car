package LavaCar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        List<Carro> carros = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Servico> servicos = new ArrayList<>();
        List<Lavagem> lavagems = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.println("--- BEM-VINDO ---");
                    System.out.println("1. Cadastrar Carro");
                    System.out.println("2. Cadastrar Cliente");
                    System.out.println("3. Cadastrar Serviço");
                    System.out.println("4. Cadastrar Lavagem");
                    System.out.println("5. Listar Carro");
                    System.out.println("6. Listar Cliente");
                    System.out.println("7. Listar Serviço");
                    System.out.println("8. Listar Lavagem");
                    System.out.println("9. Sair");
                    System.out.print("--> ");

                    int opcao = Integer.parseInt(scanner.nextLine());
                    if (opcao == 1) {
                        cadastrarCarro(carros, clientes, scanner);
                    } else if (opcao == 2) {
                        cadastrarCliente(clientes, scanner);
                    } else if (opcao == 3) {
                        cadastrarServico(servicos, scanner);
                    } else if (opcao == 4) {
                        cadastrarLavagem(lavagems, servicos, carros, scanner);
                    } else if (opcao == 5) {
                        Carro.listarCarros(carros);
                    } else if (opcao == 6) {
                        Cliente.listarCliente(clientes);
                    } else if (opcao == 7) {
                        Servico.listarServico(servicos);
                    } else if (opcao == 8) {
                        Lavagem.listarLavagem(lavagems);
                    } else if (opcao == 9) {
                        break;
                    } else {
                        System.out.println("Opção inválida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Por favor, digite um número válido.");
                }
            }
        }
    }

    public static void cadastrarCarro(List<Carro> carros, List<Cliente> clientes, Scanner scanner) {
        if (clientes.isEmpty()) {
            System.out.println("Não é possível cadastrar um carro sem clientes existentes.");
        } else {
            int cod = 1;
            List<Integer> idsExistentes = new ArrayList<>();
            for (Carro carro : carros) {
                idsExistentes.add(carro.getCodCarro());
            }
            while (idsExistentes.contains(cod)) {
                cod++;
            }

            System.out.print("Digite o tipo do novo carro: ");
            String tipo = scanner.nextLine();
            while (tipo.isBlank()) {
                System.out.print("Tipo de carro inválido! Por favor, digite novamente: ");
                tipo = scanner.nextLine();
            }

            System.out.print("Digite o marca do novo carro: ");
            String marca = scanner.nextLine();
            while (marca.isBlank()) {
                System.out.print("Marca do carro inválido! Por favor, digite novamente: ");
                marca = scanner.nextLine();
            }

            System.out.print("Digite o modelo do novo carro: ");
            String modelo = scanner.nextLine();
            while (modelo.isBlank()) {
                System.out.print("Modelo do carro inválido! Por favor, digite novamente: ");
                modelo = scanner.nextLine();
            }

            System.out.print("Digite a placa do novo carro: ");
            String placa = scanner.nextLine();
            while (!Verificador.verificarPlaca(carros, placa)) {
                System.out.print("Placa do carro inválida ou já existente! Por favor, digite novamente: ");
                placa = scanner.nextLine();
            }

            placa = placa.replaceAll("\\W", "").toUpperCase();

            // Verifica o formato e formata a placa se necessário
            if (Pattern.matches("[A-Z]{3}[0-9]{4}", placa)) {
                // Formato antigo: Adiciona traço
                placa = placa.substring(0, 3) + "-" + placa.substring(3);
            }

            System.out.print("Digite o CPF do dono: ");
            String cpf = scanner.nextLine();
            while (!Verificador.verificaCPF(clientes, cpf)) { // A função deve verificar se o CPF existe
                System.out.print("CPF inválido ou não existente! Por favor, digite novamente: ");
                cpf = scanner.nextLine();
            }

            cpf = cpf.replaceAll("\\D", "");
            String cpfFormatted = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");

            Cliente clienteEncontrado = null;
            for (Cliente cliente : clientes) {
                if (cliente.getCpf().equals(cpfFormatted)) {
                    clienteEncontrado = cliente;
                    break;
                }
            }

            if (clienteEncontrado != null) {
                Carro novoCarro = new Carro(cod, tipo, marca, modelo, placa, clienteEncontrado);
                carros.add(novoCarro);
                System.out.println("Carro cadastrado com sucesso!");
            } else {
                System.out.println("Erro: Cliente não encontrado.");
            }
        }
    }

    public static void cadastrarCliente(List<Cliente> clientes, Scanner scanner) {
        int cod = 1;
        List<Integer> idsExistentes = new ArrayList<>();
        for (Cliente cliente : clientes) {
            idsExistentes.add(cliente.getCodCliente());
        }
        while (idsExistentes.contains(cod)) {
            cod++;
        }

        System.out.print("Digite o nome do novo cliente: ");
        String nome = scanner.nextLine();
        while (nome.isBlank()) {
            System.out.print("Nome do cliente inválido! Por favor, digite novamente: ");
            nome = scanner.nextLine();
        }

        System.out.print("Digite o telefone do cliente: ");
        String telefone = scanner.nextLine();
        while (!Verificador.verificarTelefone(telefone)) {
            System.out.print("Telefone do cliente inválido! Por favor, digite novamente: ");
            telefone = scanner.nextLine();
        }

        telefone = telefone.replaceAll("\\D", "");
        String telefoneFormatted = telefone.replaceAll("(\\d{2})(\\d{4,5})(\\d{4})", "($1) $2-$3");

        System.out.print("Digite o CPF do novo cliente: ");
        String cpf = scanner.nextLine();
        while (!Verificador.verificarCPF(clientes, cpf)) {
            System.out.print("CPF inválido ou existente! Por favor, digite novamente: ");
            cpf = scanner.nextLine();
        }

        cpf = cpf.replaceAll("\\D", "");
        String cpfFormatted = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");

        Cliente novoCliente = new Cliente(cod, nome, cpfFormatted, telefoneFormatted);
        clientes.add(novoCliente); // Adiciona o novo cliente à lista de clientes
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public static void cadastrarServico(List<Servico> servicos, Scanner scanner) {
        int cod = 1;
        List<Integer> idsExistentes = new ArrayList<>();
        for (Servico servico : servicos) {
            idsExistentes.add(servico.getCodServico());
        }
        while (idsExistentes.contains(cod)) {
            cod++;
        }

        System.out.print("Digite o nome do novo serviço: ");
        String nome = scanner.nextLine();
        while (nome.isBlank()) {
            System.out.print("Nome do serviço inválido! Por favor, digite novamente: ");
            nome = scanner.nextLine();
        }

        int preco = 0;
        while (preco <= 0) {
            System.out.print("Digite o preço do novo serviço: ");
            try {
                preco = Integer.parseInt(scanner.nextLine());
                if (preco <= 0) {
                    System.out.print("Preço do serviço inválido! Por favor, digite novamente: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Preço do serviço inválido! Por favor, digite novamente: ");
            }
        }
        Servico novoServico = new Servico(nome, preco);
        servicos.add(novoServico); // Adiciona o novo cliente à lista de clientes
        System.out.println("Serviço cadastrado com sucesso!");

    }

    public static void cadastrarLavagem(List<Lavagem> lavagens, List<Servico> servicos, List<Carro> carros,
                                        Scanner scanner) {
        if (servicos.isEmpty()) {
            System.out.println("Não é possível cadastrar uma lavagem sem serviços existentes.");
        } else {
            int cod = 1;
            List<Integer> idsExistentes = new ArrayList<>();
            for (Lavagem lavagem : lavagens) {
                idsExistentes.add(lavagem.getCodLavagem());
            }
            while (idsExistentes.contains(cod)) {
                cod++;
            }

            for (int i = 0; i < servicos.size(); i++) {
                System.out.println((i + 1) + ". " + servicos.get(i).getNome());
            }
            boolean escolhaValida = false;
            int escolha = 0;
            while (!escolhaValida) {
                System.out.print("Digite o número do serviço que deseja vincular: ");
                try {
                    escolha = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha
                    if (escolha > 0 && escolha <= servicos.size()) {
                        escolhaValida = true;
                    } else {
                        System.out.println("Número do serviço inválido! Por favor, digite novamente: ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Número do serviço inválido! Por favor, digite novamente: ");
                }
            }
            Servico servicoEscolhido = servicos.get(escolha - 1);

            System.out.print("Digite a placa do carro: ");
            String placa = scanner.nextLine();
            while (!Verificador.verificaPlaca(carros, placa)) {
                System.out.print("Placa do carro inválida ou já existente! Por favor, digite novamente: ");
                placa = scanner.nextLine();
            }

            placa = placa.replaceAll("\\W", "").toUpperCase();

            Carro carroEncontrado = null;
            for (Carro carro : carros) {
                String placaVeiculo = carro.getPlaca().replaceAll("\\W", "").toUpperCase();
                if (placaVeiculo.equals(placa)) {
                    carroEncontrado = carro;
                    break;
                }
            }
            if (carroEncontrado != null) {
                Lavagem novaLavagem = new Lavagem(cod, carroEncontrado, servicoEscolhido);
                lavagens.add(novaLavagem); // Adiciona a nova lavagem à lista de lavagens
                System.out.println("Lavagem cadastrada com sucesso!");
            } else {
                System.out.println("Erro: Carro não encontrado.");
            }
        }
    }

}