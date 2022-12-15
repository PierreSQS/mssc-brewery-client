package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
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
    public static final String CUSTOMER_API_PATH_V1 = "/api/v1/customer/";

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

    public CustomerDto getCustomerByID(UUID customerId) {
        String apiUrl = String.format("%s/%s/%s",apiHost,CUSTOMER_API_PATH_V1, customerId);
        return restTemplate.getForObject(apiUrl, CustomerDto.class);
    }

    public URI saveCustomer(CustomerDto customerDto) {
        String apiUrl = String.format("%s/%s",apiHost,CUSTOMER_API_PATH_V1);
        return restTemplate.postForLocation(apiUrl, customerDto);
    }

    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        String apiUrl = String.format("%s/%s/%s",apiHost,CUSTOMER_API_PATH_V1, UUID.randomUUID());
        restTemplate.put(apiUrl,customerId,customerDto);
    }

    public void deleteCustomer(UUID customerId) {
        String apiUrl = String.format("%s/%s/%s",apiHost,CUSTOMER_API_PATH_V1, UUID.randomUUID());
        restTemplate.delete(apiUrl,customerId);
    }
}
