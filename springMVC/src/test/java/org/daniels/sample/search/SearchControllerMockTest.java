package org.daniels.sample.search;

import org.daniels.sample.SpringMvcSampleApplication;
import org.daniels.sample.controller.SearchController;
import org.daniels.sample.org.daniels.sample.search.LightTweet;
import org.daniels.sample.service.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Test for search tweets via mocked services
 * Created by daniels on 14.04.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringMvcSampleApplication.class)
@WebAppConfiguration
public class SearchControllerMockTest {

    @Mock
    private SearchService searchService;
    @InjectMocks
    private SearchController searchController;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(searchController)
                .setRemoveSemicolonContent(false)
                .build();
    }

    @Test
    public void shouldSearch() throws Exception {
        final String tweetInfo = "AnyContent of tweet";

        when(searchService.search(anyString(), anyListOf(String.class)))
         .thenReturn(Arrays.asList(new LightTweet(tweetInfo)));

        this.mockMvc.perform(get("/search/mixed;keywords=spring"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("resultPage"))
                .andExpect(model().attribute("tweets", everyItem(
                        hasProperty("text", is(tweetInfo))
                )));
        verify(searchService, times(1)).search(anyString(), anyListOf(String.class));
    }
}
