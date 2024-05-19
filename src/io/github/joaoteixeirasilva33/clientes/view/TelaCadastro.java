package io.github.joaoteixeirasilva33.clientes.view;

import io.github.joaoteixeirasilva33.clientes.dominio.Cliente;
import io.github.joaoteixeirasilva33.clientes.dominio.enums.TipoSexo;
import io.github.joaoteixeirasilva33.clientes.dominio.exceptions.CpfInvalidoException;
import io.github.joaoteixeirasilva33.clientes.logicanegocio.Cadastro;
import io.github.joaoteixeirasilva33.clientes.logicanegocio.LogicaCadastroMemoria;
import io.github.joaoteixeirasilva33.clientes.logicanegocio.ValidadorCliente;
import io.github.joaoteixeirasilva33.clientes.util.ConversorIconParaByteArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TelaCadastro extends JFrame {
    private JLabel labelNome, labelCpf, labelSexo, labelFoto;
    private JTextField campoNome, campoCpf;
    private JComboBox<TipoSexo> campoSexo;

    private JButton botaoSalvar, botaoEscolherFoto;

    private Cadastro<Cliente> logicaCadastro;

    public TelaCadastro(){
        this.logicaCadastro = new LogicaCadastroMemoria();
        contruirTela();
    }

    private void contruirTela(){
        setSize(600, 500);
        setTitle("Cadastro de Cliente");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        this.adicionarCampo();
        this.adicionarBotoes();
        this.adicionarComponenteFoto();
    }

    private void adicionarCampo(){
        labelNome = new JLabel("Nome:");
        labelNome.setBounds(20, 20, 200, 20);
        getContentPane().add(labelNome);

        campoNome = new JTextField();
        campoNome.setBounds(20, 40, 200, 20);
        getContentPane().add(campoNome);

        labelCpf = new JLabel("CPF:");
        labelCpf .setBounds(20, 60, 200, 20);
        getContentPane().add(labelCpf);

        campoCpf = new JTextField();
        campoCpf.setBounds(20, 80, 200, 20);
        getContentPane().add(campoCpf);

        labelSexo = new JLabel("Sexo:");
        labelSexo .setBounds(20, 100, 200, 20);
        getContentPane().add(labelSexo);

        TipoSexo[] tiposSexo = {null, TipoSexo.M, TipoSexo.F, TipoSexo.O};
        campoSexo = new JComboBox<>(tiposSexo);
        campoSexo.setBounds(20, 120, 200, 20);
        getContentPane().add(campoSexo);
    }

    private void adicionarBotoes(){
        botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBounds(20, 160, 100, 20);

        ActionListener acaoDoBotaoSalvar = this.botaoSalvarActionListener();
        botaoSalvar.addActionListener(acaoDoBotaoSalvar);

        getContentPane().add(botaoSalvar);
    }

    private void adicionarComponenteFoto(){
        ImageIcon imageIcon = getImageIcon();

        labelFoto = new JLabel();
        labelFoto.setIcon(imageIcon);
        labelFoto.setBounds(240, 0 , 200, 200);

        botaoEscolherFoto = new JButton("Alterar Foto");
        botaoEscolherFoto.setBounds(260, 200, 160, 20);

        botaoEscolherFoto.addActionListener(botaoEscolherFotoActionListener());

        getContentPane().add(labelFoto);
        getContentPane().add(botaoEscolherFoto);
    }

    private ImageIcon getImageIcon() {
        URL localizacaoImagem = getClass().getResource("/io/github/joaoteixeirasilva33/clientes/view/padrao.png");
        ImageIcon imageIcon = new ImageIcon(localizacaoImagem);

        Image imagemRedmensionada = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

        imageIcon = new ImageIcon(imagemRedmensionada);
        return imageIcon;
    }

    private ActionListener botaoEscolherFotoActionListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int opcao = fileChooser.showOpenDialog(TelaCadastro.this);

                if(opcao == JFileChooser.APPROVE_OPTION){
                    File arquivoSelecionado = fileChooser.getSelectedFile();
                    String caminho = arquivoSelecionado.getAbsolutePath();

                    ImageIcon imageIcon = new ImageIcon(caminho);
                    labelFoto.setIcon(imageIcon);
                }
            }
        };
    }

    private ActionListener botaoSalvarActionListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente();
                cliente.setNome(campoNome.getText());
                cliente.setCpf(campoCpf.getText());
                TipoSexo tipoSexoSelecionado = (TipoSexo) campoSexo.getSelectedItem();
                cliente.setSexo(tipoSexoSelecionado);

                try {
                    byte[] byteArray = ConversorIconParaByteArray.converter(labelFoto.getIcon());
                    cliente.setFoto(byteArray);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


                try {
                    logicaCadastro.salvar(cliente);
                    campoNome.setText("");
                    campoCpf.setText("");
                    campoSexo.setSelectedIndex(0);

                    labelFoto.setIcon(TelaCadastro.this.getImageIcon());

                    JOptionPane.showMessageDialog(TelaCadastro.this, "Cliente salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    logicaCadastro.imprimirResultado();
                } catch (CpfInvalidoException ex) {
                    JOptionPane.showMessageDialog(TelaCadastro.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        };
    }
}
