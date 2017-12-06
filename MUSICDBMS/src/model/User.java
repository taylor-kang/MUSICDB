package model;

public class User {
	public int num;
	public int isadmin;
	public String id;
	public String pw;
	public String addr;
	public String name;
	public int plist;
	
	public User() {}
	public User(int num, int isadmin, String id, String pw,
				String addr, String name,int plist) {
		this.num = num;
		this.isadmin = isadmin;
		this.id = id;
		this.pw = pw;
		this.addr = addr;
		this.name = name;
		this.plist = plist;

	}
}
