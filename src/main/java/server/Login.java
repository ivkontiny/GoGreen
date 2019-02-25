package server;

public class Login {

    private String username;
    private String password;
    private Boolean status;

    public Login(String username, String password, Boolean status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getStatus(){
        return status;
    }

    //public String getResponse() { return response; }
}
