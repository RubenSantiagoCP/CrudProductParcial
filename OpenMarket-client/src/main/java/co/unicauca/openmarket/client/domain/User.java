package co.unicauca.openmarket.client.domain;

/**
 *
 * @author Naren Imbachi
 */
public class User {
    //Atributos
    private String login;
    private String password;
    private String username;
    
    //Constructor

    public User(String login, String password, String username) {
        this.login = login;
        this.password = password;
        this.username = username;
    }
    
    //Getters y Setters
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
}
