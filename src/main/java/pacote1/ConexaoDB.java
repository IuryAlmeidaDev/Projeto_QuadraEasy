package pacote1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    // URL do banco de dados
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/reserva_esportiva";
    private static final String USUARIO = "root";  // Alterar se necessário
    private static final String SENHA = "350022";  // Alterar se necessário
    
    // Método para obter a conexão
    public static Connection getConexao() throws SQLException {
        try {
            // Registra o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL não encontrado", e);
        }
    }

    // Método para fechar a conexão (se necessário)
    public static void fecharConexao(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
