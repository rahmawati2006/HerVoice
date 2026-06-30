package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ArtikelDAO {
    
    Connection conn = Koneksi.getConnection();

    public void tampilkanDaftarArtikel(DefaultTableModel model) {
        model.setRowCount(0); // Reset tabel biar ga double
        String sql = "SELECT id, judul FROM artikel";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                model.addRow(new Object[]{ 
                    rs.getInt("id"), 
                    rs.getString("judul") 
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Baca Artikel: " + e.getMessage());
        }
    }

    // 2. Ambil isi lengkap artikel berdasarkan id saat baris tabel diklik
    public String getIsiArtikel(int idArtikel) {
        String sql = "SELECT isi FROM artikel WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idArtikel);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString("isi");
            }
        } catch (SQLException e) {
            System.out.println("Error Detail Artikel: " + e.getMessage());
        }
        return "";
    }
}