package Facade;

public class UserType {
    private String email;
    private String password;
    private boolean isLogged;

    public UserType(String email, String password) {
        this.email = email;
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Add validation for email format if needed
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        this.isLogged = logged;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isLogged=" + isLogged +
                '}';
    }
}
