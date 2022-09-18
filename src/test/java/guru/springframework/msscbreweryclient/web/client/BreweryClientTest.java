package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerByID() {
        BeerDto beerByID = breweryClient.getBeerByID(UUID.fromString("9e425689-d043-4528-9487-ae7a7e6f77fb"));
        assertThat(beerByID.getBeerName())
                .as("Check that Beer name is equal to \"%s\"", beerByID.getBeerName())
                .isEqualTo("Galaxy Cat");

        System.out.println(beerByID);
    }
}