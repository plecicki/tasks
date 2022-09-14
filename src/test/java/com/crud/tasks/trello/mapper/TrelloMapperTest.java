package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    private List<TrelloList> createListWithTrelloLists() {
        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(new TrelloList("1", "To do", false));
        trelloListList.add(new TrelloList("2", "In progress", false));
        trelloListList.add(new TrelloList("3", "Done", false));
        return trelloListList;
    }

    private List<TrelloListDto> createListWithTrelloListDtos() {
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(new TrelloListDto("1", "To do", false));
        trelloListDtoList.add(new TrelloListDto("2", "In progress", false));
        trelloListDtoList.add(new TrelloListDto("3", "Done", false));
        return trelloListDtoList;
    }

    @Test
    void mapToBoardsTest() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("1", "Board1", createListWithTrelloListDtos()));
        trelloBoardDtoList.add(new TrelloBoardDto("2", "Board2", createListWithTrelloListDtos()));
        trelloBoardDtoList.add(new TrelloBoardDto("3", "Board3", createListWithTrelloListDtos()));
        trelloBoardDtoList.add(new TrelloBoardDto("4", "Board4", createListWithTrelloListDtos()));
        trelloBoardDtoList.add(new TrelloBoardDto("5", "Board5", createListWithTrelloListDtos()));

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        Assertions.assertEquals(5, trelloBoardList.size());
        Assertions.assertEquals("1", trelloBoardList.get(0).getId());
        Assertions.assertEquals("2", trelloBoardList.get(1).getId());
        Assertions.assertEquals("3", trelloBoardList.get(2).getId());
        Assertions.assertEquals("4", trelloBoardList.get(3).getId());
        Assertions.assertEquals("5", trelloBoardList.get(4).getId());
    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("1", "Board1", createListWithTrelloLists()));
        trelloBoardList.add(new TrelloBoard("2", "Board2", createListWithTrelloLists()));
        trelloBoardList.add(new TrelloBoard("3", "Board3", createListWithTrelloLists()));
        trelloBoardList.add(new TrelloBoard("4", "Board4", createListWithTrelloLists()));
        trelloBoardList.add(new TrelloBoard("5", "Board5", createListWithTrelloLists()));

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        Assertions.assertEquals(5, trelloBoardDtoList.size());
        Assertions.assertEquals("1", trelloBoardDtoList.get(0).getId());
        Assertions.assertEquals("2", trelloBoardDtoList.get(1).getId());
        Assertions.assertEquals("3", trelloBoardDtoList.get(2).getId());
        Assertions.assertEquals("4", trelloBoardDtoList.get(3).getId());
        Assertions.assertEquals("5", trelloBoardDtoList.get(4).getId());
    }

    @Test
    void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListDtoList = createListWithTrelloListDtos();

        //When
        List<TrelloList> trelloListList = trelloMapper.mapToList(trelloListDtoList);

        //Then
        Assertions.assertEquals(3, trelloListList.size());
        Assertions.assertEquals("1", trelloListList.get(0).getId());
        Assertions.assertEquals("2", trelloListList.get(1).getId());
        Assertions.assertEquals("3", trelloListList.get(2).getId());
    }

    @Test
    void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloListList = createListWithTrelloLists();

        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloListList);

        //Then
        Assertions.assertEquals(3, trelloListDtoList.size());
        Assertions.assertEquals("1", trelloListDtoList.get(0).getId());
        Assertions.assertEquals("2", trelloListDtoList.get(1).getId());
        Assertions.assertEquals("3", trelloListDtoList.get(2).getId());
    }

    @Test
    void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard(
                "CardName",
                "CardDescription",
                "CardPosition",
                "1"
        );

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assertions.assertEquals("CardName", trelloCardDto.getName());
        Assertions.assertEquals("CardDescription", trelloCardDto.getDescription());
        Assertions.assertEquals("CardPosition", trelloCardDto.getPos());
        Assertions.assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "CardName",
                "CardDescription",
                "CardPosition",
                "1"
        );

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assertions.assertEquals("CardName", trelloCard.getName());
        Assertions.assertEquals("CardDescription", trelloCard.getDescription());
        Assertions.assertEquals("CardPosition", trelloCard.getPos());
        Assertions.assertEquals("1", trelloCard.getListId());
    }
}
