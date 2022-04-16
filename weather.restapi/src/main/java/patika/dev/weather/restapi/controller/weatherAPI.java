package patika.dev.weather.restapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import patika.dev.weather.restapi.externalapi.weatherSource;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;


@RestController
@RequestMapping
@Validated
public class weatherAPI {

    weatherSource weatherSource= new weatherSource();

    @GetMapping("test/{city}")
    public ArrayList getCityData(@PathVariable("city") @Size(min = 2, max = 50, message = "Please enter a city name of more than 2 character and less than 50 characters.")
                                             String city) throws JsonProcessingException {

       return weatherSource.getCityData(city);

    }


}
