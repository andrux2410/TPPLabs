package ua.city;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Создание объектов DAO
        GenreExp genreExp = new GenreExp();
        MusicGroupExp musicGroupExp = new MusicGroupExp();
        SongExp songExp = new SongExp();

        // Приклад додавання жанру
        Genre genre = new Genre();
        genre.setGenreName("Metal");
        int genreId = genreExp.addGenre(genre);
        genre.setGenreId(genreId);

        if (genreId > 0) {
            System.out.println("Жанр додано: " + genre);
        } else {
            System.out.println("Помилка: не вдалося додати жанр.");
        }

        // Приклад додавання музичної групи
        MusicGroup musicGroup = new MusicGroup();
        musicGroup.setGroupName("Metallica");
        int groupId = musicGroupExp.addMusicGroup(musicGroup);
        musicGroup.setGroupId(groupId);

        if (groupId > 0) {
            System.out.println("Музичну групу додано: " + musicGroup);
        } else {
            System.out.println("Помилка: не вдалося додати музичну групу.");
        }

        // Приклад додавання пісні
        Song song = new Song();
        song.setSongName("Enter Sandman");
        song.setSongDuration("00:05:31");
        int songId = songExp.addSong(song);
        song.setSongId(songId);

        if (songId > 0) {
            System.out.println("Пісню додано: " + song);
        } else {
            System.out.println("Помилка: не вдалося додати пісню.");
        }

        // Виведення всіх жанрів
        System.out.println("\nСписок жанрів:");
        List<Genre> genres = genreExp.getAllGenres();
        for (Genre g : genres) {
            System.out.println("ID: " + g.getGenreId() + ", Назва: " + g.getGenreName());
        }

        // Виведення всіх музичних груп
        System.out.println("\nСписок музичних груп:");
        List<MusicGroup> musicGroups = musicGroupExp.getAllMusicGroups();
        for (MusicGroup g : musicGroups) {
            System.out.println("ID: " + g.getGroupId() + ", Назва: " + g.getGroupName());
        }

        // Виведення всіх пісень
        System.out.println("\nСписок пісень:");
        List<Song> songs = songExp.getAllSongs();
        for (Song s : songs) {
            System.out.println("ID: " + s.getSongId() + ", Назва: " + s.getSongName() + ", Тривалість: " + s.getSongDuration());
        }
    }
}
