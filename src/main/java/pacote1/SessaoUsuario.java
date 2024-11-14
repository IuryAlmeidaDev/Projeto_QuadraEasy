package pacote1;// Classe para armazenar o ID do usuário logado globalmente



public class SessaoUsuario {
    private static int idUsuarioLogado;

    public static void setIdUsuarioLogado(int id) {
        idUsuarioLogado = id;
    }

    public static int getIdUsuarioLogado() {
        return idUsuarioLogado;
    }
}
