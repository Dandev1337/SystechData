package br.com.systechdata.app.form;

import br.com.systechdata.app.controller.TecnicoController;
import br.com.systechdata.app.entity.Tecnico;
import br.com.systechdata.app.table.TecnicoCellRender;
import br.com.systechdata.app.table.TecnicoTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

public class TecnicoForm extends JFrame{
        private JPanel TecnicoPanel;
        private JPanel gerPanel;
        private Long codTecnico;
        private List<Tecnico> tecnicoList;
    private JLabel lbNome;
    private JTextField txtNome;
    private JLabel lbCPF;
    private JTextField txtCPF;
    private JLabel lbEndereco;
    private JTextField txtEndereco;
    private JLabel lbTelefone;
    private JTextField txtTelefone;
    private JLabel lbUser;
    private JTextField txtUser;
    private JLabel lbPassword;
    private JPasswordField txtSenha;
    private JPanel btnPanel;
    private JButton saveBTN;
    private JButton editBTN;
    private JButton cancelBTN;
    private JButton newBTN;
    private JButton removeBTN;
    private JButton backBTN;
    private JPanel voltarPanel;
    private JTextField txtSearch;
    private JLabel lbPesquisar;
    private JPanel listaPanel;
    private JTable tecnicoTable;
    private JScrollPane tecnicoScrollPane;
    private JPanel searchPanel;

    public TecnicoForm() throws HeadlessException{
        super("Cadastro de Tecnicos");
        this.setTitle("Controle de Técnicos");
        this.setBounds(650, 450, 250, 250);
        this.setSize(800, 700);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo((Component)null);
        add(TecnicoPanel);
        atualizarTabelaTecnico(new TecnicoController().findTecnicos());
        refreshTable();
        enableFields(false);
        this.setVisible(true);
        searchPanel.setBounds(10,500,100,25);
        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSaveTecnico();
            }
        });
        cancelBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onNewTecnico();
            }
        });
        removeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRemoveTecnico();
            }
        });
        editBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEditTecnico();
            }
        });
        backBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardForm dashboardForm = new DashboardForm();
                dashboardForm.setVisible(true);
                dispose();
            }
        });
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                Search();
            }
        });
    }

    private void onEditTecnico() {
        int rowIndex = tecnicoTable.getSelectedRow();

        if(rowIndex == -1){
            JOptionPane.showMessageDialog(this, "Selecione o Tecnico a ser alterado");
            return;
        }
        Tecnico tecnico = new TecnicoTableModel(tecnicoList).get(rowIndex);

        codTecnico = tecnico.getCodTecnico();

        txtNome.setText(tecnico.getNome());
        txtCPF.setText(tecnico.getCpf());
        txtEndereco.setText(tecnico.getEndereco());
        txtTelefone.setText(tecnico.getTelefone());
        txtUser.setText(tecnico.getUsuario());
        txtSenha.setText(tecnico.getSenha());

        enableFields(true);
    }

    private void onRemoveTecnico() {
        int rowIndex = tecnicoTable.getSelectedRow();

        if(rowIndex == -1){
            JOptionPane.showMessageDialog(this, "Selecione o Tecnico a ser removido");
            return;
        }

        Tecnico tecnico = new TecnicoTableModel(tecnicoList).get(rowIndex);

        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o campo selecionado?", "Ecluir Técnico", JOptionPane.YES_NO_OPTION);
        if(confirm != 0){
            return;
        }
        int result = new TecnicoController().removeTecnico(tecnico.getCodTecnico());

        if (result == 1){
            JOptionPane.showMessageDialog( null , "Técnico removido com sucesso!");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(null ,"Tente novamente");
        }
    }

    private void onNewTecnico() {
        enableFields(true);
    }

    private void onSaveTecnico() {
        Tecnico tecnico = new Tecnico();
        if(txtNome.getText().length()>0 && txtCPF.getText().length()>0 && txtEndereco.getText().length()>0 && txtTelefone.getText().length()>0 && txtUser.getText().length()>0 && txtSenha.getText().length()>0){
            tecnico.setNome(txtNome.getText());
            tecnico.setCpf(txtCPF.getText());
            tecnico.setEndereco(txtEndereco.getText());
            tecnico.setTelefone(txtTelefone.getText());
            tecnico.setUsuario(txtUser.getText());
            tecnico.setSenha(txtSenha.getText());
        }else {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!");
            return;
        }
        int result = 0;
        if(codTecnico == null){
            result = new TecnicoController().addTecnico(tecnico);
        } else {
            tecnico.setCodTecnico(codTecnico);
            result = new TecnicoController().updateTecnico(tecnico);
            codTecnico = null;
        }
        if (result == 1){
            JOptionPane.showMessageDialog( null , "Valor inserido com sucesso!");
            enableFields(false);
            onCancel();
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(null ,"Dados incorretos, Tente novamente");
        }

    }
    private void onCancel(){
        txtNome.setText("");
        txtCPF.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtUser.setText("");
        txtSenha.setText("");
        enableFields(false);
    }
    private void enableFields(boolean b){
        txtNome.setEnabled(b);
        txtCPF.setEnabled(b);
        txtEndereco.setEnabled(b);
        txtTelefone.setEnabled(b);
        txtUser.setEnabled(b);
        txtSenha.setEnabled(b);
    }
    private void refreshTable(){
        tecnicoList = new TecnicoController().findTecnicos();
        if (tecnicoList != null){
            tecnicoTable.setModel(new TecnicoTableModel(tecnicoList));
            tecnicoTable.setDefaultRenderer(Object.class, new TecnicoCellRender());
        }
    }
    private void Search() {
        String pesquisar = txtSearch.getText();
        if (pesquisar.isEmpty()) {
            atualizarTabelaTecnico(new TecnicoController().findTecnicos());
        } else {
            List<Tecnico> tecnicoTemp =new TecnicoController().findTecnicos().stream().filter((Tecnico c) ->{
                return c.getNome().toLowerCase().contains(pesquisar.toLowerCase()) ||
                        c.getCpf().toLowerCase().contains(pesquisar.toLowerCase()) ||
                        c.getEndereco().toLowerCase().contains(pesquisar.toLowerCase());}).collect(Collectors.toList());

            atualizarTabelaTecnico(tecnicoTemp);
        }

    }

    private void atualizarTabelaTecnico(List<Tecnico> tecnicos) {
        TecnicoTableModel tecnicoTableModel = new TecnicoTableModel(tecnicos);
        tecnicoTable.setModel(tecnicoTableModel);
        tecnicoTable.setDefaultRenderer(Object.class, new TecnicoCellRender());

    }




}
