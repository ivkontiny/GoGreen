package pojos;

import database.AccountDao;
import services.AccountService;

import java.util.ArrayList;

public class Leaderboard {

	private ArrayList<String> usernames;
	private ArrayList<Integer> points;
	AccountService as = new AccountService();
	AccountDao db = new AccountDao();
	public Leaderboard(ArrayList<String> usernames, ArrayList<Integer> points) {
		this.usernames = usernames;
		this.points = points;
	}
	public ArrayList<String> getUsernames() {
		return usernames;
	}
	public void setUsernames(ArrayList<String> usernames) {
		this.usernames = usernames;
	}
	public ArrayList<Integer> getPoints() {
		return points;
	}
	public void setPoints(ArrayList<Integer> points) {
		this.points = points;
	}
}
