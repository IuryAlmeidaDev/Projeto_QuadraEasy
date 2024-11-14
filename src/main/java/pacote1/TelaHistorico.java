package pacote1;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaHistorico extends javax.swing.JFrame {

    private javax.swing.JTable jTable1;
    private javax.swing.JScrollPane jScrollPane1;  // Declaração do JScrollPane

    public TelaHistorico() {
        initComponents();
        carregarUsuarios();  // Chama o método que carrega os usuários ao iniciar
    }

    private void carregarUsuarios() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);  // Limpa as linhas da tabela antes de adicionar os novos dados

        // Conectando ao banco de dados e buscando os dados da tabela 'usuario'
        try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/reserva_esportiva", "root", "350022");
             Statement stmt = con.createStatement()) {

            String query = "SELECT * FROM usuario";  // SQL para buscar todos os usuários
            ResultSet rs = stmt.executeQuery(query);

            // Preenchendo a tabela com os dados retornados
            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                
                // Adiciona uma linha na tabela com os dados do usuário
                model.addRow(new Object[]{nome, email});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Inicialização do código dos componentes da tela
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();  // Inicializa o JScrollPane

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Nome", "Email"}  // Definindo as colunas da tabela para 'Nome' e 'Email'
        ));
        jScrollPane1.setViewportView(jTable1);  // Associando o JScrollPane à tabela

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaHistorico().setVisible(true));
    }
}
