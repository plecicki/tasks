package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    void validateCardTest() {
        //Given
        TrelloCard trelloCard1 = new TrelloCard("First test name", "Descr1", "Pos1", "1");
        TrelloCard trelloCard2 = new TrelloCard("Normal name", "Descr2", "Pos2", "2");
        //When&Then
        trelloValidator.validateCard(trelloCard1);
        trelloValidator.validateCard(trelloCard2);
    }

    @Test
    void validateTrelloBoardsTest() {
        //Given
        List<TrelloBoard> trelloBoards = Arrays.asList(
                new TrelloBoard("1", "test", new ArrayList<>()),
                new TrelloBoard("2", "Name normal", new ArrayList<>()),
                new TrelloBoard("3", "Super board", new ArrayList<>()),
                new TrelloBoard("4", "TEST", new ArrayList<>()),
                new TrelloBoard("5", "My board", new ArrayList<>())
        );
        //When
        List<TrelloBoard> filteredTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        Assertions.assertEquals(3, filteredTrelloBoards.size());
        Assertions.assertEquals("2", filteredTrelloBoards.get(0).getId());
        Assertions.assertEquals("3", filteredTrelloBoards.get(1).getId());
        Assertions.assertEquals("5", filteredTrelloBoards.get(2).getId());
        Assertions.assertEquals("Name normal", filteredTrelloBoards.get(0).getName());
        Assertions.assertEquals("Super board", filteredTrelloBoards.get(1).getName());
        Assertions.assertEquals("My board", filteredTrelloBoards.get(2).getName());
    }
}
