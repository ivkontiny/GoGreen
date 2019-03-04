package client;

import org.json.JSONObject;

public class ParseResponse {

    /** Used to parse the JSON object returned from the server and get the status.
     * @param res the response from the server
     * @return the status attribute of the response
     */
    public static Boolean parseStatus(String res) {
        JSONObject obj = new JSONObject(res);
        Boolean status = obj.getBoolean("status");
        return status;
    }
}
