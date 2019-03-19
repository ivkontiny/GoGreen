package client;

//import org.springframework.http.*;
import javafx.util.Pair;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pojos.Account;
import pojos.Activity;

import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

//import server.Application;
//142.93.230.132:8080

public class Connect {


    private static String SESSION_ID = "";
    private static String url_default = "http://localhost:8080/";
    private static HashMap<String, ArrayList<Activity>> acts = new HashMap<>();

    /** Get the sessionId of the local user.
     */
    public static String getSessionId() {
        return SESSION_ID;
    }


    public static HashMap<String, ArrayList<Activity>> getActs() {
        return acts;
    }

    /** Get the email of a user with a concrete sessionID.
     */
    public static String getUsername() {
        String url = url_default + "user/";
        url += SESSION_ID;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> requestBody = new HttpEntity<>(headers);
        ResponseEntity<String> response;
        response = restTemplate.exchange(url, HttpMethod.GET, requestBody,String.class);
        return response.getBody();
    }

    /** Logs out the user with a sessionID.
     */
    public static void logOut() {
        String url = url_default + "logout/";
        url += SESSION_ID;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestBody = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.GET, requestBody,String.class);
    }


    /** Registers a account.
     * @param account the account to register
     * @return true if the account is registered successfully, false otherwise
     */
    public static Boolean serverRegister(Account account) {
        String url = url_default + "register";
        url += "?username=" + account.getUsername();
        HttpHeaders headers = new HttpHeaders();
        // headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<Account> requestBody = new HttpEntity<>(account, headers);

        // Send request with POST method.
        boolean response = restTemplate.postForObject(url, requestBody, Boolean.class);
        return response;
    }

    /** Tries to log a user in.
     * @param name username of the user
     * @param pass password of the user
     * @return returns whether the log in was successful or not
     */
    public static boolean serverLogin(String name, String pass) {

        String url = url_default + "login";
        String credentials = name + ":" + pass;

        HttpHeaders headers = new HttpHeaders();
        //  headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<String> requestBody = new HttpEntity<>(credentials, headers);

        // Send request with POST method.
        String response = restTemplate.postForObject(url, requestBody, String.class);
        if (response != null) {
            SESSION_ID = response;
        }

        return response != null;
    }

    /**
     * Adds an activity to the user.
     * @param activity the activity to be added
     * @return true if the adding was successful, false otherwise
     */
    public static boolean addActivity(Activity activity) {
        //System.out.println("HERE!!");
        String url = url_default + "add_activity/";
        url += SESSION_ID;
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Activity> requestBody = new HttpEntity<>(activity, httpHeaders);

        boolean response = restTemplate.postForObject(url, requestBody, Boolean.class);
        System.out.println(response);
        return response;
    }

    /**
     * Gets all the activities of the user.
     * @return a list containing all the activities of a particular user
     */
    public static ArrayList<Activity> getActivities() {
        String url = url_default + "get_activity/";
        url += SESSION_ID;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        //HttpEntity<String> requestBody = new HttpEntity<>(headers);


        ResponseEntity<ArrayList<Activity>> response =
                restTemplate.exchange(url,HttpMethod.GET,null,
                new ParameterizedTypeReference<ArrayList<Activity>>(){});
        return response.getBody();
    }


    /**
     * Adds the activities of the friends to the acts hash map.
     * @param friends the friends we need to add
     */
    public static void addFriendActivities(ArrayList<String> friends) {
        String url = url_default + "/get_friend_activities_from_date/";
        url += SESSION_ID;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        for(String user : friends) {
            if(acts.containsKey(user)) {
                friends.remove(user);
            }
        }

        Pair<ArrayList<String>, Date> entity = new Pair<>(friends, Date.valueOf(LocalDate.now().minusDays(7)));
        HttpEntity<Pair<ArrayList<String>, Date>> requestBody = new HttpEntity<>(entity, headers);

        ResponseEntity<HashMap<String, ArrayList<Activity>>> response =
                restTemplate.exchange(url, HttpMethod.GET, requestBody,
                        new ParameterizedTypeReference<HashMap<String, ArrayList<Activity>>>(){});

        for (String user : response.getBody().keySet()) {
            acts.put(user, response.getBody().get(user));
        }
    }
}
