package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("boards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.stream()
                .filter(id -> Objects.nonNull(id.getId()))
                .filter(name -> Objects.nonNull(name.getName()))
                .filter(boardName -> boardName.getName().indexOf("Kodilla") != -1)
                .forEach(trelloBoardDto -> {
                    System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
                });
    }
}
