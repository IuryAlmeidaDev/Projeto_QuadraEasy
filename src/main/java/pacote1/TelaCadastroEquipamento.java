package pacote1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TelaCadastroEquipamento extends javax.swing.JFrame {

    public TelaCadastroEquipamento() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();  // Novo JLabel para o valor por hora
        jTextField4 = new javax.swing.JTextField();  // Novo JTextField para o valor por hora

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome do Equipamento");
        jLabel4.setText("Tipo do Equipamento");
        jLabel5.setText("Valor por Hora"); // Nova label explicativa

        jButton1.setText("Voltar");
        jButton2.setText("Cadastrar");

        // Definir o layout para a tela usando GroupLayout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100) // Definir espaço entre os elementos
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)  // Exibindo o campo "Valor por Hora"
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE) // Campo para o valor por hora
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addGap(100, 100, 100)) // Espaçamento nas bordas
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80) // Espaço superior
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)  // Exibindo o campo "Valor por Hora"
                .addGap(18, 18, 18)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE) // Campo para o valor por hora
                .addGap(30, 30, 30) // Espaço entre os campos e os botões
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(80, 80, 80)) // Espaço inferior
        );

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarEquipamento(evt);
            }
        });

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarTelaAnterior(evt);
            }
        });

        pack();
    }// </editor-fold>                        

    private void cadastrarEquipamento(java.awt.event.ActionEvent evt) {
        // Recuperando os valores dos campos de texto
        String nome = jTextField2.getText();
        String tipo = jTextField3.getText();
        String valorHoraStr = jTextField4.getText(); // Novo campo para o valor por hora

        // Verificando se os campos não estão vazios
        if (nome.isEmpty() || tipo.isEmpty() || valorHoraStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double valorHora = Double.parseDouble(valorHoraStr); // Convertendo para double

            // Conexão com o banco de dados
            Connection conn = null;
            PreparedStatement pst = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/reserva_esportiva", "root", "350022");
                String sql = "INSERT INTO equipamento (nome, tipo, valor_hora) VALUES (?, ?, ?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, nome);
                pst.setString(2, tipo);
                pst.setDouble(3, valorHora); // Passando o valor por hora

                // Executando a inserção
                int rowsInserted = pst.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Equipamento cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    // Limpar campos
                    jTextField2.setText("");
                    jTextField3.setText("");
                    jTextField4.setText(""); // Limpar o campo de valor por hora
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar equipamento: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if (pst != null) pst.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O valor por hora deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltarTelaAnterior(java.awt.event.ActionEvent evt) {
        this.dispose(); // Fecha a tela de cadastro de equipamento
        TelaPrincipal telaPrincipal = new TelaPrincipal(); // Instancia a TelaPrincipal
        telaPrincipal.setVisible(true); // Exibe a TelaPrincipal
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaCadastroEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroEquipamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;  // Variável para o JLabel do valor por hora
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;  // Variável para o JTextField do valor por hora
    // End of variables declaration                   
}
