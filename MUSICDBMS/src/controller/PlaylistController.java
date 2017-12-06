package controller;

import constants.DBConstants;
import model.Playlist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistController extends BaseController {
    public PlaylistController() { super(); }

    public boolean insert(Playlist plist) {
        String sql = "insert into " + DBConstants.Table.PLAYLIST
                + " values (NULL,?)";
        if (!open()) return false;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, plist.user);
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

    public boolean delete(Playlist plist) {
        String sql = String.format("delete from %s where %s = '%s'",
                DBConstants.Table.PLAYLIST,
                DBConstants.Column.PLIST_OWNER, plist.num);
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

    public Playlist select(int user) {
        String sql = String.format("select * from %s where %s ='%d'",
                DBConstants.Table.PLAYLIST,
                DBConstants.Column.PLIST_OWNER, user);
        if (!open()) return null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int num = rs.getInt(DBConstants.Column.PLIST_NUM);
                return new Playlist(num, user);
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            close();
            return null;
        }
    }

    public <T> boolean update(Playlist plist, String attr, T value) {
        String sql = String.format("update %s set %s = '%s' where %s = '%d'",
                DBConstants.Table.PLAYLIST,
                attr, String.valueOf(value),
                DBConstants.Column.PLIST_NUM, plist.num);

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
}