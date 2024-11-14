package pacote1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public int autenticarUsuario(String nome, String senha) {
        int idUsuario = -1;  // ID inválido para caso de falha
        try {
            // Criação da conexão com o banco de dados
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/reserva_esportiva", "root", "350022");
            String sql = "SELECT id FROM usuario WHERE nome = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            // Verificação se o usuário foi encontrado
            if (rs.next()) {
                idUsuario = rs.getInt("id");  // Retorna o ID do usuário
            }

            // Fechando a conexão
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idUsuario;
    }
}


