package constants;

import java.sql.*;

public class DBConstants {
	public static String DBNAME = "test2";
	public static String PORTNUM = "3307";
	public static String URL = "jdbc:mysql://127.0.0.1:" + PORTNUM + "/" + DBNAME;
	public static String USER = "root";
	public static String PASSWORD = "1234";
	
	public static final class Table {
		public static final String MUSIC = "MUSIC";
		public static final String USER = "USER";
		public static final String ALBUM = "ALBUM";
		public static final String ARTIST = "ARTIST";
		public static final String GENRE = "GENRE";
		public static final String LIKELIST = "LIKELIST";
		public static final String PLAYLIST = "PLAYLIST";
		public static final String MUSICINLIKELIST = "MUSICINLIKELIST";
		public static final String MUSICINPLAYLIST = "MUSICINPLAYLIST";


	}
	
	public static final class Column {
		public static final String USER_NUM = "USERNUM";
		public static final String USER_ISADMIN = "ISADMIN";
		public static final String USER_ID = "USERID";
		public static final String USER_PW = "USERPW";
		public static final String USER_ADDR = "USERADDR";
		public static final String USER_NAME = "USERNAME";
		public static final String USER_PLIST = "USERPLIST";
		
		public static final String MUSIC_NUM = "MUSICNUM";
		public static final String MUSIC_NAME = "MUSICNAME";
		public static final String MUSIC_GENRE = "GENAME";
		public static final String MUSIC_ALBUM = "ALBUMNAME";
		public static final String MUSIC_ARTIST = "ARTISTNAME";
		public static final String MUSIC_TRACK = "TRNUM";
		public static final String MUSIC_LIKENUM = "LIKENUM";

		public static final String ALBUM_NUM = "ALBUMNUM";
		public static final String ALBUM_NAME = "ALBUMNAME";
		public static final String ALBUM_ARTIST = "ARTISTNAME";

		public static final String ARTIST_NUM = "ARTISTNUM";
		public static final String ARTIST_NAME = "ARTISTNAME";

		public static final String LLIST_NUM = "LLISTNUM";
		public static final String LLIST_NAME = "LLISTNAME";
		public static final String LLIST_OWNER = "OWNER";

		public static final String PLIST_NUM = "PLISTNUM";
		public static final String PLIST_OWNER = "OWNER";



	}
	
	public static final class CreateDB {

		//create table
		public static final String CREATE_TABLE_USER =
				"create table " + Table.USER + " ( "
						+ Column.USER_NUM + " int(11) primary key auto_increment, "
						+ Column.USER_ISADMIN + " int(1) not null, "
						+ Column.USER_ID + " varchar(16) not null, "
						+ Column.USER_PW + " varchar(16) not null, "
						+ Column.USER_ADDR + " varchar(48) not null, "
						+ Column.USER_NAME + " varchar(20) not null, "
						+ Column.USER_PLIST + " int(11) not null )";
		
		public static final String CREATE_TABLE_MUSIC =
				"create table " + Table.MUSIC + " ( "
						+ Column.MUSIC_NUM + " int(11) primary key auto_increment, "
						+ Column.MUSIC_NAME + " varchar(32) not null, "
						+ Column.MUSIC_GENRE + " varchar(16) not null, "
						+ Column.MUSIC_ALBUM + " varchar(32) not null, "
						+ Column.MUSIC_ARTIST + " varchar(32) not null, "
						+ Column.MUSIC_TRACK + " int(11) not null, "
						+ Column.MUSIC_LIKENUM + " int(11) not null )";
		public static final String CREATE_TABLE_ALBUM =
				"create table " + Table.ALBUM + " ( "
						+ Column.ALBUM_NUM + " int(11) primary key auto_increment, "
						+ Column.ALBUM_NAME + " varchar(32) not null, "
						+ Column.ALBUM_ARTIST + " varchar(32) not null )";
		public static final String CREATE_TABLE_ARTIST =
				"create table " + Table.ARTIST + " ( "
						+ Column.ARTIST_NUM + " int(11) primary key auto_increment, "
						+ Column.ARTIST_NAME + " varchar(32) not null )";

