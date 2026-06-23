/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author nabil
 */

import java.util.ArrayList;
import model.User;

public class DataStore {

    // Tempat simpan user sementara
    public static final ArrayList<User> users = new ArrayList<>();

    // User default untuk testing login
    static {
        users.add(new User("admin","admin123","Admin"));
        users.add(new User("user","user123","Member"));
        users.add(new User("konselor1","pass123","Counselor"));
    }

}