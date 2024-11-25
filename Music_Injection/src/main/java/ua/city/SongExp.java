package ua.city;

import java.sql.*;

public class SongExp {
    // Insert (Create)
    public void insertSong(String songName, String songDuration) {
        String sql = "INSERT INTO song2 (songName, songDuration) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, songName);
            pstmt.setTime(2, Time.valueOf(songDuration));
            pstmt.executeUpdate();
            System.out.println("Song inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Select all (Read)
    public void getAllSongs() {
        String sql = "SELECT * FROM song2";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("song_id") +
                        ", Name: " + rs.getString("songName") +
                        ", Duration: " + rs.getString("songDuration"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void updateSong(int songId, String newSongName, String newDuration) {
        String sql = "UPDATE song2 SET songName = ?, songDuration = ? WHERE song_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newSongName);
            pstmt.setTime(2, Time.valueOf(newDuration));
            pstmt.setInt(3, songId);
            pstmt.executeUpdate();
            System.out.println("Song updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSongName(int songId, String newSongName) {
        String sql = "UPDATE song2 SET songName = ? WHERE song_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newSongName);
            pstmt.setInt(2, songId);
            pstmt.executeUpdate();
            System.out.println("Song name updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteSong(int songId) {
        String sql = "DELETE FROM song2 WHERE song_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, songId);
            pstmt.executeUpdate();
            System.out.println("Song deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}