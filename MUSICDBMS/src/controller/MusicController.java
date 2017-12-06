package controller;

import constants.DBConstants;
import model.Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MusicController extends BaseController {
	public MusicController() {
		super();
	}

	public boolean insert(Music music) {
		String sql = "INSERT INTO " + DBConstants.Table.MUSIC
				+ " VALUES (NULL,?,?,?,?,?,0)";
		if (!open()) return false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, music.name);
			pstmt.setString(2, music.genre);
			pstmt.setString(3, music.album);
			pstmt.setString(4, music.artist);
			pstmt.setInt(5, music.trnum);
			pstmt.executeUpdate();
			close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			close();
			return false;
		}
	}

	public boolean delete(Music music) {
		String sql = String.format("delete from %s where %s = '%s' and %s = '%s'",
				DBConstants.Table.MUSIC,
				DBConstants.Column.MUSIC_NAME, music.name,
				DBConstants.Column.MUSIC_ARTIST, music.artist);
		if (!open()) return false;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
			return false;
		}
	}

	public Music select(String name, String artist) {
		String sql = String.format("select * from %s where %s ='%s' and %s = '%s'",
				DBConstants.Table.MUSIC,
				DBConstants.Column.MUSIC_NAME, name,
				DBConstants.Column.MUSIC_ARTIST, artist);
		if (!open()) return null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				int num = rs.getInt(DBConstants.Column.MUSIC_NUM);
				String musicname = rs.getString(DBConstants.Column.MUSIC_NAME);
				String genre = rs.getString(DBConstants.Column.MUSIC_GENRE);
				String musicartist = rs.getString(DBConstants.Column.MUSIC_ARTIST);
				String album = rs.getString(DBConstants.Column.MUSIC_ALBUM);
				int trnum = rs.getInt(DBConstants.Column.MUSIC_TRACK);
				int likenum = rs.getInt(DBConstants.Column.MUSIC_LIKENUM);
				return new Music(num, musicname, genre, musicartist, album, trnum, likenum);
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
			return null;
		}
	}

	public Music selectByNum(int num) {
		String sql = String.format("select * from %s where %s ='%d'",
				DBConstants.Table.MUSIC,
				DBConstants.Column.MUSIC_NUM, num);
		if (!open()) return null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String musicname = rs.getString(DBConstants.Column.MUSIC_NAME);
				String genre = rs.getString(DBConstants.Column.MUSIC_GENRE);
				String musicartist = rs.getString(DBConstants.Column.MUSIC_ARTIST);
				String album = rs.getString(DBConstants.Column.MUSIC_ALBUM);
				int trnum = rs.getInt(DBConstants.Column.MUSIC_TRACK);
				int likenum = rs.getInt(DBConstants.Column.MUSIC_LIKENUM);
				return new Music(num, musicname, genre, musicartist, album, trnum, likenum);
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
			return null;
		}
	}

	public <T> ArrayList<Music> selectBy(String attr, T value) {
		ArrayList<Music> musicList = new ArrayList<Music>();
		String sql = String.format("select * from %s where %s ='%s'",
				DBConstants.Table.MUSIC,
				attr, String.valueOf(value));
		if (!open()) return null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int num = rs.getInt(DBConstants.Column.MUSIC_NUM);
				String musicname = rs.getString(DBConstants.Column.MUSIC_NAME);
				String genre = rs.getString(DBConstants.Column.MUSIC_GENRE);
				String musicartist = rs.getString(DBConstants.Column.MUSIC_ARTIST);
				String album = rs.getString(DBConstants.Column.MUSIC_ALBUM);
				int trnum = rs.getInt(DBConstants.Column.MUSIC_TRACK);
				int likenum = rs.getInt(DBConstants.Column.MUSIC_LIKENUM);
				Music music = new Music(num, musicname, genre, musicartist, album, trnum, likenum);
				musicList.add(music);
			}
			return musicList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
			return null;
		}
	}

	public ArrayList<Music> selectLike(String str) {
		ArrayList<Music> musicList = new ArrayList<Music>();
		String sql = String.format("select * from %s where %s LIKE '%%%s%%' or %s LIKE '%%%s%%' or %s LIKE '%%%s%%'",
				DBConstants.Table.MUSIC,
				DBConstants.Column.MUSIC_NAME, str,
				DBConstants.Column.MUSIC_ARTIST, str,
				DBConstants.Column.MUSIC_ALBUM, str);
		if (!open()) return null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int num = rs.getInt(DBConstants.Column.MUSIC_NUM);
				String name = rs.getString(DBConstants.Column.MUSIC_NAME);
				String genre = rs.getString(DBConstants.Column.MUSIC_GENRE);
				String artist = rs.getString(DBConstants.Column.MUSIC_ARTIST);
				String album = rs.getString(DBConstants.Column.MUSIC_ALBUM);
				int trnum = rs.getInt(DBConstants.Column.MUSIC_TRACK);
				int likenum = rs.getInt(DBConstants.Column.MUSIC_LIKENUM);
				Music music = new Music(num, name, genre, artist, album, trnum, likenum);
				musicList.add(music);
			}
			close();
			return musicList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
			return null;
		}
	}

	public <T> boolean update(Music music, String attr, T value) {
		String sql = String.format("update %s set %s = '%s' where %s = '%d'",
				DBConstants.Table.MUSIC,
				attr, String.valueOf(value),
				DBConstants.Column.MUSIC_NUM, music.num);

		if (!open()) return false;

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			close();
			return false;
		}

	}


	public ArrayList<Music> selectAll() {
		ArrayList<Music> musicList = new ArrayList<Music>();
		String sql = String.format("select * from %s",
				DBConstants.Table.MUSIC);
		if (!open()) return null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int num = rs.getInt(DBConstants.Column.MUSIC_NUM);
				String musicname = rs.getString(DBConstants.Column.MUSIC_NAME);
				String genre = rs.getString(DBConstants.Column.MUSIC_GENRE);
				String musicartist = rs.getString(DBConstants.Column.MUSIC_ARTIST);
				String album = rs.getString(DBConstants.Column.MUSIC_ALBUM);
				int trnum = rs.getInt(DBConstants.Column.MUSIC_TRACK);
				int likenum = rs.getInt(DBConstants.Column.MUSIC_LIKENUM);
				Music music = new Music(num, musicname, genre, musicartist, album, trnum, likenum);
				musicList.add(music);
			}
			return musicList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
			return null;
		}
	}
}