package controller;

import constants.DBConstants;
import model.MusicInPlaylist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MusicInPlaylistController extends BaseController {
    public MusicInPlaylistController() {
        super();
    }

    public boolean insert(MusicInPlaylist musicInPlaylist) {
        String sql = "INSERT INTO " + DBConstants.Table.MUSICINPLAYLIST
                + " VALUES (?,?)";
        if (!open()) return false;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, musicInPlaylist.musicNum);
            pstmt.setInt(2, musicInPlaylist.plistNum);
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

    public boolean delete(MusicInPlaylist musicInPlaylist) {
        String sql = String.format("delete from %s where %s = '%s'",
                DBConstants.Table.MUSICINPLAYLIST,
                DBConstants.Column.MUSIC_NUM, musicInPlaylist.musicNum);
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


    public <T> ArrayList<MusicInPlaylist> select(String attr, T value) {
        ArrayList<MusicInPlaylist> musicList = new ArrayList<MusicInPlaylist>();
        String sql = String.format("select * from %s where %s ='%s'",
                DBConstants.Table.MUSICINPLAYLIST,
                attr, String.valueOf(value));
        if (!open()) return null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int musicNum = rs.getInt(DBConstants.Column.MUSIC_NUM);
                int plistNum = rs.getInt(DBConstants.Column.PLIST_NUM);
                MusicInPlaylist musicInPlaylist = new MusicInPlaylist(musicNum, plistNum);
                musicList.add(musicInPlaylist);
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