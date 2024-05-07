package models;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetConversionRate {
    //Variables
    String originalCurrency;
    String targetCurrency;

    //Constructors
    public GetConversionRate(String original, String target) {
        this.originalCurrency = original;
        this.targetCurrency = target;
    }

    //Methods
    //Obtain the conversion
    public double obtainRate() throws IOException, InterruptedException {
        //Make the url
        String url = "https://v6.exchangerate-api.com/v6/20b061fa282b421f31d53142/latest/" + originalCurrency;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        //Json response
        String json = response.body();

        //Gson
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
        return conversionRates.get(targetCurrency).getAsDouble();


    }

}
