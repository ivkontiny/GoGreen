package client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import util.User;
//import server.Application;

/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;*/

public class Connect {


    /** Get the email of a user with a concrete sessionID.
     */
    public static void serverGetMail() {
        String url = "http://localhost:8080/user/";
        url += Login.getSessionId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> requestBody = new HttpEntity<>(headers);
        ResponseEntity<String> response;
        response = restTemplate.exchange(url, HttpMethod.GET, requestBody,String.class);
        System.out.println(response.getBody());
    }


    /** Registers a user.
     * @param user the user to register
     * @return true if the user is registered successfully, false otherwise
     */
    public static Boolean serverRegister(User user) {
        String url = "http://localhost:8080/register";
        url += "?username=" + user.getUsername();
        HttpHeaders headers = new HttpHeaders();
        // headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);

        // Send request with POST method.
        User response = restTemplate.postForObject(url, requestBody, User.class);
        if (response == null) {
            return false;
        }
        return true;
    }

    /** Tries to log a user in.
     * @param name username of the user
     * @param pass password of the user
     * @return returns whether the log in was successful or not
     */
    public static String serverLogin(String name, String pass) {

        String url = "http://localhost:8080/login";
        String logincredentials = name + ":" + pass;

        HttpHeaders headers = new HttpHeaders();
        //  headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<String> requestBody = new HttpEntity<>(logincredentials, headers);

        // Send request with POST method.
        String response = restTemplate.postForObject(url, requestBody, String.class);
        Login.setSessionId(response);

        String resp;
        if (response == null) {
            resp = "Login Failed";
        } else {
            resp = "Logged in as " + name; //" with Session ID: " + response;
        }

        return resp;
    }
}
