package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

/**
 * Created by Pierrot on 2022-09-18.
 */
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class BreweryClient {

    public static final String BEER_API_PATH_V1 = "/api/v1/beer/";

    private String apiHost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerByID(UUID uuid) {
        String apiUrl = apiHost+BEER_API_PATH_V1+uuid.toString();
        return restTemplate.getForObject(apiUrl,BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto){
        return restTemplate.postForLocation(apiHost+BEER_API_PATH_V1,beerDto);
    }

    public void updateBeer(UUID beerUid, BeerDto beerDto) {
        restTemplate.put(apiHost+BEER_API_PATH_V1+beerUid.toString(), beerDto);
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
