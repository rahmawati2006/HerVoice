package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ForumDAO {
    
    Connection conn = Koneksi.getConnection();

    // Mengisi JTable langsung dari DAO biar kode di Jframe kamu bersih
    public void tampilkanTabel(DefaultTableModel model) {
        model.setRowCount(0); // Reset tabel
        
        // Gabungkan tabel forum dan users untuk mengecek status anonim
        String sql = "SELECT f.id_forum, f.isi_diskusi, u.nama, u.anonim " +
                     "FROM forum f JOIN users u ON f.user_id = u.id ORDER BY f.id_forum DESC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                boolean isAnonim = rs.getBoolean("anonim");
                String namaTampil = isAnonim ? "Anonymous" : rs.getString("nama");
                
                model.addRow(new Object[]{
                    rs.getInt("id_forum"),
                    namaTampil,
                    rs.getString("isi_diskusi")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tambah postingan baru menggunakan Session.id
public boolean tambahPostingan(int userId, String komentarUser) {
    // Sesuaikan kolomnya: user_id, judul, isi
    String sql = "INSERT INTO forum (user_id, judul, isi) VALUES (?, ?, ?)"; 
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, "Balasan Diskusi"); // Judul default karena tidak ada input judul di UI
        ps.setString(3, komentarUser);     // Teks dari jTextField1 masuk ke kolom 'isi'
        
        ps.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println("Error MySQL Forum: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
}