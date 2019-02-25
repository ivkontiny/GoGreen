package client;

import org.json.JSONObject;

public class ParseResponse {

    public static String parseJson(String res) {
        JSONObject obj = new JSONObject(res);
        String response = obj.getString("response");
        return response;
    }
}
