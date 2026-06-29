package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;
import session.Session;

public class UserDAO {

    Connection conn = Koneksi.getConnection();

    // ================= REGISTER =================
    public boolean register(String nama, String email, String password, String role, boolean anonim) {

        String sql = "INSERT INTO users(nama,email,password,role,anonim) VALUES (?,?,?,?,?)";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nama);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.setBoolean(5, anonim);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }
    }

    // ================= LOGIN =================
   public boolean login(String email, String password) {

    String sql = "SELECT * FROM users WHERE email=? AND password=?";

    try {

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            Session.id = rs.getInt("id");
            Session.nama = rs.getString("nama");
            Session.email = rs.getString("email");
            Session.role = rs.getString("role");

            return true;
        }

    } catch (SQLException e) {

        e.printStackTrace();

    }

    return false;
}
}