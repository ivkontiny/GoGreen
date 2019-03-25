package client;

import javafx.util.Pair;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import pojos.Activity;
import pojos.Friendship;

import java.sql.Date;
import java.time.LocalDate;
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

    public static ArrayList<String> getFriends() {
        String url = url_default + "active_friendships/";
        url += ConnectAccount.getSessionId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        //HttpEntity<String> requestBody = new HttpEntity<>(headers);
        ResponseEntity<ArrayList<Friendship>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<ArrayList<Friendship>>() {
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
