import constants.DBConstants;
import controller.AlbumController;
import controller.ArtistController;
import controller.MusicController;
import controller.UserController;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by kangtaehee on 2017. 11. 28..
 */
public class AdminDashboard {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://127.0.0.1:3307/MUSICDB";

//    static final String USERNAME = "root";
//    static final String PASSWORD = "1234";


    Integer userNum;

    public static void printDashboard () throws ClassNotFoundException, SQLException {
        //connection
        Connection conn = null;
        Statement stmt = null;

        MusicController musicDB = new MusicController();
        ArtistController artistController = new ArtistController();
        AlbumController albumDB = new AlbumController();
        UserController userDB = new UserController();
        Scanner scn = new Scanner(System.in);

        while(true) {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DBConstants.getUrl(),DBConstants.USER,DBConstants.PASSWORD                                                                                                                            );
            stmt = conn.createStatement();

            System.out.println("============================");
            System.out.println("         Admin Page         ");
            System.out.println("----------------------------");
            System.out.println();
            System.out.println("1. Insert/ Update / Delete / Select Music List");
            System.out.println("2. Insert/ Update / Delete / Select User List");
            System.out.println("3. Log-out");
            System.out.println();
            System.out.println("----------------------------");
            System.out.print("Input: ");
            int input = scn.nextInt();

            switch (input) {
                //1. My model.Playlist
                case 1:
                    System.out.println("============================");
                    System.out.println("            MUSIC           ");
                    System.out.println("----------------------------");
                    System.out.println();
                    System.out.println("1. Insert");
                    System.out.println("2. Update");
                    System.out.println("3. Delete");
                    System.out.println("4. Select");
                    System.out.println();
                    System.out.println("----------------------------");
                    System.out.print("Input: ");
                    input = scn.nextInt();

                    switch (input){

                        case 1:
                            System.out.println("============================");
                            scn.nextLine();
                            System.out.println();
                            //MUSIC NAME
                            System.out.print("type model.Music Name: ");
                            String inputName = scn.nextLine();
                            System.out.println();
                            //GENUM
                            for(int i=0; i< Genre.NUM_GENRE ; i++){
                                System.out.printf("%d. %s\n", i+1, Genre.GENRE_LIST[i]);
                            }
                            System.out.print("type model.Genre Number: ");
                            int inputGenre = scn.nextInt()-1;
                            scn.nextLine();

                            //ARTIST
                            System.out.print("type model.Artist Name: ");
                            String inputArtist = scn.nextLine();
                            Artist artist = artistController.select(inputArtist);
                            if(artist == null) {
                                artist = new Artist(0, inputArtist);
                                artistController.insert(artist);
                            }

                            //ALBUM NUM
                            System.out.print("type model.Album Name: ");
                            String inputAlbum = scn.nextLine();
                            Album album = albumDB.select("ALBUMNAME", inputAlbum);
                            if(album == null){
                                album = new Album(0,inputAlbum,inputArtist);
                                albumDB.insert(album);
                            }

                            System.out.print("type Track Number: ");
                            Integer inputTrnum = scn.nextInt();
                            System.out.println();

                            Music music = new Music(0, inputName, Genre.GENRE_LIST[inputGenre], inputArtist, inputAlbum, inputTrnum, 0);
                            musicDB.insert(music);


                            System.out.println("----------------------------");
                            System.out.println("=>Insert Music Successfully!");

                            stmt.close();
                            conn.close();

                            break;
                        case 2:
                            System.out.println("============================");
                            scn.nextLine();
                            System.out.println();
                            //MUSIC NAME
                            System.out.print("type Music Name: ");
                            inputName = scn.nextLine();

                            System.out.print("type Artist Name: ");
                            inputArtist = scn.nextLine();
                            System.out.println();
                            System.out.println("----------------------------");
                            //있는지 확인먼저
                            music = musicDB.select(inputName, inputArtist);
                            if (music == null) {
                                System.out.println("=>Not registered model.Music!");
                            }
                            else {
                                System.out.println();
                                System.out.println("type Attribute name for Update");
                                String inputAttr = scn.nextLine();
                                System.out.println("type Attribute value for Update");
                                String inputValue = scn.nextLine();
                                System.out.println();
                                System.out.println("----------------------------");
                                musicDB.update(music,inputAttr,inputValue);
                                System.out.println("=>Update Music Successfully!");
                            }

                            break;
                        case 3:
                            System.out.println("============================");
                            scn.nextLine();
                            System.out.println();
                            //MUSIC NAME
                            System.out.print("type model.Music Name: ");
                            inputName = scn.nextLine();

                            System.out.print("type model.Artist Name: ");
                            inputArtist = scn.nextLine();
                            System.out.println();

                            System.out.println("----------------------------");
                            //있는지 확인먼저
                            music = musicDB.select(inputName, inputArtist);
                            if (music == null) {
                                System.out.println("=>Not registered model.Music");
                            }
                            else {
                                musicDB.delete(music);
                                System.out.println("=>Delete model.Music Successfully");
                            }

                            break;

                        case 4:
                            System.out.println("============================");
                            scn.nextLine();
                            System.out.println();
                            //MUSIC NAME
                            System.out.print("type Value for Select: ");
                            String inputValue = scn.nextLine();
                            System.out.println();
                            System.out.println("----------------------------");
                            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-8s|\n","NAME","GENRE","ALBUM","ARTIST","TRACK#");

                            ArrayList<Music> music_list = musicDB.selectLike(inputValue);

                            for(int i=0; i<music_list.size() ;i++){
                                music = music_list.get(i);

                                System.out.printf("%-20s| %-20s| %-20s| %-20s| %8d|\n",
                                        music.name,music.genre,music.album,music.artist,music.trnum);
                            }
                            System.out.println("----------------------------");
                            break;
                    }
                    break;
                //2. model.User data
                case 2:
                    //TODO : user의 플레이리스트 음악 출력
                    System.out.println("============================");
                    System.out.println("             USER           ");
                    System.out.println("----------------------------");
                    System.out.println();
                    System.out.println("1. Insert");
                    System.out.println("2. Update");
                    System.out.println("3. Delete");
                    System.out.println("4. Select");
                    System.out.println();
                    System.out.println("----------------------------");
                    System.out.print("Input: ");
                    input = scn.nextInt();

                    switch (input){

                        case 1: //insert
                            System.out.println("============================");
                            scn.nextLine();
                            System.out.println();
                            //USER authoriztion
                            System.out.print("type model.User Authorization( user / admin ): ");
                            String inputAuth = scn.nextLine();
                            int authorizaiton = 0;
                            if(inputAuth.toLowerCase().equals("user")) {
                                authorizaiton = 0;
                            }else if(inputAuth.toLowerCase().equals("admin")){
                                authorizaiton = 1;
                            }

                            //USER ID
                            System.out.print("type model.User ID: ");
                            String inputID = scn.nextLine();

                            //USER PW
                            System.out.print("type model.User PW: ");
                            String inputPW = scn.nextLine();

                            //USER ADDR
                            System.out.print("type model.User Address: ");
                            String inputAddr = scn.nextLine();

                            //USER NAME
                            System.out.print("type model.User Name: ");
                            String inputName = scn.nextLine();

                            User user = new User(0,authorizaiton, inputID, inputPW, inputAddr, inputName, 0);
                            userDB.insert(user);
                            System.out.println();
                            System.out.println("----------------------------");
                            System.out.println("=>Insert model.User Successfully!");
                            stmt.close();
                            conn.close();

                            break;

                        case 2: //update
                            System.out.println("============================");
                            scn.nextLine();
                            System.out.println();
                            //USER ID
                            System.out.print("type model.User ID: ");
                            inputID = scn.nextLine();
                            user = userDB.select(inputID);
                            if(user == null){
                                System.out.println("----------------------------");
                                System.out.println("=>Not registered model.User");
                            }
                            else {
                                System.out.print("type Attribute name for Update: ");
                                String inputAttr = scn.nextLine();
                                System.out.print("type Attribute value for Update: ");
                                String inputValue = scn.nextLine();
                                userDB.update(user, inputAttr, inputValue);
                                System.out.println();
                                System.out.println("----------------------------");
                                System.out.println("=>Update model.User Succesfully!");
                            }

                            break;
                        case 3: //delete
                            System.out.println("============================");
                            scn.nextLine();
                            System.out.println();
                            //USER ID
                            System.out.print("type model.User ID: ");
                            inputID = scn.nextLine();
                            System.out.println();
                            user = userDB.select(inputID);
                            if(user == null){
                                System.out.println("----------------------------");
                                System.out.println("=>Not registered model.User!");
                            }
                            else {
                                userDB.delete(user);
                                System.out.println("----------------------------");
                                System.out.println("=>Delete model.User Successfully!");
                            }

                            break;

                        case 4: //select
                            System.out.println("============================");
                            scn.nextLine();
                            System.out.println();
                            //MUSIC NAME
                            System.out.print("type Value for Select: ");
                            String inputValue = scn.nextLine();
                            System.out.println();

                            System.out.println("----------------------------");
                            System.out.printf("%-8s| %-8s| %-20s| %-20s| %-28s| %-20s| %-8s|\n",
                                    "USER#","AUTH","ID","PW","ADDR","NAME","PLIST#");

                            ArrayList<User> userList = userDB.selectLike(inputValue);

                            for(int i=0; i<userList.size() ;i++){
                                user = userList.get(i);
                                System.out.printf("%8s| %8s| %-20s| %-20s| %-28s| %-20s| %8s|\n",
                                        user.num,user.isadmin,user.id,user.pw,user.addr,user.name,user.plist);
                            }
                            break;
                    }
                     break;

                //3. Log Out
                case 3:
                    System.out.println("=>Log-Out Succesfully!");
                    return;

            }
        }
    }
}
