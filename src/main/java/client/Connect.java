package client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;*/
import java.util.Arrays;

public class Connect {

    public static Boolean serverRegister(User user)
    {
        String url = "http://localhost:8080/register";
        url += "?username=" + user.getUsername();
        HttpHeaders headers = new HttpHeaders();
     //  headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);

        // Send request with POST method.
        User response = restTemplate.postForObject(url, requestBody, User.class);
        if(response == null) return false;
        return true;
    }
    public static void serverLogin(String name, String pass){
        /*String url = "http://localhost:8080/login";
        url+="?username=";
        url = url + name;
        url = url + "&";
        url+="password=";
        url = url + pass;
        String cset = "UTF-8";

        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", cset);
            InputStream response = connection.getInputStream();

            String contentType = connection.getHeaderField("Content-Type");
            String charset = null;

            for (String param : contentType.replace(" ", "").split(";")) {
                if (param.startsWith("charset=")) {
                    charset = param.split("=", 2)[1];
                    break;
                }
            }

            if (charset != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, charset))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        System.out.println(ParseResponse.parseJson(line));
                    }
                }
            }
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL!");
        } catch (IOException e1) {
            System.out.println("IOException!");
        }*/

        String url = "http://localhost:8080/login";
        url += "?username=" + name;
        url += "&password=" + pass;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String result = response.getBody();

        System.out.println(ParseResponse.parseStatus(result));
    }
}
