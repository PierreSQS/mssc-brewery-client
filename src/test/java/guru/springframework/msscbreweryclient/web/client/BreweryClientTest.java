package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BreweryClientTest {

    BeerDto beerDto;

    CustomerDto customerDto;

    @Autowired
    BreweryClient breweryClient;


    @BeforeEach
    void setUp() {
        beerDto = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("33 Export")
                .beerStyle("Pils")
                .upc(123456789L)
                .build();

        customerDto = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Pierrot Tests")
                .build();

    }

    @Test
    void getBeerByID() {
        BeerDto beerByID = breweryClient.getBeerByID(UUID.fromString("9e425689-d043-4528-9487-ae7a7e6f77fb"));
        assertThat(beerByID.getBeerName())
                .as("Check that Beer name is equal to \"%s\"", beerByID.getBeerName())
                .isEqualTo("Galaxy Cat");

        System.out.println(beerByID);
    }

    @Test
    void saveNewBeer() {
        BeerDto newBeerDto = beerDto;
        URI uri = breweryClient.saveNewBeer(newBeerDto);
        System.out.println(uri);
        assertThat(uri.getPath())
                .as("Check that Path is like \"%s\"", uri.getPath())
                .contains("/api/v1/beer/");
    }

    @Test
    void updateBeer() {
        BeerDto beerDtoToUpdate = beerDto;
        breweryClient.updateBeer(UUID.randomUUID(), beerDtoToUpdate);
    }

    @Test
    void getCustomerByID() {
        CustomerDto customerByID = breweryClient.getCustomerByID(UUID.randomUUID());
        String customer = customerByID.getName();
        assertThat(customer)
                .as("Check that Customer Name is equal to \"%s\"", customer)
                .isEqualTo("Joe Buck");
    }

    @Test
    void saveCustomer() {
        String regex = "[/api/v1/customer/]\\S+";
        URI uri = breweryClient.saveCustomer(customerDto);
        assertThat(uri.getPath())
                .as("Check that Customer Name matches \"%s\"", BreweryClient.CUSTOMER_API_PATH_V1)
                .matches(regex);
        System.out.println(uri);
    }

    @Test
    void updateCustomerByID() {
        breweryClient.updateCustomer(UUID.randomUUID(),customerDto);
    }

    @Test
    void deleteCustomerByID() {
        breweryClient.deleteCustomer(UUID.randomUUID());
    }
}