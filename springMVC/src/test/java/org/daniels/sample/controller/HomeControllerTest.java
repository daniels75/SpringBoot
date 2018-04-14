package org.daniels.sample.controller;

import org.daniels.sample.SpringMvcSampleApplication;
import org.daniels.sample.profile.UserProfileSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;


/**
 * Created by daniels on 14.04.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringMvcSampleApplication.class)
@WebAppConfiguration
public class HomeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void shouldRedirectToProfile() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/profile"));
    }

    @Test
    public void testRedirectToTastes() throws Exception {
        MockHttpSession session = new MockHttpSession();
        UserProfileSession sessionBean = new UserProfileSession();
        sessionBean.setTastes(Arrays.asList("spring", "groovy"));
        session.setAttribute("scopedTarget.userProfileSession", sessionBean);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/").session(session))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/search/mixed;keywords=spring,groovy"));


    }
}


