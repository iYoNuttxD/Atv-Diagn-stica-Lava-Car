package LavaCar;

import java.util.List;

public class Lavagem {
    private int codLavagem;
    private Carro carro;
    private Servico servico;

    public Lavagem(int codLavagem, Carro carro, Servico servico) {
        this.codLavagem = codLavagem;
        this.carro = carro;
        this.servico = servico;
    }

    public static void listarLavagem(List<Lavagem> lavagems) {
        for (Lavagem lavagem : lavagems) {
            exibirInfo(lavagem);
            System.out.println();
        }
    }

    public static void exibirInfo(Lavagem lavagem) {
        System.out.println("Serviço: " + lavagem.getServico().getNome());
        System.out.println("Placa do carro: " + lavagem.getCarro().getPlaca());
        System.out.println("Dono do carro: " + lavagem.getCarro().getDono().getNome());
        System.out.println("Telefone do dono: " + lavagem.getCarro().getDono().getTelefone());
    }

    public int getCodLavagem() {
        return codLavagem;
    }

    public void setCodLavagem(int codLavagem) {
        this.codLavagem = codLavagem;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
