package com.crud.tasks.mapper;


import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloMapperTest {
    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void testMapToBoards() {
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("Board", "1", trelloListDtos);
        trelloBoardDtos.add(trelloBoardDto);

        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtos);

        Assert.assertEquals(trelloBoardList.get(0).getName(), "Board");
        Assert.assertEquals(trelloBoardList.get(0).getId(), "1");

        trelloBoardList.remove(0);
    }

    @Test
    public void testMapToBoardsDto() {
        List <TrelloBoard> trelloBoards = new ArrayList<>();
        List <TrelloList> trelloLists = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("1", "Board", trelloLists);
        trelloBoards.add(0, trelloBoard);

        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        Assert.assertEquals(trelloBoardDtos.size(), 1);
        Assert.assertEquals(trelloBoardDtos.get(0).getLists(), trelloLists);

        trelloBoards.remove(0);
    }

    @Test
    public void testMapToList() {
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1", "List", false);
        trelloListDtos.add(trelloListDto);

        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        Assert.assertEquals(trelloLists.get(0).getId(), "1");
        Assert.assertEquals(trelloLists.get(0).getName(), "List");

        trelloListDtos.remove(0);
    }

    @Test
    public void testMapToListDto() {
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList = new TrelloList("1", "List", false);
        trelloLists.add(0,trelloList);

        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        Assert.assertEquals(trelloListDtos.get(0).getId(), "1");
        Assert.assertEquals(trelloListDtos.get(0).getName(), "List");

        trelloLists.remove(0);
    }

    @Test
    public void testMapToCardDto() {
        TrelloCard trelloCard = new TrelloCard("Card", "Card for test", "pos", "1");

        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        Assert.assertTrue(trelloCardDto.getDescription().equals("Card for test"));
        Assert.assertTrue(trelloCardDto.getName().equals("Card"));
        Assert.assertTrue(trelloCardDto.getPos().equals("pos"));
    }

    @Test
    public void testMapToCard() {
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card", "Card for test", "pos", "1");

        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        Assert.assertEquals(trelloCard.getName(), "Card");
        Assert.assertEquals(trelloCard.getListId(), "1");
        Assert.assertEquals(trelloCardDto.getPos(), "pos");
        Assert.assertEquals(trelloCardDto.getListId(), "1");
    }
}
