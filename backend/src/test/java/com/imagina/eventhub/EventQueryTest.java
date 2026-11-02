package com.imagina.eventhub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
class EventQueryTest {

    @Autowired
    private HttpGraphQlTester graphQlTester;

    @Test
    void eventsAreListedWithVenueAndDecimalPrices() {
        graphQlTester.document("{ events { title venue { name city } ticketTypes { name price } } }")
                .execute()
                .path("events").entityList(Object.class).hasSize(2)
                .path("events[0].venue.city").entity(String.class).isEqualTo("Madrid")
                .path("events[0].ticketTypes[0].price").entity(String.class).isEqualTo("95.00");
    }

    @Test
    void eventsCanBeFilteredByCity() {
        String title = graphQlTester.document("{ events(city: \"Barcelona\") { title } }")
                .execute()
                .path("events[0].title").entity(String.class).get();
        assertThat(title).isEqualTo("Concierto de Año Nuevo");
    }
}
