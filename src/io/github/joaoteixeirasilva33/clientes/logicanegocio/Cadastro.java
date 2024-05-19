package io.github.joaoteixeirasilva33.clientes.logicanegocio;

import io.github.joaoteixeirasilva33.clientes.dominio.exceptions.CpfInvalidoException;

import java.util.UUID;

public interface Cadastro<Tipo> {
    void salvar(Tipo objetoCadastrado) throws Exception;
    Tipo buscar(UUID codigo);
    void deletar(UUID codigo);
    void atualizar(Tipo objetoAtualizar);

    void imprimirResultado();
}
