/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nabil
 */
public class User {
    private final String username; // tidak bisa diubah 
    private String password;       // bisa diubah → untuk reset password
    private final String role;     // tetap final

    // Constructor
    public User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getter
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String getRole(){ return role; }

    // Setter untuk password (reset password)
    public void setPassword(String password){ this.password = password; }
}
