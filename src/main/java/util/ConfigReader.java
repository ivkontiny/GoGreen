package util;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ConfigReader {
    String username;
    String password;
    String url;


    public ConfigReader(String path) {
        File xml = new File(path);

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xml);

            this.username = doc.getElementsByTagName("username").item(0).getTextContent();
            this.password = doc.getElementsByTagName("password").item(0).getTextContent();
            this.url = doc.getElementsByTagName("url").item(0).getTextContent();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
