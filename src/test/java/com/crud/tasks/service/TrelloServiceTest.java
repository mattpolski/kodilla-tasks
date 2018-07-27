package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void shouldGetEmptyList(){
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);

        List<TrelloBoardDto> emptyList = trelloClient.getTrelloBoards();

        Assert.assertEquals(0, emptyList.size());
    }

    @Test
    public void shouldCreateNewCard() {
        TrelloDto trelloDto = new TrelloDto(23, 23);
        TrelloAttachmentByType trelloAttachmentByType = new TrelloAttachmentByType(trelloDto);
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "New Card", "short Uri");
        TrelloCardDto trelloCardDto = new TrelloCardDto("New Card", "Card for tests", "pos", "1");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        CreatedTrelloCardDto testCard = trelloService.createTrelloCard(trelloCardDto);

        Assert.assertEquals("New Card", testCard.getName());
        Assert.assertEquals("short Uri", createdTrelloCardDto.getShortUrl());
    }
}
