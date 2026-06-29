/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static Connection conn;

    public static Connection getConnection() {

        if (conn == null) {

            try {

                String url = "jdbc:mysql://localhost:3306/hervoice";
                String user = "root";
                String pass = "GojoSatoru1";

                conn = DriverManager.getConnection(url, user, pass);

                System.out.println("Koneksi berhasil!");

            } catch (SQLException e) {

                System.out.println("Koneksi gagal!");
                e.printStackTrace();

            }

        }

        return conn;
    }
}
/**
 *
 * @author mulya
 */
