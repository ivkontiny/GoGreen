package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Connect {

    public static void serverConnection(String name, String pass){
        String url = "http://localhost:8080/login";
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

            for(String param: contentType.replace(" ", "").split(";")){
                if(param.startsWith("charset=")){
                    charset = param.split("=", 2)[1];
                    break;
                }
            }

            if(charset != null){
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(response, charset))){
                    for(String line; (line = reader.readLine()) != null;){
                        System.out.println(ParseResponse.parseJson(line));
                    }
                }
            }
        }
        catch(MalformedURLException e){
            System.out.println("Malformed URL!");
        }
        catch(IOException e1){
            System.out.println("IOException!");
        }
    }
}
