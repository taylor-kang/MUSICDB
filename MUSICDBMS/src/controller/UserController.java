package controller;

import constants.DBConstants;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController extends BaseController {
    public UserController() { super(); }

    public boolean insert(User user) {
        String sql = "insert into " + DBConstants.Table.USER
                + " values (NULL,?,?,?,?,?,0)";
        if (!open()) return false;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.isadmin);
            pstmt.setString(2, user.id);
            pstmt.setString(3, user.pw);
            pstmt.setString(4, user.addr);
            pstmt.setString(5, user.name);
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

    public boolean delete(User user) {
        String sql = String.format("delete from %s where %s = '%s' and %s = '%s'",
                DBConstants.Table.USER,
                DBConstants.Column.USER_ID, user.id,
                DBConstants.Column.USER_PW, user.pw);
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

    public User select(String id) {
        String sql = String.format("select * from %s where %s ='%s'",
                DBConstants.Table.USER,
                DBConstants.Column.USER_ID, id);
        if (!open()) return null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int num = rs.getInt(DBConstants.Column.USER_NUM);
                int isadmin = rs.getInt(DBConstants.Column.USER_ISADMIN);
                //String id = rs.getString(constants.DBConstants.Column.USER_ID);
                String pw = rs.getString(DBConstants.Column.USER_PW);
                String addr = rs.getString(DBConstants.Column.USER_ADDR);
                String name = rs.getString(DBConstants.Column.USER_NAME);
                int plist = rs.getInt(DBConstants.Column.USER_PLIST);
                return new User(num, isadmin, id, pw, addr, name, plist);
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
    public User selectByNum(int num) {
        String sql = String.format("select * from %s where %s ='%s'",
                DBConstants.Table.USER,
                DBConstants.Column.USER_NUM, num);
        if (!open()) return null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int isadmin = rs.getInt(DBConstants.Column.USER_ISADMIN);
                String id = rs.getString(DBConstants.Column.USER_ID);
                String pw = rs.getString(DBConstants.Column.USER_PW);
                String addr = rs.getString(DBConstants.Column.USER_ADDR);
                String name = rs.getString(DBConstants.Column.USER_NAME);
                int plist = rs.getInt(DBConstants.Column.USER_PLIST);
                return new User(num, isadmin, id, pw, addr, name, plist);
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

    public <T> ArrayList<User> select(String attr, T value) {
        ArrayList<User> userList = new ArrayList<User>();
        String sql = String.format("select * from %s where %s ='%s'",
                DBConstants.Table.USER,
                attr, String.valueOf(value));
        if (!open()) return null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int num = rs.getInt(DBConstants.Column.USER_NUM);
                int isadmin = rs.getInt(DBConstants.Column.USER_ISADMIN);
                String id = rs.getString(DBConstants.Column.USER_ID);
                String pw = rs.getString(DBConstants.Column.USER_PW);
                String addr = rs.getString(DBConstants.Column.USER_ADDR);
                String name = rs.getString(DBConstants.Column.USER_NAME);
                int plist = rs.getInt(DBConstants.Column.USER_PLIST);
                User user = new User(num, isadmin, id, pw, addr, name, plist);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            close();
            return null;
        }
    }
    public ArrayList<User> selectLike(String str) {
        ArrayList<User> userList = new ArrayList<User>();
        String sql = String.format("select * from %s where %s LIKE '%%%s%%' or %s LIKE '%%%s%%' or %s LIKE '%%%s%%'",
                DBConstants.Table.USER,
                DBConstants.Column.USER_ID, str,
                DBConstants.Column.USER_ADDR, str,
                DBConstants.Column.USER_NAME, str);
        if (!open()) return null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int num = rs.getInt(DBConstants.Column.USER_NUM);
                int isadmin = rs.getInt(DBConstants.Column.USER_ISADMIN);
                String id = rs.getString(DBConstants.Column.USER_ID);
                String pw = rs.getString(DBConstants.Column.USER_PW);
                String addr = rs.getString(DBConstants.Column.USER_ADDR);
                String name = rs.getString(DBConstants.Column.USER_NAME);
                int plist = rs.getInt(DBConstants.Column.USER_PLIST);
                User user = new User(num, isadmin, id, pw, addr, name, plist);
                userList.add(user);
            }
            close();
            return userList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            close();
            return null;
        }
    }



    public <T> boolean update(User user, String attr, T value) {
        String sql = String.format("update %s set %s = '%s' where %s = '%d'",
                DBConstants.Table.USER,
                attr, String.valueOf(value),
                DBConstants.Column.USER_NUM, user.num);

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