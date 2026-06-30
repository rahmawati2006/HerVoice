package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextArea;

public class ChatDAO {

    Connection conn = Koneksi.getConnection();

    // 1. Ambil riwayat chat berdasarkan chat_id sesi obrolannya
    public void muatRiwayatChat(int chatSesiId, int myId, JTextArea txtAreaChat) {
        String sql = "SELECT pengirim_id, pesan FROM chat_pesan WHERE chat_id = ? ORDER BY id ASC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, chatSesiId);
            ResultSet rs = ps.executeQuery();
            
            txtAreaChat.setText(""); 
            while(rs.next()) {
                int pengirim = rs.getInt("pengirim_id");
                String pesan = rs.getString("pesan");
                
                if(pengirim == myId) {
                    txtAreaChat.append("Anda: " + pesan + "\n");
                } else {
                    txtAreaChat.append("Konselor: " + pesan + "\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error Muat Chat: " + e.getMessage());
        }
    }

    // 2. Simpan pesan baru ke tabel chat_pesan sesuai struktur barumu
public boolean kirimPesanBaru(int chatSesiId, int pengirimId, String pesan) {
    // Sesuaikan nama tabel dan kolom dengan database aslimu
    String sql = "INSERT INTO chat_pesan (chat_id, pengirim_id, pesan) VALUES (?, ?, ?)";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, chatSesiId);
        ps.setInt(2, pengirimId);
        ps.setString(3, pesan);
        
        int rows = ps.executeUpdate();
        return rows > 0;
    } catch (SQLException e) {
        System.out.println("Error Kirim Chat: " + e.getMessage());
        return false;
    }
}
}