package server;

import database.LeaderboardDao;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pojos.Leaderboard;
import services.LeaderboardService;

@RestController
public class LeaderboardController {

	LeaderboardService ls = new LeaderboardService();
	
	@RequestMapping("/leaderboard/{username}")
	public Leaderboard getLeaderboard(@PathVariable("username") String username) {
		Leaderboard myleaderboard = ls.getLeaderboard(username);
		return  myleaderboard;
	}
	@RequestMapping("/addpoints/{username}")
	public String addPoints(@PathVariable("username")String username) {
		try {
		LeaderboardDao db = new LeaderboardDao();
		ls.getDb().addPoints(1000, username);
		return "Succesfully added points to" + username;
		} catch(Exception e) {
			return e.toString();
		}
	}
}
