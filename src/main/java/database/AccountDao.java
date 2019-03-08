package database;

import pojos.Account;

import java.sql.*;

public class AccountDao extends Dao {

    public AccountDao() {
        super();
    }

    public boolean exists(String username) {
        if (this.getAccount(username) == null) {
            return false;
        }
        return true;
    }

    public Account getAccount(String username) {
        try {
            String query = "SELECT * FROM account WHERE username=?";

            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            Account a = null;
            if(rs.next()) {
                String name = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                int points = rs.getInt("total_points");
                a = new Account(name, email, password, firstname, lastname);
                a.setPoints(points);
            }
            rs.close();
            st.close();
            return a;

        } catch(Exception e) {

            System.out.println(e);
            return null;
        }

    }

    public boolean createAccount(Account acc) {
        try {
            String query = "INSERT INTO account (username, email, password," +
                    " total_points, first_name, last_name)" +
                    " VALUES  (?, ?, ?, ?, ?, ?)";

            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, acc.getUsername());
            st.setString(2, acc.getMail());
            st.setString(3, acc.getPassword());
            st.setInt(4, acc.getPoints());
            st.setString(5, acc.getFirstName());
            st.setString(6, acc.getLastName());

            st.execute();
            st.close();
            return true;

        } catch (Exception e) {

            System.out.println("we got into dao");
            System.out.println(e);
            return false;
        }

    }
    public String[] getNamesOfFriends(String username) {
    	try {
    		String[] ret = new String[10];
    		String query = "SELECT * From account order BY total_points DESC";
    		PreparedStatement st = this.conn.prepareStatement(query);
    		ResultSet rs = st.executeQuery();
    		int i = 0;
    		while(rs.next()) {
                String name = rs.getString("username");
                ret[i] = name;
                i++;
    		}
    		return ret;
    	} catch(Exception e) {
    		System.out.println(e);
    		return null;
    	}
    }
    public int[] getPointOfFriends(String username) {
    	try {
    		int[] ret = new int[10];
    		String query = "SELECT * From account order BY total_points DESC";
    		PreparedStatement st = this.conn.prepareStatement(query);
    		ResultSet rs = st.executeQuery();
    		int i = 0;
    		while(rs.next()) {
    			int points = rs.getInt("total_points");
    			ret[i] = points;
    			i++;
			}
    		return ret;
    	} catch(Exception e) {
    	System.out.println(e);
    	return null;
     	}
    }
    public void Addpoints(int toadd, String username) {
    	try {
    		String query = "SELECT total_points FROM account WHERE username=?";
    		PreparedStatement st1 = this.conn.prepareStatement(query);
    		st1.setString(1, username);
    		ResultSet rs = st1.executeQuery();
    		rs.next();
    		int plus = rs.getInt("total_points");
    		String query2 = "Update Account SET total_points=? WHERE username=?";
    		PreparedStatement st = this.conn.prepareStatement(query2);
    		st.setString(2, username);
    		st.setInt(1, toadd + plus);
    		st.executeQuery();
    		} catch(Exception e){
    			System.out.println(e);
    	}
    }
}
