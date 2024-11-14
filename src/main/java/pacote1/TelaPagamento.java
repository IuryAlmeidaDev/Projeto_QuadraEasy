package pacote1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TelaPagamento extends javax.swing.JFrame {

    public TelaPagamento() {
        initComponents();
        setLocationRelativeTo(null);
        carregarDetalhesReserva();  // Carregar os detalhes das reservas pendentes ao abrir a tela
    }

    private void initComponents() {
        jLabelValorTotal = new javax.swing.JLabel();
        jButtonConfirmarPagamento = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();  // Definindo o botão Voltar

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonConfirmarPagamento.setText("Confirmar Pagamento");
        jButtonConfirmarPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarPagamento();
            }
        });

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarParaTelaPrincipal();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelValorTotal)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonConfirmarPagamento)
                        .addGap(20, 20, 20)  // Espaço entre os botões
                        .addComponent(jButtonVoltar))
                )
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabelValorTotal)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConfirmarPagamento)
                    .addComponent(jButtonVoltar))  // Alinhando os botões
                .addGap(80, 80, 80))
        );

        pack();
    }

    // Método para voltar à TelaPrincipal
    private void voltarParaTelaPrincipal() {
        this.dispose();
        new TelaPrincipal().setVisible(true);
    }

    private boolean verificarReservasPendentes() {
        boolean reservasPendentes = false;

        // Abrir a conexão com o banco de dados
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/reserva_esportiva", "root", "350022")) {
            // Query para verificar se o usuário tem reservas pendentes
            String sql = "SELECT id FROM reserva WHERE status_pagamento = 'pendente' AND id_usuario = ?";

            // Preparar a statement
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, SessaoUsuario.getIdUsuarioLogado());  // Usar o ID do usuário logado

            // Executar a query
            ResultSet rs = pst.executeQuery();

            // Verificar se existem reservas pendentes
            if (rs.next()) {
                reservasPendentes = true;  // Se encontrar alguma reserva pendente, define como verdadeiro
            }

        } catch (SQLException e) {
            // Exibir erro de banco de dados se ocorrer
            JOptionPane.showMessageDialog(this, "Erro ao verificar reservas pendentes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return reservasPendentes;
    }

    // Método para calcular o valor total de todas as reservas pendentes do usuário logado
    private double calcularValorTotalReservasPendentes() {
        double valorTotal = 0.0;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/reserva_esportiva", "root", "350022")) {
            String sql = "SELECT q.valor_hora, r.hora_inicio, r.hora_fim, e.valor_hora " +
                         "FROM reserva r " +
                         "JOIN quadra q ON r.id_quadra = q.id " +
                         "JOIN reserva_equipamento re ON r.id = re.id_reserva " +
                         "JOIN equipamento e ON re.id_equipamento = e.id " +
                         "WHERE r.status_pagamento = 'pendente' AND r.id_usuario = ?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, SessaoUsuario.getIdUsuarioLogado()); // Usar o ID do usuário logado
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                double valorPorHoraQuadra = rs.getDouble("valor_hora");

                // Calculando a diferença de horas entre hora_fim e hora_inicio (tipo TIME)
                String horaInicio = rs.getString("hora_inicio");
                String horaFim = rs.getString("hora_fim");

                int horaInicioMinutos = converterHoraParaMinutos(horaInicio);
                int horaFimMinutos = converterHoraParaMinutos(horaFim);
                int horasReservadas = (horaFimMinutos - horaInicioMinutos) / 60;  // Convertendo de minutos para horas

                double valorEquipamentos = rs.getDouble("valor_hora");  // Valor por hora do equipamento

                // Somar o valor total da reserva (quadra + equipamentos)
                valorTotal += (valorPorHoraQuadra * horasReservadas) + (valorEquipamentos * horasReservadas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao calcular valor das reservas pendentes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return valorTotal;
    }

    // Método para converter a hora no formato TIME (HH:mm:ss) para minutos
    private int converterHoraParaMinutos(String hora) {
        String[] partes = hora.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        return (horas * 60) + minutos;
    }

    // Carregar os detalhes das reservas pendentes para exibir o valor total
    private void carregarDetalhesReserva() {
        double valorTotal = calcularValorTotalReservasPendentes();
        jLabelValorTotal.setText("Valor Total: R$ " + String.format("%.2f", valorTotal));
    }

    private void confirmarPagamento() {
        double valorTotal = calcularValorTotalReservasPendentes();  // Calcular o valor total das reservas pendentes

        // Confirmar o pagamento com uma janela pop-up
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja confirmar o pagamento de R$ " + String.format("%.2f", valorTotal) + "?", "Confirmação de Pagamento", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            // Verificar se existem reservas pendentes para o usuário logado
            if (verificarReservasPendentes()) {
                // Inserir o pagamento na tabela de pagamento
                inserirPagamento(SessaoUsuario.getIdUsuarioLogado(), valorTotal);

                // Atualizar o status de todas as reservas pendentes para 'pago'
                atualizarStatusPagamento("pago", valorTotal);

                JOptionPane.showMessageDialog(this, "Pagamento confirmado com sucesso! Todas as reservas foram finalizadas.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // Ao invés de fechar a janela, você pode limpar os campos ou manter a tela aberta
                jLabelValorTotal.setText("Pagamento realizado com sucesso!");
                jButtonConfirmarPagamento.setEnabled(false);  // Desabilita o botão de confirmar pagamento após o pagamento ser feito
                jButtonVoltar.setText("Fechar");
            } else {
                JOptionPane.showMessageDialog(this, "Não há reservas pendentes para o usuário logado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pagamento não confirmado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inserirPagamento(int idUsuario, double valorPago) {
        // Inserir o pagamento na tabela "pagamento"
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/reserva_esportiva", "root", "350022")) {
            String sqlPagamento = "INSERT INTO pagamento (id_usuario, valor_pago, status_pagamento) VALUES (?, ?, ?)";
            PreparedStatement pstPagamento = conn.prepareStatement(sqlPagamento);
            pstPagamento.setInt(1, idUsuario);  // ID do usuário logado
            pstPagamento.setDouble(2, valorPago);  // Valor pago
            pstPagamento.setString(3, "pago");  // Status do pagamento (confirmado)

            int rowsInserted = pstPagamento.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Pagamento registrado com sucesso na tabela 'pagamento'.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar pagamento: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarStatusPagamento(String status, double valorPago) {
        // Atualizar o status de pagamento de todas as reservas pendentes
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/reserva_esportiva", "root", "350022")) {
            String sql = "UPDATE reserva SET status_pagamento = ?, valor_pago = ? WHERE status_pagamento = 'pendente' AND id_usuario = ?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, status);
            pst.setDouble(2, valorPago);
            pst.setInt(3, SessaoUsuario.getIdUsuarioLogado());  // Usar o ID do usuário logado

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Status do pagamento atualizado para todas as reservas.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar status de pagamento: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private javax.swing.JButton jButtonConfirmarPagamento;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JLabel jLabelValorTotal;
}
