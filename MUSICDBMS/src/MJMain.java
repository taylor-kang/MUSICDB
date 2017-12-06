import constants.DBConstants;
import controller.BaseController;
import controller.PlaylistController;
import controller.UserController;
import model.Playlist;
import model.User;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by kangtaehee on 2017. 11. 28..
 * 2015004284
 *
 */
public class MJMain {
      static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://127.0.0.1:3307/MUSICDB";
//
//    static final String USERNAME = "root";
//    static final String PASSWORD = "1234";


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //connection
        Connection conn = null;
        Statement stmt = null;
        Scanner scn = new Scanner(System.in);


        System.out.print("type DB NAME");
        DBConstants.DBNAME =  scn.nextLine();
        System.out.print("type PORT Number");
        DBConstants.PORTNUM =  scn.nextLine();
        System.out.print("type USER ID");
        DBConstants.USER =  scn.nextLine();
        System.out.print("type USER PW");
        DBConstants.PASSWORD =  scn.nextLine();

        Class.forName(JDBC_DRIVER);


        BaseController b = new BaseController();
        b.initializeDB();

        UserController userDB = new UserController();
        PlaylistController plistDB = new PlaylistController();

        try{




            //First Page
            while(true) {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DBConstants.getUrl(), DBConstants.USER,DBConstants.PASSWORD);
                stmt = conn.createStatement();

                System.out.println("============================");
                System.out.println("         Welcome To         ");
                System.out.println("     M U S I C J A V A      ");
                System.out.println("----------------------------");
                System.out.println();
                System.out.println("1. Log-IN");
                System.out.println("2. Find PASSWORD");
                System.out.println("3. Admin Log-IN");
                System.out.println("4. New Account");
                System.out.println("0. Exit");
                System.out.println();
                System.out.println("----------------------------");
                System.out.print("Input: ");
                int input = scn.nextInt();

                switch (input) {

                    //1. model.User Log-IN
                    case 1:
                        System.out.println("============================");
                        System.out.println("            Log-IN          ");
                        System.out.println("----------------------------");
                        System.out.println();

                        System.out.print("type your ID: ");
                        scn.nextLine();
                        String inputID = scn.nextLine();
                        System.out.print("type your PW: ");
                        String inputPW = scn.nextLine();
                        System.out.println();
                        System.out.println("----------------------------");

                        User user = userDB.select(inputID);
                        if(user == null) {
                            System.out.println("=>Log-IN Failed!");
                        }
                        else {
                            if(user.pw.equals(inputPW)){
                                System.out.println("=>Log-IN Successed!");
                                UserDashboard.printDashboard(user.num);
                            }
                            else
                                System.out.println("=>Log-IN Failed!");
                        }
                        break;

                    //2. Find PASSWORD
                    case 2:
                        System.out.println("============================");
                        System.out.println("       Find PASSWORD        ");
                        System.out.println("----------------------------");
                        System.out.println();
                        scn.nextLine();

                        System.out.print("type your ID: ");
                        inputID = scn.nextLine();
                        System.out.print("type your Email Address: ");
                        String inputAddress = scn.nextLine();
                        System.out.println();
                        System.out.println("----------------------------");

                        user = userDB.select(inputID);
                        if(user == null) {
                            System.out.println("=>Not valid ID and Address");
                        }
                        else {
                            if(user.addr.equals(inputAddress))
                                System.out.println("=>We send your password \n  to your email address");
                            else
                                System.out.println("=>Not valid ID and Address");
                        }
                        break;

                    //3. Admin Log-IN
                    case 3:
                        System.out.println("============================");
                        System.out.println("        Admin Log-IN        ");
                        System.out.println("----------------------------");
                        System.out.println();

                        System.out.print("type your ID: ");
                        scn.nextLine();
                        inputID = scn.nextLine();
                        System.out.print("type your PASSWORD: ");
                        inputPW = scn.nextLine();
                        System.out.println();
                        System.out.println("----------------------------");

                        user = userDB.select(inputID);
                        if(user == null) {
                            System.out.println("=>Log-IN Failed!");
                        }
                        else {
                            if(user.pw.equals(inputPW)){
                                if(user.isadmin == 1) {
                                    System.out.println("=>Log-IN Successed!");
                                    AdminDashboard.printDashboard();
                                }
                                else
                                    System.out.println("=>Access Denied!");
                            }
                            else
                                System.out.println("=>Log-IN Failed!");
                        }
                        break;

                    //4. New Account
                    case 4:
                        System.out.println("============================");
                        System.out.println("        New Account         ");
                        System.out.println("----------------------------");
                        System.out.println();

                        //new account info
                        scn.nextLine();
                        System.out.print("type USER ID: ");
                        inputID = scn.nextLine();

                        System.out.print("type USER PW: ");
                        inputPW = scn.nextLine();

                        System.out.print("type USER Address: ");
                        String inputAddr = scn.nextLine();

                        System.out.print("type model.User Name: ");
                        String inputName = scn.nextLine();
                        System.out.println();
                        System.out.println("----------------------------");

                        user = new User(0,0,inputID,inputPW,inputAddr,inputName,0);
                        userDB.insert(user);
                        user = userDB.select(inputID);
                        Playlist plist = new Playlist(0, user.num);
                        plistDB.insert(plist);
                        plist = plistDB.select(user.num);
                        userDB.update(user, "USERPLIST", plist.num);

                        System.out.println("=>New Accout Succesfully Created!");


                        break;

                    //0. Program Exit
                    case 0:
                        stmt.close();
                        conn.close();
                        System.exit(0);
                }

            }

        }catch(SQLException se1){
            se1.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        stmt.close();
        conn.close();
    }
}
