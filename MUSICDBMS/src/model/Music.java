package model;

public class Music {
	public int num;
	public String name;
	public String genre;
	public String artist;
	public String album;
	public int trnum;
	public int likenum;
	
	public Music() { }
	public Music(int num, String name, String genre, String artist,
			String album, int trnum, int likenum) {
		this.num = num;
		this.name = name;
		this.genre = genre;
		this.artist = artist;
		this.album = album;
		this.trnum = trnum;
		this.likenum = likenum;
	}
}
