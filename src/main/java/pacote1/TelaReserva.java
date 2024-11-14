package pacote1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class TelaReserva extends javax.swing.JFrame {

    private JFormattedTextField txtDataReserva;
    private JSpinner spnHoraInicio;
    private JSpinner spnHoraFim;
    private JComboBox<String> cbQuadra;
    private JComboBox<String> cbEquipamentos;

    public TelaReserva() {
        initComponents();
        carregarQuadras();
        carregarEquipamentos();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Componentes
        JLabel lblTitulo = new JLabel("Tela de Reservas", SwingConstants.CENTER);
        JLabel lblDataReserva = new JLabel("Data da Reserva:");
        JLabel lblHoraInicio = new JLabel("Hora de Início:");
        JLabel lblHoraFim = new JLabel("Hora de Fim:");
        JLabel lblQuadra = new JLabel("Quadra:");
        JLabel lblEquipamentos = new JLabel("Equipamentos:");

        // Campo para data
        txtDataReserva = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        txtDataReserva.setValue(new Date()); // Define a data atual como padrão

        // Configuração de Spinners para hora
        spnHoraInicio = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorHoraInicio = new JSpinner.DateEditor(spnHoraInicio, "HH:mm");
        spnHoraInicio.setEditor(editorHoraInicio);

        spnHoraFim = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorHoraFim = new JSpinner.DateEditor(spnHoraFim, "HH:mm");
        spnHoraFim.setEditor(editorHoraFim);

        cbQuadra = new JComboBox<>();
        cbEquipamentos = new JComboBox<>();

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnVoltar = new JButton("Voltar");

        // Listeners
        btnConfirmar.addActionListener(evt -> confirmarReserva());
        btnVoltar.addActionListener(evt -> voltar());

        // Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblDataReserva)
                            .addGap(18, 18, 18)
                            .addComponent(txtDataReserva, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblHoraInicio)
                            .addGap(18, 18, 18)
                            .addComponent(spnHoraInicio, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblHoraFim)
                            .addGap(18, 18, 18)
                            .addComponent(spnHoraFim, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblQuadra)
                            .addGap(18, 18, 18)
                            .addComponent(cbQuadra, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblEquipamentos)
                            .addGap(18, 18, 18)
                            .addComponent(cbEquipamentos, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                            .addComponent(btnConfirmar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))))
                .addGap(10)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataReserva)
                    .addComponent(txtDataReserva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoraInicio)
                    .addComponent(spnHoraInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoraFim)
                    .addComponent(spnHoraFim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuadra)
                    .addComponent(cbQuadra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEquipamentos)
                    .addComponent(cbEquipamentos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar)
                    .addComponent(btnConfirmar))
                .addContainerGap(20, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void confirmarReserva() {
        String dataReservaText = txtDataReserva.getText();
        Date horaInicio = (Date) spnHoraInicio.getValue();
        Date horaFim = (Date) spnHoraFim.getValue();
        String quadraNome = (String) cbQuadra.getSelectedItem();
        String equipamentoNome = (String) cbEquipamentos.getSelectedItem(); // Equipamento selecionado

        String dataReserva;
        try {
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatoSaida = new SimpleDateFormat("yyyy-MM-dd");
            Date data = formatoEntrada.parse(dataReservaText);
            dataReserva = formatoSaida.format(data);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Erro ao converter a data.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idQuadra = obterIdQuadra(quadraNome);
        if (idQuadra == -1) {
            JOptionPane.showMessageDialog(this, "Quadra não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idEquipamento = obterIdEquipamento(equipamentoNome); // Obtém o ID do equipamento existente
        if (idEquipamento == -1) {
            JOptionPane.showMessageDialog(this, "Equipamento não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idUsuarioLogado = SessaoUsuario.getIdUsuarioLogado();
        if (idUsuarioLogado <= 0) {
            JOptionPane.showMessageDialog(this, "Erro: usuário não está logado. Por favor, faça login para continuar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConexaoDB.getConexao()) {
            // Inserir a reserva na tabela reserva
            String sqlReserva = "INSERT INTO reserva (data, hora_inicio, hora_fim, id_quadra, id_usuario, status_pagamento) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstReserva = conn.prepareStatement(sqlReserva, PreparedStatement.RETURN_GENERATED_KEYS);
            pstReserva.setString(1, dataReserva);
            pstReserva.setTime(2, new java.sql.Time(horaInicio.getTime()));
            pstReserva.setTime(3, new java.sql.Time(horaFim.getTime()));
            pstReserva.setInt(4, idQuadra);
            pstReserva.setInt(5, idUsuarioLogado);
            pstReserva.setString(6, "pendente"); // Status de pagamento definido como "pendente"

            int rowsAffected = pstReserva.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = pstReserva.getGeneratedKeys();
                if (rs.next()) {
                    int idReserva = rs.getInt(1); // ID da reserva inserida

                    // Agora, insira o vínculo entre a reserva e o equipamento (sem gerar novo id)
                    String sqlVinculo = "INSERT INTO reserva_equipamento (id_reserva, id_equipamento, quantidade) VALUES (?, ?, ?)";
                    try (PreparedStatement pstVinculo = conn.prepareStatement(sqlVinculo)) {
                        pstVinculo.setInt(1, idReserva);
                        pstVinculo.setInt(2, idEquipamento);  // Usando o ID do equipamento existente
                        pstVinculo.setInt(3, 1);  // Definindo a quantidade como 1, caso apenas um equipamento seja reservado
                        pstVinculo.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Reserva confirmada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao confirmar a reserva.", "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private int obterIdQuadra(String quadraNome) {
        try (Connection conn = ConexaoDB.getConexao()) {
            String sql = "SELECT id FROM quadra WHERE nome = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, quadraNome);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private int obterIdEquipamento(String equipamentoNome) {
        try (Connection conn = ConexaoDB.getConexao()) {
            String sql = "SELECT id FROM equipamento WHERE nome = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, equipamentoNome);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private void carregarQuadras() {
        try (Connection conn = ConexaoDB.getConexao()) {
            String sql = "SELECT nome FROM quadra";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cbQuadra.addItem(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void carregarEquipamentos() {
        try (Connection conn = ConexaoDB.getConexao()) {
            String sql = "SELECT nome FROM equipamento";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cbEquipamentos.addItem(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void voltar() {
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        dispose();
    }
}
