package io.github.joaoteixeirasilva33.clientes.logicanegocio;

import io.github.joaoteixeirasilva33.clientes.dominio.Cliente;

import javax.swing.*;
import java.util.UUID;

public class LogicaCadastroClienteFake implements Cadastro<Cliente>{
    @Override
    public void salvar(Cliente objetoCadastrado) {
        JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso");
    }

    @Override
    public Cliente buscar(UUID codigo) {
        return null;
    }

    @Override
    public void deletar(UUID codigo) {

    }

    @Override
    public void atualizar(Cliente objetoAtualizar) {

    }

    @Override
    public void imprimirResultado() {

    }
}
