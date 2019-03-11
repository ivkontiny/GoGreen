package pojos;

public class Account {
    private String username;
    private String mail;
    private String password;
    private String firstname;
    private String lastname;
    private int points;


    public Account() {

    }


    /**
     * Making a new user.
     *
     * @param username the username of the user
     * @param mail     the email of the user
     * @param password the password of the user
     * @param name     first name of the user
     * @param surname  last name of the user
     */
    public Account(String username, String mail, String password, String name, String surname) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.firstname = name;
        this.lastname = surname;
        this.points = 0;
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

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String name) {
        this.firstname = name;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String surname) {
        this.lastname = surname;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account that = (Account) obj;

            if (this.username.equals(that.username)
                    && this.mail.equals(that.mail)
                    && this.password.equals(that.password)
                    && this.firstname.equals(that.firstname)
                    && this.lastname.equals(that.lastname)) {
                return true;
            }
        }

        return false;
    }
}
