package pacote1;

import java.sql.Date;
import java.sql.Time;

public class Reserva {

    private int id;
    private int idUsuario;
    private int idQuadra;
    private Date data;
    private Time horaInicio;
    private Time horaFim;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdQuadra() {
        return idQuadra;
    }

    public void setIdQuadra(int idQuadra) {
        this.idQuadra = idQuadra;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Time horaFim) {
        this.horaFim = horaFim;
    }

    // Método para obter uma descrição legível da reserva
    public String getDescricao() {
        // Retorna uma descrição formatada da reserva
        return "Reserva " + id + " - " + data.toString() + " das " + horaInicio.toString() + " às " + horaFim.toString();
    }

    // Método para exibir informações sobre a reserva (opcional)
    @Override
    public String toString() {
        return getDescricao(); // Retorna a descrição como a string padrão
    }
}
