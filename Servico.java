package LavaCar;

import java.util.List;

public class Servico {
    private int codServico;
    private String nome;
    private int preco;

    public Servico(String nome, int preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public static void listarServico(List<Servico> servicos) {
        for (Servico servico : servicos) {
            exibirInfo(servico);
            System.out.println();
        }
    }

    public static void exibirInfo(Servico servico) {
        System.out.println("Nome do serviço: " + servico.getNome());
        System.out.println("Preço do serviço: " + servico.getPreco());
    }

    public int getCodServico() {
        return codServico;
    }

    public void setCodServico(int codServico) {
        this.codServico = codServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
}
