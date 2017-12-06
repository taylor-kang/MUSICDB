import constants.DBConstants;
import controller.*;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by kangtaehee on 2017. 11. 28..
 */
public class UserDashboard {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://127.0.0.1:3307/MUSICDB";
    //static final String USERNAME = "root";
    //static final String PASSWORD = "1234";


    public static void printDashboard(Integer USERNUM) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        Statement stmt = null;

        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DBConstants.URL,DBConstants.USER,DBConstants.PASSWORD);
        stmt = conn.createStatement();

        MusicController musicDB = new MusicController();
        UserController userDB = new UserController();
        ArtistController artistController = new ArtistController();
        AlbumController albumDB = new AlbumController();
        MusicInPlaylistController musicInPlaylistDB = new MusicInPlaylistController();
        Scanner scn = new Scanner(System.in);


        User user = userDB.selectByNum(USERNUM);


        while(true) {

            //TODO linkg name for ID
            System.out.println("============================");
            System.out.println("    Hello, \n    " + user.name);
            System.out.println("----------------------------");
            System.out.println();
            System.out.println("1. My model.Playlist");
            System.out.println("2. Browse");
            System.out.println("3. My Info");
            System.out.println("4. Log-Out");
            System.out.println();
            System.out.println("----------------------------");
            System.out.print("Input: ");
            int input = scn.nextInt();

            switch (input) {
                //1. My model.Playlist
                case 1:

                    System.out.println("============================");
                    System.out.println("        My model.Playlist         ");
                    System.out.println("----------------------------");
                    System.out.println();
                    System.out.println("1. Show model.Playlist");
                    System.out.println("2. Add model.Music to model.Playlist");
                    System.out.println();
                    System.out.println("----------------------------");
                    System.out.print("Input: ");
                    input = scn.nextInt();

                    //Show all music in model.Playlist
                    if(input == 1){
                            System.out.println("============================");
                            System.out.println("          PLAYLIST          ");
                            System.out.println("----------------------------");

                            ArrayList<MusicInPlaylist> MIPList= musicInPlaylistDB.select("PLISTNUM", user.plist);
                            ArrayList<Music> musicList = new ArrayList<Music>();
                            for(int i=0; i< MIPList.size() ; i++) {
                                MusicInPlaylist MIP = MIPList.get(i);
                                Music music = musicDB.selectByNum(MIP.musicNum);
                                musicList.add(music);
                            }

                            System.out.printf("%-8s| %-20s| %-16s| %-20s| %-20s| %-8s|\n",
                                    "MUSIC#", "NAME","GENRE","ALBUM","ARTIST","TRACK#");

                            for(int i=0;i<musicList.size(); i++){
                                Music music = musicList.get(i);
                                System.out.printf("%8d| %-20s| %-16s| %-20s| %-20s| %8d|\n",
                                        music.num, music.name,music.genre,music.album,music.artist,music.trnum);

                            }

                        }

                        //Add music into model.Playlist
                        if(input ==2 ){

                            System.out.println("============================");
                            System.out.println("          Add model.Music         ");
                            System.out.println("----------------------------");
                            scn.nextLine();
                            System.out.println();
                            System.out.print("type model.Music Name: ");
                            String inputName = scn.nextLine();

                            System.out.print("type model.Artist Name: ");
                            String inputArtist = scn.nextLine();

                            Music music = musicDB.select(inputName, inputArtist);

                            System.out.println();

                            System.out.println("----------------------------");
                            if(music == null)
                                System.out.println("=>Not registered model.Music");
                            else {
                                MusicInPlaylist musicInPlaylist = new MusicInPlaylist(music.num, user.plist);
                                musicInPlaylistDB.insert(musicInPlaylist);
                                System.out.println("=>Add Succesfully!");
                            }

                    }
                    break;

                //2. Browse
                case 2:
                    System.out.println("============================");
                    System.out.println("           Browse           ");
                    System.out.println("----------------------------");
                    System.out.println();
                    System.out.println("1. Songs");
                    System.out.println("2. Artists");
                    System.out.println("3. Albums");
                    System.out.println("4. Genres");
                    System.out.println("0. Search");
                    System.out.println();
                    System.out.println("----------------------------");
                    System.out.print("Input: ");
                    input = scn.nextInt();

                    //Songs
                    if(input == 1) {
                        System.out.println("============================");
                        System.out.println("           Songs            ");
                        System.out.println("----------------------------");
                        System.out.printf("%-8s| %-20s| %-16s| %-20s| %-20s| %-8s|\n",
                                "MUSIC#", "NAME","GENRE","ALBUM","ARTIST","TRACK#");

                        ArrayList<Music> musicList = musicDB.selectAll();
                        for(int i=0; i<musicList.size(); i++){
                            Music music = musicList.get(i);
                            System.out.printf("%8d| %-20s| %-16s| %-20s| %-20s| %8d|\n",
                                    music.num, music.name,music.genre,music.album,music.artist,music.trnum);
                        }

                    }
                    //Artists
                    else if(input == 2){
                        System.out.println("============================");
                        System.out.println("          Artists           ");
                        System.out.println("----------------------------");
                        System.out.printf("%-8s| %-20s|\n","ARTIST#", "NAME");

                        ArrayList<Artist> artistList = artistController.selectAll();

                        for(int i=0; i<artistList.size(); i++){
                            Artist artist = artistList.get(i);
                            System.out.printf("%8d| %-20s| \n", artist.num, artist.name);
                        }

                    }
                    //Albums
                    else if(input == 3){
                        System.out.println("============================");
                        System.out.println("          Albums            ");
                        System.out.println("----------------------------");
                        System.out.printf("%-8s| %-20s| %-20s|\n","ALBUM#", "NAME","ARTIST");

                        ArrayList<Album> albumList = albumDB.selectAll();

                        for(int i=0 ;i<albumList.size(); i++){
                            Album album = albumList.get(i);
                            System.out.printf("%8d| %-20s| %-20s|\n",album.num, album.name, album.artist);
                        }

                    }
                    //Genres
                    else if(input == 4){
                        System.out.println("============================");
                        System.out.println("          Genres            ");
                        System.out.println("----------------------------");
                        System.out.println();
                        //GENUM
                        for(int i=0; i< Genre.NUM_GENRE ; i++){
                            System.out.printf("%d. %s\n", i+1, Genre.GENRE_LIST[i]);
                        }
                        System.out.println();
                        System.out.println("----------------------------");
                        System.out.print("type model.Genre Number: ");
                        int inputGenre = scn.nextInt()-1;
                        scn.nextLine();
                        System.out.println("----------------------------");
                        System.out.println("=> " + Genre.GENRE_LIST[inputGenre]);
                        System.out.println();
                                                                                                                                                    System.out.printf("%-8s| %-20s| %-20s| %-20s| %-8s|\n","MUSIC#", "NAME","ALBUM","ARTIST","TRACK#");

                        ArrayList<Music> musicList = musicDB.selectBy("GENAME", Genre.GENRE_LIST[inputGenre]);
                        for(int i=0; i<musicList.size(); i++){
                            Music music = musicList.get(i);
                            System.out.printf("%8d| %-20s| %-20s| %-20s| %8d|\n",
                                    music.num, music.name,music.album,music.artist,music.trnum);
                        }

                    }

                    //Search
                    else if(input == 0){
                        System.out.println("============================");
                        System.out.println("          Search            ");
                        System.out.println("----------------------------");
                        scn.nextLine();

                        System.out.println();
                        System.out.print("type Value for Search: ");
                        String inputValue = scn.nextLine();
                        System.out.println("----------------------------");
                        System.out.println();
                        System.out.printf("%-8s| %-20s| %-16s| %-20s| %-20s| %-8s|\n",
                                "MUSIC#", "NAME","GENRE","ALBUM","ARTIST","TRACK#");

                        ArrayList<Music> musicList = musicDB.selectLike(inputValue);
                        for(int i=0; i<musicList.size(); i++){
                            Music music = musicList.get(i);
                            System.out.printf("%8d| %-20s| %-16s| %-20s| %-20s| %8d|\n",
                                    music.num, music.name,music.genre,music.album,music.artist,music.trnum);
                        }
                        System.out.println();
                    }

                    break;

                //3. My Info
                case 3:
                    System.out.println("============================");
                    System.out.println("         My - Info          ");
                    System.out.println("----------------------------");
                    System.out.println();
                    System.out.println("ID : " + user.id);
                    System.out.println("PW : " + user.pw);
                    System.out.println("ADDRESS : " + user.addr);
                    if (user.isadmin == 0)
                        System.out.println("Authorization : USER");
                    else if (user.isadmin == 1)
                        System.out.println("Authorization : ADMIN");
                    System.out.println();
                    break;

                //4. Log Out
                case 4:
                    System.out.println("=>Log-Out Succesfully!");
                    return;

            }

        }


    }
}
