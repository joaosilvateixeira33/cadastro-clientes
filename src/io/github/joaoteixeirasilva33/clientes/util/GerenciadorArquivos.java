package io.github.joaoteixeirasilva33.clientes.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GerenciadorArquivos {
    public static final String pastaFotos = "C:\\Users\\jhonny\\Desktop\\cursos\\dio\\java-backend\\cadastro-clientes\\src\\io\\github\\joaoteixeirasilva33\\clientes\\view\\uploads\\";
    public static void persistirArquivo(String nome, byte[] bytes){
        try{
            Path path = Paths.get(pastaFotos + nome);
            Files.write(path, bytes);
        }catch (IOException e){
            System.out.println("Ocorreu um erro " + e.getMessage());
        }
    }
}
