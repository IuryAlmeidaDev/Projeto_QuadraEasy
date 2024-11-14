package pacote1;

import java.sql.*;

public class FeedbackDAO {

    // Método para inserir feedback no banco de dados
    public void inserirFeedback(int idReserva, String comentario, int nota) {
        String sql = "INSERT INTO feedback (id_reserva, comentario, nota) VALUES (?, ?, ?)";

        // Estabelecendo a conexão com o banco de dados
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idReserva);     // Define o parâmetro id_reserva
            stmt.setString(2, comentario); // Define o comentário
            stmt.setInt(3, nota);          // Define a nota

            // Executa a inserção no banco de dados
            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }
}
