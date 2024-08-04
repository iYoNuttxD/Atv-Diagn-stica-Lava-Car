package LavaCar;

import java.util.List;

public class Carro {
    private int codCarro;
    private String tipo;
    private String marca;
    private String modelo;
    private String placa;
    private Cliente dono;

    public Carro(int codCarro, String tipo, String marca, String modelo, String placa, Cliente dono) {
        this.codCarro = codCarro;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.dono = dono;
    }

    public static void listarCarros(List<Carro> carros) {
        for (Carro carro : carros) {
            exibirInfo(carro);
            System.out.println();
        }
    }

    public static void exibirInfo(Carro carro) {
        System.out.println("Tipo do carro: " + carro.getTipo());
        System.out.println("Modelo do carro: " + carro.getModelo());
        System.out.println("Placa do carro: " + carro.getPlaca());
        System.out.println("Dono do carro: " + carro.getDono().getNome());
    }


    public int getCodCarro() {
        return codCarro;
    }

    public void setCodCarro(int codCarro) {
        this.codCarro = codCarro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
