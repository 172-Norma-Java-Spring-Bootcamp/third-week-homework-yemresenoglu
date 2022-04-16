package patika.dev.weather.restapi.controlleradvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@Slf4j
public class WeatherAPIControllerAdvice {

    /**The method that will send the error message to the client ınstead of the error message thrown by spring when it receives a "404 NOT FOUND" error.*/
    @ExceptionHandler
    public String CityNotFoundException(HttpClientErrorException httpClientErrorException)
    {
        String message;
        if (httpClientErrorException.getStatusCode() == HttpStatus.NOT_FOUND);
        {
            message="There is no ınformation about the entered city. You can try again in another city.";
        }
        return message;
    }

}
