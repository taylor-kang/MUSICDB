package controller;

import constants.DBConstants;
import model.Artist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArtistController extends BaseController {
    public ArtistController() {
        super();
    }

    public boolean insert(Artist Artist) {
        String sql = "INSERT INTO " + DBConstants.Table.ARTIST
                + " VALUES (NULL,?)";
        if (!open()) return false;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Artist.name);
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

    public boolean delete(Artist artist) {
        String sql = String.format("delete from %s where %s = '%s'",
                DBConstants.Table.ARTIST,
                DBConstants.Column.ARTIST_NAME, artist.name);
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

    public Artist select(String name) {
        String sql = String.format("select * from %s where %s ='%s'",
                DBConstants.Table.ARTIST,
                DBConstants.Column.ARTIST_NAME, name);
        if (!open()) return null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int num = rs.getInt(DBConstants.Column.ARTIST_NUM);
                return new Artist(num, name);
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

    public ArrayList<Artist> selectLike(String str) {
        ArrayList<Artist> artistList = new ArrayList<Artist>();
        String sql = String.format("select * from %s where %s LIKE '%%%s%%'",
                DBConstants.Table.ARTIST,
                DBConstants.Column.ARTIST_NAME, str);
        if (!open()) return null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int num = rs.getInt(DBConstants.Column.ARTIST_NUM);
                String name = rs.getString(DBConstants.Column.ARTIST_NAME);
                Artist artist =  new Artist(num, name);
                artistList.add(artist);
            }
            close();
            return artistList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            close();
            return null;
        }
    }



    public boolean update(Artist artist, String name) {
        String sql = String.format("update %s set %s = '%s' where %s = '%d'",
                DBConstants.Table.ARTIST,
                DBConstants.Column.ARTIST_NAME, name,
                DBConstants.Column.ARTIST_NUM, artist.num);

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


    public ArrayList<Artist> selectAll() {
        ArrayList<Artist> artistList = new ArrayList<Artist>();
        String sql = String.format("select * from %s",
                DBConstants.Table.ARTIST);
        if (!open()) return null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int num = rs.getInt(DBConstants.Column.ARTIST_NUM);
                String name = rs.getString(DBConstants.Column.ARTIST_NAME);
                Artist artist =  new Artist(num, name);
                artistList.add(artist);
            }
            return artistList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            close();
            return null;
        }
    }
}