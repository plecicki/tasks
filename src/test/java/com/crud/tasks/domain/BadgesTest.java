package com.crud.tasks.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BadgesTest {

    @Test
    void getAndSetFieldsTest() {
        //Given
        Badges badges = new Badges();
        //When
        badges.setVotes(10);
        AttachmentsByType attachments = new AttachmentsByType();
        badges.setAttachments(attachments);
        int gotVotes = badges.getVotes();
        AttachmentsByType gotAttachmentsByType = badges.getAttachments();
        //Then
        Assertions.assertEquals(10, badges.getVotes());
        Assertions.assertEquals(attachments, gotAttachmentsByType);
    }
}
