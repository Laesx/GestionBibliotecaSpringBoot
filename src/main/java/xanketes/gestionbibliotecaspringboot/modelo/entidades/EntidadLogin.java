package xanketes.gestionbibliotecaspringboot.modelo.entidades;

public class EntidadLogin {
    private String username;
    private String password;

    public EntidadLogin() {
    }

    public EntidadLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
