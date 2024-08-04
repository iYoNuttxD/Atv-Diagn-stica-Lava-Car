package LavaCar;

import java.util.List;

public class Cliente {
    private int codCliente;
    private String nome;
    private String cpf;
    private String telefone;

    public Cliente(int codCliente, String nome, String cpf, String telefone) {
        this.codCliente = codCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public static void listarCliente(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            exibirInfo(cliente);
            System.out.println();
        }
    }


    public static void exibirInfo(Cliente cliente) {
        System.out.println("Nome do cliente: " + cliente.getNome());
        System.out.println("CPF do cliente: " + cliente.getCpf());
        System.out.println("Telefone do cliente: " + cliente.getTelefone());
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
