package LavaCar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Verificador {

    public static boolean verificarPlaca(List<Carro> carros, String placa) {
        if (placa == null || placa.isBlank()) {
            return false;
        }

        // Remove todos os caracteres não alfanuméricos e converte para maiúsculas
        placa = placa.replaceAll("\\W", "").toUpperCase();

        // Verifica o formato e formata a placa se necessário
        if (!Pattern.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}", placa) && !Pattern.matches("[A-Z]{3}[0-9]{4}",
                placa)) {
            // Formato inválido
            return false;
        }

        // Verifica se a placa já está em uso
        for (Carro carro : carros) {
            String placaVeiculo = carro.getPlaca().replaceAll("\\W", "").toUpperCase();
            if (placaVeiculo.equals(placa)) {
                return false; // A placa fornecida já está em uso
            }
        }

        return true; // A placa é válida e não está em uso
    }

    public static boolean verificarCPF(List<Cliente> clientes, String cpf) {
        if (cpf == null || cpf.isBlank()) {
            return false;
        }
        // Remove as pontuações do CPF e verifica se todos os caracteres restantes são dígitos
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            return false;
        }
        // Verifica se o CPF já está em uso
        for (Cliente cliente : clientes) {
            // Remove as pontuações do Cpf já cadastrado
            String cpfUsuario = cliente.getCpf().replaceAll("\\D", "");
            if (cpfUsuario.equals(cpf)) {
                return false; // O CPF fornecido já está em uso
            }
        }
        // Calcula e verifica os dígitos verificadores do CPF
        int digito1 = Character.getNumericValue(cpf.charAt(9));
        int digito2 = Character.getNumericValue(cpf.charAt(10));
        int soma, resto, resultado;

        soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        resto = soma % 11;
        if (resto < 2) {
            resultado = 0;
        } else {
            resultado = 11 - resto;
        }
        if (resultado != digito1) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        if (resto < 2) {
            resultado = 0;
        } else {
            resultado = 11 - resto;
        }
        return resultado == digito2;
    }

    public static boolean verificaCPF(List<Cliente> clientes, String cpf) {
        // Remove as pontuações do CPF fornecido
        cpf = cpf.replaceAll("\\D", "");

        // Verifica se o CPF existe na lista de clientes
        for (Cliente cliente : clientes) {
            // Remove as pontuações do CPF já cadastrado
            String cpfUsuario = cliente.getCpf().replaceAll("\\D", "");
            if (cpfUsuario.equals(cpf)) {
                return true; // O CPF fornecido existe.
            }
        }
        return false; // O CPF fornecido não existe.
    }

    public static boolean verificaPlaca(List<Carro> carros, String placa) {
        // Remove todos os caracteres não alfanuméricos e converte para maiúsculas
        placa = placa.replaceAll("\\W", "").toUpperCase();

        // Verifica se a placa já está em uso
        for (Carro carro : carros) {
            String placaVeiculo = carro.getPlaca().replaceAll("\\W", "").toUpperCase();
            if (placaVeiculo.equals(placa)) {
                return true; // A placa fornecida já existe.
            }
        }
        return false; // A placa fornecida não existe.
    }




    public static boolean verificarTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) {
            return false;
        }
        List<String> DDD = new ArrayList<>(List.of(
                "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "21", "22", "24", "27", "28", "31", "32", "33", "34", "35", "37", "38", "41",
                "42", "43", "44", "45", "46", "47", "48", "49", "51", "53", "54", "55", "61",
                "62", "63", "64", "65", "66", "67", "68", "69", "71", "73", "74", "75", "77",
                "79", "81", "82", "83", "84", "85", "86", "87", "88", "89", "91", "92", "93",
                "94", "95", "96", "97", "98", "99"
        ));
        telefone = telefone.replaceAll("\\D", "");
        if (telefone.length() != 11 || !telefone.matches("\\d{11}")) {
            return false;
        } else if (Character.getNumericValue(telefone.charAt(2)) != 9) {
            return false;
        } else return DDD.contains(telefone.substring(0, 2));
    }
}
