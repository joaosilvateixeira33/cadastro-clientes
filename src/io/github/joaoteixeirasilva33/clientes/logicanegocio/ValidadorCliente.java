package io.github.joaoteixeirasilva33.clientes.logicanegocio;

import io.github.joaoteixeirasilva33.clientes.dominio.Cliente;
import io.github.joaoteixeirasilva33.clientes.dominio.enums.TipoSexo;
import io.github.joaoteixeirasilva33.clientes.dominio.exceptions.CpfInvalidoException;
import io.github.joaoteixeirasilva33.clientes.dominio.exceptions.DadosObrigatoriosException;

public class ValidadorCliente {
    public static void validar(Cliente cliente) throws CpfInvalidoException {
        validarDadosObrigatorios(cliente);
        validarCpf(cliente.getCpf());
    }

    private static void validarDadosObrigatorios(Cliente cliente){
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new DadosObrigatoriosException("Campo de nome não pode estar vazio.");
        }
        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new DadosObrigatoriosException("Campo de CPF não pode estar vazio.");
        }
        if (cliente.getSexo() == null || cliente.getSexo().isBlank()) {
            throw new DadosObrigatoriosException("Campo de sexo não pode estar vazio.");
        }

        if(cliente.getFoto() == null){
            throw  new DadosObrigatoriosException("Foto Obrigatoria");
        }
    }

    private static void validarCpf(String cpf) throws CpfInvalidoException {
        if (cpf.length() != 11 ) {
            throw new CpfInvalidoException("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
        }
    }
}
