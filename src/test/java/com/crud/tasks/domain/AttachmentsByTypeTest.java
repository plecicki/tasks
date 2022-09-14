package com.crud.tasks.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AttachmentsByTypeTest {

    @Test
    void setAndGetFieldsTest() {
        //Given
        AttachmentsByType attachments = new AttachmentsByType();
        Trello trello = new Trello();
        trello.setBoard(0);
        trello.setCard(0);
        //When
        attachments.setTrello(trello);
        Trello gotTrello = attachments.getTrello();
        //Then
        Assertions.assertEquals(0, gotTrello.getBoard());
        Assertions.assertEquals(0, gotTrello.getCard());
    }
}
