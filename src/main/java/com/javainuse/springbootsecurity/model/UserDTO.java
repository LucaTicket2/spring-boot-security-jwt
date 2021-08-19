package com.javainuse.springbootsecurity.model;

import lombok.Data;

import java.io.Serializable;

/**
 * The type User dto.
 */
@Data
public class UserDTO implements Serializable {
    private String username;
    private String password;
    private String role;

    /**
     * Instantiates a new User dto.
     */
    public UserDTO() {
        super();
    }

    /**
     * Instantiates a new User dto.
     *
     * @param username the username
     * @param password the password
     * @param role     the role
     */
    public UserDTO(String username, String password, String role) {
        super();
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Instantiates a new User dto.
     *
     * @param username the username
     * @param role     the role
     */
    public UserDTO(String username, String role) {
        super();
        this.username = username;
        this.role = role;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
