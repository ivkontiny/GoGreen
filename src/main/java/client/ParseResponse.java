package client;

import org.json.JSONObject;

public class ParseResponse {

    public static Boolean parseStatus(String res){
        JSONObject obj = new JSONObject(res);
        Boolean status = obj.getBoolean("status");
        return status;
    }
}
