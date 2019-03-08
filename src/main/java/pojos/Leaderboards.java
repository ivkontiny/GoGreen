package pojos;

import database.AccountDao;
import services.AccountService;

public class Leaderboards {
	private String[] usernames;
	private int[] points;
	AccountService as = new AccountService();
	AccountDao db = new AccountDao();
	public Leaderboards(String username) {
		this.usernames = db.getNamesOfFriends(username);
		this.points = db.getPointOfFriends(username);
	}
	public String[] getUsernames() {
		return usernames;
	}
	public void setUsernames(String[] usernames) {
		this.usernames = usernames;
	}
	public int[] getPoints() {
		return points;
	}
	public void setPoints(int[] points) {
		this.points = points;
	}
}
