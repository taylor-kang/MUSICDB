package controller;

import constants.DBConstants;

import java.sql.*;

public class BaseController {

	protected Connection conn;
	protected Statement stmt;
	protected PreparedStatement pstmt;
	
	public BaseController() {}
	
	protected boolean open() {
		try {
			conn = DriverManager.getConnection(DBConstants.URL, DBConstants.USER, DBConstants.PASSWORD);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	protected void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean initializeDB() {
		open();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(DBConstants.CreateDB.CREATE_TABLE_MUSIC);
			stmt.executeUpdate(DBConstants.CreateDB.CREATE_TABLE_USER);
			stmt.executeUpdate(DBConstants.CreateDB.CREATE_TABLE_ALBUM);
			stmt.executeUpdate(DBConstants.CreateDB.CREATE_TABLE_ARTIST);
			stmt.executeUpdate(DBConstants.CreateDB.CREATE_TABLE_LIKELIST);
			stmt.executeUpdate(DBConstants.CreateDB.CREATE_TABLE_PLAYLIST);
			stmt.executeUpdate(DBConstants.CreateDB.CREATE_TABLE_MUSICINPLAYLIST);

			stmt.executeUpdate(DBConstants.testCase.INSERT_MUSIC_DATA1);
			stmt.executeUpdate(DBConstants.testCase.INSERT_MUSIC_DATA2);
			stmt.executeUpdate(DBConstants.testCase.INSERT_MUSIC_DATA3);
			stmt.executeUpdate(DBConstants.testCase.INSERT_MUSIC_DATA4);
			stmt.executeUpdate(DBConstants.testCase.INSERT_MUSIC_DATA5);
			stmt.executeUpdate(DBConstants.testCase.INSERT_MUSIC_DATA6);
			stmt.executeUpdate(DBConstants.testCase.INSERT_MUSIC_DATA7);
			stmt.executeUpdate(DBConstants.testCase.INSERT_MUSIC_DATA8);
			stmt.executeUpdate(DBConstants.testCase.INSERT_MUSIC_DATA9);
			stmt.executeUpdate(DBConstants.testCase.INSERT_MUSIC_DATA10);

			stmt.executeUpdate(DBConstants.testCase.INSERT_USER_DATA1);
			stmt.executeUpdate(DBConstants.testCase.INSERT_USER_DATA2);
			stmt.executeUpdate(DBConstants.testCase.INSERT_USER_DATA3);
			stmt.executeUpdate(DBConstants.testCase.INSERT_USER_DATA4);
			stmt.executeUpdate(DBConstants.testCase.INSERT_USER_DATA5);
			stmt.executeUpdate(DBConstants.testCase.INSERT_USER_DATA6);
			stmt.executeUpdate(DBConstants.testCase.INSERT_USER_DATA7);
			stmt.executeUpdate(DBConstants.testCase.INSERT_USER_DATA8);
			stmt.executeUpdate(DBConstants.testCase.INSERT_USER_DATA9);
			stmt.executeUpdate(DBConstants.testCase.INSERT_USER_DATA10);
			stmt.executeUpdate(DBConstants.testCase.INSERT_USER_DATA11);

			stmt.executeUpdate(DBConstants.testCase.INSERT_PLIST_DATA1);
			stmt.executeUpdate(DBConstants.testCase.INSERT_PLIST_DATA2);
			stmt.executeUpdate(DBConstants.testCase.INSERT_PLIST_DATA3);
			stmt.executeUpdate(DBConstants.testCase.INSERT_PLIST_DATA4);
			stmt.executeUpdate(DBConstants.testCase.INSERT_PLIST_DATA5);
			stmt.executeUpdate(DBConstants.testCase.INSERT_PLIST_DATA6);
			stmt.executeUpdate(DBConstants.testCase.INSERT_PLIST_DATA7);
			stmt.executeUpdate(DBConstants.testCase.INSERT_PLIST_DATA8);
			stmt.executeUpdate(DBConstants.testCase.INSERT_PLIST_DATA9);
			stmt.executeUpdate(DBConstants.testCase.INSERT_PLIST_DATA10);
			stmt.executeUpdate(DBConstants.testCase.INSERT_PLIST_DATA11);

			stmt.executeUpdate(DBConstants.testCase.INSERT_ALBUM_DATA1);
			stmt.executeUpdate(DBConstants.testCase.INSERT_ALBUM_DATA2);
			stmt.executeUpdate(DBConstants.testCase.INSERT_ALBUM_DATA3);
			stmt.executeUpdate(DBConstants.testCase.INSERT_ALBUM_DATA4);
			stmt.executeUpdate(DBConstants.testCase.INSERT_ALBUM_DATA5);
			stmt.executeUpdate(DBConstants.testCase.INSERT_ALBUM_DATA6);
			stmt.executeUpdate(DBConstants.testCase.INSERT_ALBUM_DATA7);

			stmt.executeUpdate(DBConstants.testCase.INSERT_ARTIST_DATA1);
			stmt.executeUpdate(DBConstants.testCase.INSERT_ARTIST_DATA2);
			stmt.executeUpdate(DBConstants.testCase.INSERT_ARTIST_DATA3);
			stmt.executeUpdate(DBConstants.testCase.INSERT_ARTIST_DATA4);
			stmt.executeUpdate(DBConstants.testCase.INSERT_ARTIST_DATA5);
			stmt.executeUpdate(DBConstants.testCase.INSERT_ARTIST_DATA6);
			stmt.executeUpdate(DBConstants.testCase.INSERT_ARTIST_DATA7);

			close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
			return false;
		}
	}
	
	
}
