package controller;

import constants.DBConstants;
import model.Album;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlbumController extends BaseController {
    public AlbumController() {
        super();
    }

    public boolean insert(Album album) {
        String sql = "INSERT INTO " + DBConstants.Table.ALBUM
                + " VALUES (NULL,?,?)";
        if (!open()) return false;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, album.name);
            pstmt.setString(2, album.artist);
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

    public boolean delete(Album album) {
        String sql = String.format("delete from %s where %s = '%s'",
                DBConstants.Table.ALBUM,
                DBConstants.Column.ALBUM_NAME, album.name);
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

    public Album select(String name, String artist) {
        String sql = String.format("select * from %s where %s ='%s' and %s ='%s'",
                DBConstants.Table.ALBUM,
                DBConstants.Column.ALBUM_NAME, name,
                DBConstants.Column.ALBUM_ARTIST, artist);
        if (!open()) return null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int num = rs.getInt(DBConstants.Column.ALBUM_NUM);
                return new Album(num, name, artist);
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


    public <T> ArrayList<Album> select(String attr, T value) {
        ArrayList<Album> albumList = new ArrayList<Album>();
        String sql = String.format("select * from %s where %s ='%s'",
                DBConstants.Table.ALBUM,
                attr, String.valueOf(value));
        if (!open()) return null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int num = rs.getInt(DBConstants.Column.ALBUM_NUM);
                String name = rs.getString(DBConstants.Column.ALBUM_NAME);
                String artist = rs.getString(DBConstants.Column.ALBUM_ARTIST);
                Album album = new Album(num, name, artist);
                albumList.add(album);
            }
            return albumList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            close();
            return null;
        }
    }

    public ArrayList<Album> selectLike(String str) {
        ArrayList<Album> albumList = new ArrayList<Album>();
        String sql = String.format("select * from %s where %s LIKE '%%%s%%' or %s LIKE '%%%s%%'",
                DBConstants.Table.ALBUM,
                DBConstants.Column.ALBUM_NAME, str,
                DBConstants.Column.ALBUM_ARTIST, str);
        if (!open()) return null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int num = rs.getInt(DBConstants.Column.ALBUM_NUM);
                String name = rs.getString(DBConstants.Column.ALBUM_NAME);
                String artist = rs.getString(DBConstants.Column.ALBUM_ARTIST);
                Album album = new Album(num, name, artist);
                albumList.add(album);
            }
            close();
            return albumList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            close();
            return null;
        }
    }

    public <T> boolean update(Album album, String attr, T value) {
        String sql = String.format("update %s set %s = '%s' where %s = '%d'",
                DBConstants.Table.ALBUM,
                attr, String.valueOf(value),
                DBConstants.Column.ALBUM_NUM, album.num);

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


    public ArrayList<Album> selectAll() {
        ArrayList<Album> albumList = new ArrayList<Album>();
        String sql = String.format("select * from %s",
                DBConstants.Table.ALBUM);
        if (!open()) return null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int num = rs.getInt(DBConstants.Column.ALBUM_NUM);
                String name = rs.getString(DBConstants.Column.ALBUM_NAME);
                String artist = rs.getString(DBConstants.Column.ALBUM_ARTIST);
                Album album = new Album(num, name, artist);
                albumList.add(album);
            }
            return albumList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            close();
            return null;
        }
    }
}