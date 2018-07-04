package com.imooc.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

/**
 * @author: JefferyJu
 * @date: 2018/7/4
 */
@AutoConfigureMockMvc
public class GirlControllerTest extends GirlApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void girlList() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.get("/girls"))
               .andExpect(MockMvcResultMatchers.status().isOk())
       .andExpect(MockMvcResultMatchers.content().string("abc"));
    }
}