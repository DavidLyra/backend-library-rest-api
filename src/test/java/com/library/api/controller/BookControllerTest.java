package com.library.api.controller;

import com.library.api.config.security.JWTUtil;
import com.library.api.model.User;
import com.library.api.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Environment env;

    @Autowired
    private JWTUtil jwtUtil;

    private String accessToken;

    @BeforeEach
    public void setup() {
        User user = new User();
        user.setName("admin");
        user.setEmail(env.getProperty("sa@email.com"));
        user.setPassword(env.getProperty("123456"));
        accessToken = jwtUtil.generateToken(user);
    }

    @Test
    @DisplayName("GET /api/v1/books/1 - Found")
    void testGetBookByIdFound() throws Exception {
    String result = mockMvc.perform(get("/api/v1/books/{id}",1L)
              .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
             .andExpect(status().isOk())
              .andReturn()
             .getResponse()
             .getContentAsString();

      System.out.println(result);
    }

    @Test
    @DisplayName("GET /api/v1/books/1 - Not Found")
    public void testGetBookByIdNotFound() throws Exception {
        doReturn(Optional.empty()).when(bookService).getBookFamilyById(1L);
        mockMvc.perform(get("/api/v1/books/{id}",1L)).andExpect(status().isNotFound());
    }
}
