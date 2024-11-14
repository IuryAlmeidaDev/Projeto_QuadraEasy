package pacote1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    // Método para buscar reservas do usuário
    public List<Reserva> buscarReservasPorUsuario(int idUsuario) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva WHERE id_usuario = ?";

        // Estabelecendo a conexão com o banco de dados
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);  // Define o parâmetro de id_usuario
            ResultSet rs = stmt.executeQuery();  // Executa a consulta

            // Processa o resultado da consulta
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setIdUsuario(rs.getInt("id_usuario"));
                reserva.setIdQuadra(rs.getInt("id_quadra"));
                reserva.setData(rs.getDate("data"));
                reserva.setHoraInicio(rs.getTime("hora_inicio"));
                reserva.setHoraFim(rs.getTime("hora_fim"));
                // Adiciona a reserva à lista
                reservas.add(reserva);
            }

        } catch (SQLException e) {
        }
        return reservas;  // Retorna a lista de reservas
    }
}
