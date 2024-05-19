package io.github.joaoteixeirasilva33.clientes.dominio;

import io.github.joaoteixeirasilva33.clientes.dominio.enums.TipoSexo;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Cliente {
    private UUID codigo;
    private String nome;
    private String cpf;
    private TipoSexo sexo;
    private byte[] foto;

    public Cliente() {
        this.codigo = UUID.randomUUID();
    }

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
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

    public String getSexo() {
        return sexo != null ? sexo.getDescricao() : null;
    }

    public void setSexo(TipoSexo sexo) {
        this.sexo = sexo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", sexo=" + sexo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(codigo, cliente.codigo) &&
                Objects.equals(nome, cliente.nome) &&
                Objects.equals(cpf, cliente.cpf) &&
                sexo == cliente.sexo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, cpf, sexo);
    }
}