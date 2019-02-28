package client;

public class User {
    private String username;
    private String mail;
    private String password;
    private String name;
    private String surname;
    public User()
    {

    }
    public User(String username, String mail, String password, String name, String surname)
    {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
