package patika.dev.weather.restapi.externalapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Arrays;
import static org.springframework.http.MediaType.APPLICATION_JSON;


@RestController
@AllArgsConstructor
@NoArgsConstructor
public class weatherSource  {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    ObjectMapper objectMapper = new ObjectMapper();


/**The method that brings the current weather conditions and city's ınformation according to the "city".*/
    @GetMapping()
    public ArrayList getCityData(String city) throws JsonProcessingException {
        String apiURL = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=d242d3f8e8e8c6a75a0067640f6b9f11";
        headers.setAccept(Arrays.asList(APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(apiURL, HttpMethod.GET, entity, String.class);
        return convert(response);
    }

/**Created the "convert" method to get the data we want from the JSON data coming with the "response" object and send them to the local server as a response.*/
    private ArrayList convert(ResponseEntity<String> response) throws JsonProcessingException {

        JsonNode root = objectMapper.readTree(response.getBody());
        ArrayList data = new ArrayList<>();


            data.add(root.path("coord").path("lon").asDouble()); //city longitude
            data.add(root.path("coord").path("lat").asDouble()); //city latitude
            data.add(root.path("main").path("feels_like").asDouble()); //felt temperature
            data.add(root.path("sys").path("country").asText()); // city country
            data.add(root.path("name").asText()); // city name

            return data;
    }

}
