package client;

//import org.springframework.http.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pojos.Account;

//import server.Application;

public class Connect {


    /** Get the email of a user with a concrete sessionID.
     */
    public static String getUsername() {
        String url = "http://localhost:8080/user/";
        url += Login.getSessionId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> requestBody = new HttpEntity<>(headers);
        ResponseEntity<String> response;
        response = restTemplate.exchange(url, HttpMethod.GET, requestBody,String.class);
        return response.getBody();
    }


    /** Registers a account.
     * @param account the account to register
     * @return true if the account is registered successfully, false otherwise
     */
    public static Boolean serverRegister(Account account) {
        String url = "http://localhost:8080/register";
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

        /**String resp;
        if (response == null) {
            resp = "Login Failed";
        } else {
            resp = "Logged in as " + name; //" with Session ID: " + response;
        }

        return resp;**/

        return response != null;
    }
}
