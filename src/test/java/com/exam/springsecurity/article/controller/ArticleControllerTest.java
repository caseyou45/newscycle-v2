package com.exam.springsecurity.article.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Test
    void getArticlesByCategory() {
        assertDoesNotThrow(() -> mockMvc.perform(get("/api/article/category?category=general"))
                .andExpect(status().isOk()));
    }

    @Test
    void getOneArticleByID() {
        assertDoesNotThrow(() -> mockMvc.perform(get("/api/article/id?id=1"))
                .andExpect(status().isOk()));
    }
}