		public static final String CREATE_TABLE_LIKELIST =
				"create table " + Table.LIKELIST + " ( "
						+ Column.LLIST_NUM + " int(11) primary key auto_increment, "
						+ Column.LLIST_NAME + " varchar(32) not null, "
						+ Column.LLIST_OWNER + " int(11) not null )";
		public static final String CREATE_TABLE_PLAYLIST =
				"create table " + Table.PLAYLIST + " ( "
						+ Column.PLIST_NUM + " int(11) primary key auto_increment, "
						+ Column.PLIST_OWNER + " int(11) not null )";
		public static final String CREATE_TABLE_MUSICINLIKELIST =
				"create table " + Table.MUSICINLIKELIST + " ( "
						+ Column.MUSIC_NUM + " int(11) not null, "
						+ Column.LLIST_NUM + " int(11) not null, "
						+ "primary key (" + Column.MUSIC_NUM + "," + Column.LLIST_NUM + "))";
		public static final String CREATE_TABLE_MUSICINPLAYLIST =
				"create table " + Table.MUSICINPLAYLIST + " ( "
						+ Column.MUSIC_NUM + " int(11) not null, "
						+ Column.PLIST_NUM + " int(11) not null, "
						+ "primary key (" + Column.MUSIC_NUM + "," + Column.PLIST_NUM + "))";

	}
	public static final class testCase {
		//insert test database
		public static final String INSERT_MUSIC_DATA1 =
				"INSERT INTO `MUSIC`(`MUSICNUM`, `MUSICNAME`, `GENAME`, `ALBUMNAME`, `ARTISTNAME`, `TRNUM`, `LIKENUM`) "
						+ "VALUES (null,'too good','Ballad','the thrill of it','sam smith','1','0')";
		public static final String INSERT_MUSIC_DATA2 =
				"INSERT INTO `MUSIC`(`MUSICNUM`, `MUSICNAME`, `GENAME`, `ALBUMNAME`, `ARTISTNAME`, `TRNUM`, `LIKENUM`) "
						+ "VALUES (null,'say it first','Ballad','the thrill of it','sam smith','2','0')";
		public static final String INSERT_MUSIC_DATA3 =
				"INSERT INTO `MUSIC`(`MUSICNUM`, `MUSICNAME`, `GENAME`, `ALBUMNAME`, `ARTISTNAME`, `TRNUM`, `LIKENUM`) "
						+ "VALUES (null,'palace','Ballad','the thrill of it','sam smith','9','0')";
		public static final String INSERT_MUSIC_DATA4 =
				"INSERT INTO `MUSIC`(`MUSICNUM`, `MUSICNAME`, `GENAME`, `ALBUMNAME`, `ARTISTNAME`, `TRNUM`, `LIKENUM`) "
						+ "VALUES (null,'zeze','Dance','chat-shire','iu','2','0')";
		public static final String INSERT_MUSIC_DATA5 =
				"INSERT INTO `MUSIC`(`MUSICNUM`, `MUSICNAME`, `GENAME`, `ALBUMNAME`, `ARTISTNAME`, `TRNUM`, `LIKENUM`) "
						+ "VALUES (null,'red queen','Pop','chat-shire','iu','5','0')";
		public static final String INSERT_MUSIC_DATA6 =
				"INSERT INTO `MUSIC`(`MUSICNUM`, `MUSICNAME`, `GENAME`, `ALBUMNAME`, `ARTISTNAME`, `TRNUM`, `LIKENUM`) "
						+ "VALUES (null,'stay','R&B','square two','blackpink','1','0')";
		public static final String INSERT_MUSIC_DATA7 =
				"INSERT INTO `MUSIC`(`MUSICNUM`, `MUSICNAME`, `GENAME`, `ALBUMNAME`, `ARTISTNAME`, `TRNUM`, `LIKENUM`) "
						+ "VALUES (null,'i love the winter','Pop','christmas ed','tony Bennet','1','0')";
		public static final String INSERT_MUSIC_DATA8 =
				"INSERT INTO `MUSIC`(`MUSICNUM`, `MUSICNAME`, `GENAME`, `ALBUMNAME`, `ARTISTNAME`, `TRNUM`, `LIKENUM`) "
						+ "VALUES (null,'white christmas','Jazz','buble Christmas','michael buble','3','0')";
		public static final String INSERT_MUSIC_DATA9 =
				"INSERT INTO `MUSIC`(`MUSICNUM`, `MUSICNAME`, `GENAME`, `ALBUMNAME`, `ARTISTNAME`, `TRNUM`, `LIKENUM`) "
						+ "VALUES (null,'thunder','Rock','crazy world','boys like girls','5','0')";
		public static final String INSERT_MUSIC_DATA10 =
				"INSERT INTO `MUSIC`(`MUSICNUM`, `MUSICNAME`, `GENAME`, `ALBUMNAME`, `ARTISTNAME`, `TRNUM`, `LIKENUM`) "
						+ "VALUES (null,'summer','Electronic','my way','calvin harris','1','0')";

