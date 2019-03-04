package server;

public class Login {

    private String username;
    private String password;
    private Boolean status;


    /** Creating a new Login object.
     * @param username the username
     * @param password the password
     * @param status true if the log in was successful, false otherwise
     */
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

    public Boolean getStatus() {
        return status;
    }

    //public String getResponse() { return response; }
}
