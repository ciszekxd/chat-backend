package com.anon.chat.user;

import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class ChatUserControllerTest {

    @Autowired private MockMvc mockMvc;

    private final String registerUserPath = "/user/register";

    @Test
    void shouldHaveReturnStatus2XX() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(registerUserPath).content(""))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void shouldReturnUserUuid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(registerUserPath).content(""))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string(MatchesPattern.matchesPattern(".{8}-.{4}-.{4}-.{4}-.{12}")));
    }
}