		public static final String INSERT_ARTIST_DATA1 =
				"INSERT INTO `ARTIST`(`ARTISTNUM`, `ARTISTNAME`) VALUES ('1','sam smith')";
		public static final String INSERT_ARTIST_DATA2 =
				"INSERT INTO `ARTIST`(`ARTISTNUM`, `ARTISTNAME`) VALUES ('2','iu')";
		public static final String INSERT_ARTIST_DATA3 =
				"INSERT INTO `ARTIST`(`ARTISTNUM`, `ARTISTNAME`) VALUES ('3','blackpink')";
		public static final String INSERT_ARTIST_DATA4 =
				"INSERT INTO `ARTIST`(`ARTISTNUM`, `ARTISTNAME`) VALUES ('4','tony Bennet')";
		public static final String INSERT_ARTIST_DATA5 =
				"INSERT INTO `ARTIST`(`ARTISTNUM`, `ARTISTNAME`) VALUES ('5','michael buble')";
		public static final String INSERT_ARTIST_DATA6 =
				"INSERT INTO `ARTIST`(`ARTISTNUM`, `ARTISTNAME`) VALUES ('6','boys like girls')";
		public static final String INSERT_ARTIST_DATA7 =
				"INSERT INTO `ARTIST`(`ARTISTNUM`, `ARTISTNAME`) VALUES ('7','calvin harris')";


		public static final String INSERT_ALBUM_DATA1 =
				"INSERT INTO `ALBUM`(`ALBUMNUM`, `ALBUMNAME`, `ARTISTNAME`) VALUES ('1','the thrill of it','sam smith')";
		public static final String INSERT_ALBUM_DATA2 =
				"INSERT INTO `ALBUM`(`ALBUMNUM`, `ALBUMNAME`, `ARTISTNAME`) VALUES ('2','chat-shire','iu')";
		public static final String INSERT_ALBUM_DATA3 =
				"INSERT INTO `ALBUM`(`ALBUMNUM`, `ALBUMNAME`, `ARTISTNAME`) VALUES ('3','square two','blackpink')";
		public static final String INSERT_ALBUM_DATA4 =
				"INSERT INTO `ALBUM`(`ALBUMNUM`, `ALBUMNAME`, `ARTISTNAME`) VALUES ('4','christmas ed','tony Bennet')";
		public static final String INSERT_ALBUM_DATA5 =
				"INSERT INTO `ALBUM`(`ALBUMNUM`, `ALBUMNAME`, `ARTISTNAME`) VALUES ('5','buble Christmas','michael buble')";
		public static final String INSERT_ALBUM_DATA6 =
				"INSERT INTO `ALBUM`(`ALBUMNUM`, `ALBUMNAME`, `ARTISTNAME`) VALUES ('6','crazy world','boys like girls')";
		public static final String INSERT_ALBUM_DATA7 =
				"INSERT INTO `ALBUM`(`ALBUMNUM`, `ALBUMNAME`, `ARTISTNAME`) VALUES ('7','my way','calvin harris')";


