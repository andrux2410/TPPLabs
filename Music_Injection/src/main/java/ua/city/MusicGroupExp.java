package ua.city;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MusicGroupExp {
    // Insert (Create)
    public void insertMusicGroup(String groupName) {
        String sql = "INSERT INTO music_group2 (groupName) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, groupName);
            pstmt.executeUpdate();
            System.out.println("Music group inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Select all (Read)
    public void getAllMusicGroups() {
        String sql = "SELECT * FROM music_group2";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("group_id") +
                        ", Name: " + rs.getString("groupName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void updateMusicGroup(int groupId, String newGroupName) {
        String sql = "UPDATE music_group2 SET groupName = ? WHERE group_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newGroupName);
            pstmt.setInt(2, groupId);
            pstmt.executeUpdate();
            System.out.println("Music group updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteMusicGroup(int groupId) {
        String sql = "DELETE FROM music_group2 WHERE group_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, groupId);
            pstmt.executeUpdate();
            System.out.println("Music group deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}