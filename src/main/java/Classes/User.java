package Classes;

public class User {

    private String Username;
    private String Nombre;
    private String Password;
    private boolean Administrator;
    private boolean Author;

    public User(String username, String nombre, String password, boolean administrator, boolean author) {
        Username = username;
        Nombre = nombre;
        Password = password;
        Administrator = administrator;
        Author = author;
    }

    public User() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isAdministrator() {
        return Administrator;
    }

    public void setAdministrator(boolean administrator) {
        Administrator = administrator;
    }

    public boolean isAuthor() {
        return Author;
    }

    public void setAuthor(boolean author) {
        Author = author;
    }
}
