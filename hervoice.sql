SELECT
b.judul_buku,
SUM(p.biaya_produksi) AS total_modal
FROM buku b
 JOIN proses_produksi p ON b.id_buku = p.id_buku
GROUP BY b.id_buku, b.judul_buku
ORDER BY total_modal DESC
LIMIT 5;