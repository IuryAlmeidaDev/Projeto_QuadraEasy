package pacote1;

import javax.swing.JOptionPane;

public class TelaPrincipal extends javax.swing.JFrame {

    // Declaração do botão jButton6
    // Cria uma nova tela para a classe
    public TelaPrincipal() {
        initComponents();
        setLocationRelativeTo(null); // Centraliza a tela
    }

    // Método para inicializar a interface gráfica
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton(); // Aqui você adiciona o botão jButton6

        jScrollPane1.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Botão Fazer Pagamento
        jButton1.setText("Fazer Pagamento");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Ação do botão: Abrir a tela de pagamento
                TelaPagamento telaPagamento = new TelaPagamento();  // Cria a nova instância da tela
                telaPagamento.setVisible(true);  // Torna a tela visível
                setVisible(false); // Oculta a tela principal
            }
        });

        jLabel1.setText("Sistema - QuadraEasy");

        // Botão Fazer Reserva
        jButton2.setText("Fazer Reserva");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Ação do botão: Abrir a tela de Fazer Reserva
                openFazerReserva();
            }
        });

        // Botão Adicionar Feedback
        jButton3.setText("Adicionar Feedback");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Ação do botão: Abrir a tela de Adicionar Feedback
                openAdicionarFeedback();
            }
        });

        // Botão Sair
        jButton4.setText("Sair");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Ação do botão: Sair do sistema
                sairSistema();
            }
        });

        // Botão Inserir Quadra
        jButton5.setText("Inserir Quadra");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Ação do botão: Abrir a tela de cadastro de quadra
                TelaCadastroQuadra telaCadastroQuadra = new TelaCadastroQuadra();
                telaCadastroQuadra.setVisible(true);
                setVisible(false); // Oculta a tela principal
            }
        });

        // Botão Inserir Equipamento
        jButton6.setText("Inserir Equipamento");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Ação do botão: Abrir a tela de cadastro de equipamento
                TelaCadastroEquipamento telaCadastroEquipamento = new TelaCadastroEquipamento();
                telaCadastroEquipamento.setVisible(true);
                setVisible(false); // Oculta a tela principal
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)))
                .addGap(168, 168, 168))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton6) // Adicionei o botão jButton6 aqui
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }

    // Ação ao clicar no botão "Fazer Reserva"
    private void openFazerReserva() {
        TelaReserva telaReservar = new TelaReserva(); // Cria uma nova instância da tela de reserva
        telaReservar.setVisible(true); // Torna a tela de reserva visível
        setVisible(false); // Oculta a tela principal
    }

    // Método para abrir a TelaFeedback
    private void openAdicionarFeedback() {
        TelaFeedback telaFeedback = new TelaFeedback(); // Cria uma nova instância da tela de feedback
        telaFeedback.setVisible(true); // Torna a tela de feedback visível
        this.dispose(); // Fecha a tela principal
    }

    // Ação ao clicar no botão "Sair"
    private void sairSistema() {
        int confirmed = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            System.exit(0); // Fecha o sistema
        }
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Cria e exibe a interface gráfica */
        java.awt.EventQueue.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }

    // Declaração de variáveis - Não modifique
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6; // Aqui é a declaração do jButton6
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // Fim da declaração de variáveis
}
