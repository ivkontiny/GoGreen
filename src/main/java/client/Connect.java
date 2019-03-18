package client;

//import org.springframework.http.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pojos.Account;
import pojos.Activity;

import java.util.ArrayList;

//import server.Application;
//142.93.230.132:8080

public class Connect {


    private static String SESSION_ID = "";

    /** Get the sessionId of the local user.
     */
    public static String getSessionId() {
        return SESSION_ID;
    }

    /** Get the email of a user with a concrete sessionID.
     */
    public static String getUsername() {
        String url = "http://142.93.230.132:8080/user/";
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
        String url = "http://142.93.230.132:8080/logout/";
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
        String url = "http://142.93.230.132:8080/register";
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

        String url = "http://142.93.230.132:8080/login";
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
        String url = "http://142.93.230.132:8080/add_activity/";
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
        String url = "http://142.93.230.132:8080/get_activity/";
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

}
