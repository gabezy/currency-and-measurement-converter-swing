package challenge.one.app.currencyconvertor;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;


//USDBRL

public class APIValue {

    private double price;

    APIValue(String endpoint) {
        JSONParser parser = new JSONParser();
        try {
            URL obj = new URL("https://economia.awesomeapi.com.br/last/" + endpoint);
            String key = endpoint.replace("-", "");
            System.out.println(key);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            JSONObject json = (JSONObject) parser.parse(new InputStreamReader(connection.getInputStream()));

            JSONObject jsonPrice = (JSONObject) parser.parse(new StringReader(json.get(key).toString()));
            this.price = Double.parseDouble(jsonPrice.get("high").toString());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

    }

    public double getPrice() {
        return price;
    }
}
