package client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import pojos.Activity;
import pojos.Category;
import pojos.DefaultValue;

import java.util.ArrayList;

public class ConnectActivity extends Connect {

    public static ArrayList<String> getFood(Category category) {
        String url = url_default + "get_descriptions_by_category";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Category> requestEntity = new HttpEntity<>(category);


        ResponseEntity<ArrayList<String>> response =
                restTemplate.exchange(url, HttpMethod.POST, requestEntity,
                        new ParameterizedTypeReference<ArrayList<String>>() {
                        });
        return response.getBody();
    }

    public static DefaultValue getConsumption(String description) {
        String url = url_default + "get_consumption_by_description";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> requestEntity = new HttpEntity<>(description);


        ResponseEntity<DefaultValue> response =
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, DefaultValue.class);
        return response.getBody();
    }
}
