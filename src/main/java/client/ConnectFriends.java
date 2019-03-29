package client;

import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;
import pojos.Activity;
import pojos.Friendship;

import java.util.ArrayList;
import java.util.HashMap;

public class ConnectFriends {

    private static String url_default = "http://localhost:8080/";
    private static HashMap<String, ArrayList<Activity>> acts = new HashMap<>();
    private static ArrayList<String> friends = new ArrayList<>();



    public static ArrayList<String> getLocalFriends() { return friends; }

    public static void setFriends(ArrayList<String> friends)
    {
        ConnectFriends.friends = friends;
    }

    public static HashMap<String, ArrayList<Activity>> getUsersActivities() {
        return acts;
    }

    /** sends request to a user.
     * *
     * @param receiver
     * @return if the request was sent successfully
     */
    public static boolean sendRequest(String receiver) {
        String url = url_default + "request/";
        url += ConnectAccount.getSessionId();
        url += "?username=";
        url += receiver;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(url,
                HttpMethod.GET,null,Boolean.class);
        return response.getBody();

    }
    /**
     * Adds the activities of the friends to the acts hash map.
     *
     * @param friends the friends we need to add
     */
    public static void getFriendActivities(ArrayList<String> friends) {
        String url = url_default + "friend_activities/";
        url += ConnectAccount.getSessionId();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        friends.add(ConnectAccount.getUsername());

        HttpEntity<ArrayList<String>> requestBody = new HttpEntity<>(friends);

        ResponseEntity<HashMap<String, ArrayList<Activity>>> response =
                restTemplate.exchange(url, HttpMethod.POST, requestBody,
                        new ParameterizedTypeReference<HashMap<String, ArrayList<Activity>>>() {
                        });

        for (String user : response.getBody().keySet()) {
            acts.put(user, response.getBody().get(user));
        }
    }

    /**
     * Get all the friends of the current user.
     * @return an array list containing all friends of the user
     */
    public static ArrayList<String> getFriends() {
        String url = url_default + "active_friendships/";
        url += ConnectAccount.getSessionId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        //HttpEntity<String> requestBody = new HttpEntity<>(headers);
        ResponseEntity<ArrayList<Friendship>> response = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<ArrayList<Friendship>>() {
                });

        ArrayList usernames = new ArrayList<>();
        for (Friendship search : response.getBody()) {
            if (search.getReceiver().equals(ConnectAccount.getUsername())) {
                usernames.add(search.getSender());
            } else {
                usernames.add(search.getReceiver());
            }
        }
        ConnectFriends.setFriends(usernames);
        return usernames;

    }
}
