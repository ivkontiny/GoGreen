package server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import database.AccountDao;
import pojos.Account;
import services.AccountService;

@RestController
public class Leaderboards_Controller {
	AccountService as = new AccountService();
	
	@RequestMapping("/leaderboards")
	public Account[] Leaderboards() {
		AccountDao db = new AccountDao();
		try{ 
			return db.getLeaderboards();
		} catch (Exception e){
			System.out.println(e);
			return null;
		}
	}
}
