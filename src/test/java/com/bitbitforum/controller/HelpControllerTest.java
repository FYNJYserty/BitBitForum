package com.bitbitforum.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(HelpController.class)
public class HelpControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void aboutUsTest() throws Exception {
        mockMvc.perform(get("/aboutus"))
                .andExpect(status().isOk())
                .andExpect(view().name("other/about"))
                .andExpect(content().string(containsString("")));
    }

    @Test
    @WithMockUser
    public void helpTest() throws Exception {
        mockMvc.perform(get("/help"))
                .andExpect(status().isOk())
                .andExpect(view().name("other/help"))
                .andExpect(content().string(containsString("""
                        If you have any problems or can't figure something out, describe your issue and send it to us""")));
    }
}
