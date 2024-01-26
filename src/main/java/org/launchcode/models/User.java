package org.launchcode.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {
    @NotNull(message = "Username required.")
    @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters.")
    private String username;
    @Email (message = "Email format not recognized.")
    private String email;
    @NotNull(message = "Password required.")
    @Size(min = 6, message = "Password must be longer than 6 characters.")
    private String password;
    @NotNull(message = "Password does not not match.")
    private String verifyPassword;

    public User() {

    }

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword(password, this.verifyPassword);
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword(this.password, verifyPassword);
    }

    private void checkPassword(String password, String verifyPassword) {
        if (!password.equals(verifyPassword)) {
            this.verifyPassword = null;
        }
    }
}