		public static final String INSERT_USER_DATA1 =
				"INSERT INTO `USER`(`USERNUM`, `ISADMIN`, `USERID`, `USERPW`, `USERADDR`, `USERNAME`, `USERPLIST`) "
						+ "VALUES ('1','1','admin','admin','admin@google.com','admin','1')";
		public static final String INSERT_USER_DATA2 =
				"INSERT INTO `USER`(`USERNUM`, `ISADMIN`, `USERID`, `USERPW`, `USERADDR`, `USERNAME`, `USERPLIST`) "
						+ "VALUES ('2','0','user1','user1','rkdxogml@google.com','taylor','2')";
		public static final String INSERT_USER_DATA3 =
				"INSERT INTO `USER`(`USERNUM`, `ISADMIN`, `USERID`, `USERPW`, `USERADDR`, `USERNAME`, `USERPLIST`) "
						+ "VALUES ('3','0','user2','user2','michael@google.com','jackson','3')";
		public static final String INSERT_USER_DATA4 =
				"INSERT INTO `USER`(`USERNUM`, `ISADMIN`, `USERID`, `USERPW`, `USERADDR`, `USERNAME`, `USERPLIST`) "
						+ "VALUES ('4','0','user3','user3','tony@google.com','tony stark','4')";
		public static final String INSERT_USER_DATA5 =
				"INSERT INTO `USER`(`USERNUM`, `ISADMIN`, `USERID`, `USERPW`, `USERADDR`, `USERNAME`, `USERPLIST`) "
						+ "VALUES ('5','0','user4','user4','captain@google.com','captain_america','5')";
		public static final String INSERT_USER_DATA6 =
				"INSERT INTO `USER`(`USERNUM`, `ISADMIN`, `USERID`, `USERPW`, `USERADDR`, `USERNAME`, `USERPLIST`) "
						+ "VALUES ('6','0','user5','user5','hulk@google.com','hulk','6')";
		public static final String INSERT_USER_DATA7 =
				"INSERT INTO `USER`(`USERNUM`, `ISADMIN`, `USERID`, `USERPW`, `USERADDR`, `USERNAME`, `USERPLIST`) "
						+ "VALUES ('7','0','user6','user6','thor@google.com','thor','7')";
		public static final String INSERT_USER_DATA8 =
				"INSERT INTO `USER`(`USERNUM`, `ISADMIN`, `USERID`, `USERPW`, `USERADDR`, `USERNAME`, `USERPLIST`) "
						+ "VALUES ('8','0','user7','user7','black@google.com','black_widow','8')";
		public static final String INSERT_USER_DATA9 =
				"INSERT INTO `USER`(`USERNUM`, `ISADMIN`, `USERID`, `USERPW`, `USERADDR`, `USERNAME`, `USERPLIST`) "
						+ "VALUES ('9','0','user8','user8','loki@google.com','loki','9')";
		public static final String INSERT_USER_DATA10 =
				"INSERT INTO `USER`(`USERNUM`, `ISADMIN`, `USERID`, `USERPW`, `USERADDR`, `USERNAME`, `USERPLIST`) "
						+ "VALUES ('10','0','user9','user9','kdaniel@google.com','kangdaniel','10')";
		public static final String INSERT_USER_DATA11 =
				"INSERT INTO `USER`(`USERNUM`, `ISADMIN`, `USERID`, `USERPW`, `USERADDR`, `USERNAME`, `USERPLIST`) "
						+ "VALUES ('11','0','user10','user10','hwang@google.com','minhyeon','11')";

		public static final String INSERT_PLIST_DATA1 =
				"INSERT INTO `PLAYLIST`(`PLISTNUM`, `OWNER`) VALUES ('1','1')";
		public static final String INSERT_PLIST_DATA2 =
				"INSERT INTO `PLAYLIST`(`PLISTNUM`, `OWNER`) VALUES ('2','2')";
		public static final String INSERT_PLIST_DATA3 =
				"INSERT INTO `PLAYLIST`(`PLISTNUM`, `OWNER`) VALUES ('3','3')";
		public static final String INSERT_PLIST_DATA4 =
				"INSERT INTO `PLAYLIST`(`PLISTNUM`, `OWNER`) VALUES ('4','4')";
		public static final String INSERT_PLIST_DATA5 =
				"INSERT INTO `PLAYLIST`(`PLISTNUM`, `OWNER`) VALUES ('5','5')";
		public static final String INSERT_PLIST_DATA6 =
				"INSERT INTO `PLAYLIST`(`PLISTNUM`, `OWNER`) VALUES ('6','6')";
		public static final String INSERT_PLIST_DATA7 =
				"INSERT INTO `PLAYLIST`(`PLISTNUM`, `OWNER`) VALUES ('7','7')";
		public static final String INSERT_PLIST_DATA8 =
				"INSERT INTO `PLAYLIST`(`PLISTNUM`, `OWNER`) VALUES ('8','8')";
		public static final String INSERT_PLIST_DATA9 =
				"INSERT INTO `PLAYLIST`(`PLISTNUM`, `OWNER`) VALUES ('9','9')";
		public static final String INSERT_PLIST_DATA10 =
				"INSERT INTO `PLAYLIST`(`PLISTNUM`, `OWNER`) VALUES ('10','10')";
		public static final String INSERT_PLIST_DATA11 =
				"INSERT INTO `PLAYLIST`(`PLISTNUM`, `OWNER`) VALUES ('11','11')";



	}
}
