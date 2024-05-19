package io.github.joaoteixeirasilva33.clientes.logicanegocio;

import io.github.joaoteixeirasilva33.clientes.dominio.Cliente;
import io.github.joaoteixeirasilva33.clientes.dominio.exceptions.CpfInvalidoException;
import io.github.joaoteixeirasilva33.clientes.util.GerenciadorArquivos;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LogicaCadastroMemoria implements Cadastro<Cliente>{
    private List<Cliente> listaClientes;

    public LogicaCadastroMemoria() {
        this.listaClientes = new ArrayList<>();
    }

    @Override
    public void salvar(Cliente cliente) throws CpfInvalidoException {
        ValidadorCliente.validar(cliente);
        this.listaClientes.add(cliente);
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.");
        GerenciadorArquivos.persistirArquivo(cliente.getNome()  + ".jpg", cliente.getFoto());
    }

    @Override
    public Cliente buscar(UUID codigo) {
        Cliente clienteEncontrado = null;

        for(Cliente c : listaClientes){
            if(c.getCodigo().equals(codigo)){
                clienteEncontrado = c;
                break;
            }
        }

        return clienteEncontrado;
    }

    @Override
    public void deletar(UUID codigo) {
        Cliente clienteEncontrado = buscar(codigo);
        if(clienteEncontrado != null){
            this.listaClientes.remove(clienteEncontrado);
        }
    }

    @Override
    public void atualizar(Cliente cliente) {

    }

    public void imprimirResultado(){
        System.out.println("Imprimindo " + this.listaClientes.size() + " clientes:");
        for (Cliente cliente : this.listaClientes ){
            System.out.println(cliente);
        }
    }
}
