package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TrelloConfigTest {

    @Autowired
    TrelloConfig trelloConfig;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpointTest;
    @Value("${trello.app.username}")
    private String trelloUsernameTest;
    @Value("${trello.app.key}")
    private String trelloAppKeyTest;
    @Value("${trello.app.token}")
    private String trelloTokenTest;

    @Test
    void trelloConfigGettersTest() {
        //Given&When
        //Fields and Annotations
        //Then
        Assertions.assertEquals(trelloApiEndpointTest, trelloConfig.getTrelloApiEndpoint());
        Assertions.assertEquals(trelloUsernameTest, trelloConfig.getTrelloUsername());
        Assertions.assertEquals(trelloAppKeyTest, trelloConfig.getTrelloAppKey());
        Assertions.assertEquals(trelloTokenTest, trelloConfig.getTrelloToken());
    }
}
