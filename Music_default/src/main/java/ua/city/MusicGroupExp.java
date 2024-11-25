package ua.city;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicGroupExp {

	// Метод для додавання нової музичної групи
	public int addMusicGroup(MusicGroup musicGroup) {
		String sql = "INSERT INTO music_group (groupName) VALUES (?)";
		try (Connection conn = DatabaseControl.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, musicGroup.getGroupName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	// Метод для отримання музичної групи за ID
	public MusicGroup getMusicGroupById(int groupId) throws SQLException {
		String sql = "SELECT * FROM music_group WHERE group_id = ?";

		try (Connection conn = DatabaseControl.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, groupId); // Встановлюємо значення group_id
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new MusicGroup(rs.getInt("group_id"), rs.getString("groupName"));
			}
		}
		return null;
	}

	// Метод для оновлення назви музичної групи
	public boolean updateMusicGroup(int groupId, String newGroupName) throws SQLException {
		String sql = "UPDATE music_group SET groupName = ? WHERE group_id = ?";

		try (Connection conn = DatabaseControl.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, newGroupName);
			stmt.setInt(2, groupId);
			int rowsAffected = stmt.executeUpdate();

			return rowsAffected > 0; // Повертаємо true, якщо оновлення успішне
		}
	}

	// Метод для отримання всіх музичних груп
	public List<MusicGroup> getAllMusicGroups() throws SQLException {
		List<MusicGroup> musicGroups = new ArrayList<>();
		String sql = "SELECT * FROM music_group";

		try (Connection conn = DatabaseControl.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				musicGroups.add(new MusicGroup(rs.getInt("group_id"), rs.getString("groupName")));
			}
		}
		return musicGroups;
	}

	// Метод для видалення музичної групи за ID
	public boolean deleteMusicGroup(int groupId) throws SQLException {
		String sql = "DELETE FROM music_group WHERE group_id = ?";

		try (Connection conn = DatabaseControl.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, groupId);
			int rowsAffected = stmt.executeUpdate();

			return rowsAffected > 0; // Повертаємо true, якщо видалення успішне
		}
	}
}
