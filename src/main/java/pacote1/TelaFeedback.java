package pacote1;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class TelaFeedback extends javax.swing.JFrame {

    // Componentes da tela
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea jTextAreaComentario;
    private javax.swing.JComboBox<String> jComboBoxReservas;
    private javax.swing.JComboBox<String> comboNota;
    private javax.swing.JLabel jLabel2;

    public TelaFeedback() {
        initComponents();
        setLocationRelativeTo(null);
    }

private void initComponents() {
    // Inicialização dos componentes
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
        JLabel jLabelNota = new javax.swing.JLabel(); // Novo JLabel para "Nota da Avaliação"
    jComboBoxReservas = new javax.swing.JComboBox<>();
    jTextAreaComentario = new javax.swing.JTextArea();
    comboNota = new javax.swing.JComboBox<>();
    jButtonVoltar = new javax.swing.JButton();
    jButtonAdicionar = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    // Título da tela
    jLabel1.setText("Adicionar Feedback");

    // Título do campo de comentário
    jLabel2.setText("Comentário");

    // Adicionando o novo JLabel com o texto "Nota da Avaliação"
    jLabelNota.setText("Nota da Avaliação");

    // Configurando a ComboBox para as reservas (carregadas dinamicamente)
    carregarReservasDoUsuario();

    // Configuração do JComboBox para a nota
    comboNota.addItem("1");
    comboNota.addItem("2");
    comboNota.addItem("3");
    comboNota.addItem("4");
    comboNota.addItem("5");

    // Botão Voltar
    jButtonVoltar.setText("Voltar");
    jButtonVoltar.addActionListener(evt -> voltarActionPerformed(evt));

    // Botão Adicionar
    jButtonAdicionar.setText("Adicionar");
    jButtonAdicionar.addActionListener(evt -> enviarFeedbackActionPerformed(evt));

    // Layout do JFrame
    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(120, 120, 120)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jButtonVoltar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAdicionar))
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jComboBoxReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextAreaComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelNota)  // Novo JLabel
                .addComponent(comboNota, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(120, 120, 120))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(60, 60, 60)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jComboBoxReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jTextAreaComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabelNota)  // Coloca o texto "Nota da Avaliação" acima do JComboBox de notas
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(comboNota, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonVoltar)
                .addComponent(jButtonAdicionar))
            .addContainerGap(60, Short.MAX_VALUE))
    );

    pack();
}


    // Método que carrega as reservas feitas pelo usuário no JComboBox
    private void carregarReservasDoUsuario() {
        int idUsuario = SessaoUsuario.getIdUsuarioLogado(); // Obtém o ID do usuário logado
        ReservaDAO reservaDAO = new ReservaDAO();
        List<Reserva> reservas = reservaDAO.buscarReservasPorUsuario(idUsuario);

        // Limpar o JComboBox antes de adicionar as novas opções
        jComboBoxReservas.removeAllItems();

        // Adicionar as reservas ao JComboBox
        for (Reserva reserva : reservas) {
            // Supondo que o ID da reserva é adicionado ao JComboBox como item oculto
            // O valor do JComboBox será a descrição, mas o ID será recuperado internamente.
            jComboBoxReservas.addItem(reserva.getDescricao());
            jComboBoxReservas.setActionCommand(String.valueOf(reserva.getId())); // Armazena o ID da reserva
        }
    }

    // Método que envia o feedback para o banco de dados
    private void enviarFeedbackActionPerformed(java.awt.event.ActionEvent evt) {
        String comentario = jTextAreaComentario.getText();
        String nota = comboNota.getSelectedItem().toString();
        String reservaSelecionada = (String) jComboBoxReservas.getSelectedItem();

        if (reservaSelecionada == null || reservaSelecionada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma reserva!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (comentario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha o comentário!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/reserva_esportiva", "root", "350022");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO feedback (id_reserva, comentario, nota) VALUES (?, ?, ?)")) {

            // Recupera o ID da reserva selecionada
            String reservaIdStr = jComboBoxReservas.getActionCommand();
            int reservaId = Integer.parseInt(reservaIdStr);

            // Preenche os parâmetros da consulta
            stmt.setInt(1, reservaId); // Agora usando o ID da reserva
            stmt.setString(2, comentario);
            stmt.setString(3, nota);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Feedback enviado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos(); // Limpar os campos após o envio
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao enviar feedback: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpar os campos após o envio do feedback
    private void limparCampos() {
        jTextAreaComentario.setText("");
        comboNota.setSelectedIndex(0);
        jComboBoxReservas.setSelectedIndex(0); // Limpar a seleção da reserva
    }
    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {
    // Fecha a tela atual
    this.dispose();
    
    // Abre a TelaPrincipal (ou qualquer outra tela que você queira abrir)
    TelaPrincipal telaPrincipal = new TelaPrincipal(); // Ou a tela que você deseja abrir
    telaPrincipal.setVisible(true);
}


    // Método main para testar a tela
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaFeedback().setVisible(true));
    }
}
