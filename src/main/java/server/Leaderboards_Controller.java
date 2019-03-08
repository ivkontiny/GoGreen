package server;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import database.AccountDao;
import pojos.Account;
import pojos.Leaderboards;
import services.AccountService;

@RestController
public class Leaderboards_Controller {
	AccountService as = new AccountService();
	
	@RequestMapping("/Leaderboards/{username}")
	public Leaderboards getLeaderboards(@PathVariable("username") String username) {
		Leaderboards ret = new Leaderboards(username);
		return  ret;
	}
	@RequestMapping("/addpoints/{username}")
	public String addpoints(@PathVariable("username")String username) {
		try {
		AccountDao db = new AccountDao();
		db.Addpoints(1000, username);
		return "Succesfully added points to" + username;
		} catch(Exception e) {
			return e.toString();
		}
	}
}
