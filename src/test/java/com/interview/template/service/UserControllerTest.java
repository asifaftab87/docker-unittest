package com.interview.template.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.template.controller.UserController;
import com.interview.template.exceptions.UserNotFoundException;
import com.interview.template.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @InjectMocks
    UserController userController;

    @MockBean
    UserService userService;

    UserEntity userEntity;

    @BeforeEach
    void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);

        userEntity = new UserEntity(90L, "asfiya", "asfiya@mail.com", "password");
    }

    @Test
    final void testGetUserById() throws UserNotFoundException {

        when(userService.getUser(1L)).thenReturn(userEntity);

        UserEntity responseEntity = userController.getUser(1l);

        assertNotNull(responseEntity);

        assertEquals(userEntity.getId(), responseEntity.getId());
    }

    @Test
    final void testGetUserByUsername() throws UserNotFoundException {

        when(userService.findByUsername("asfiya")).thenReturn(userEntity);

        List<UserEntity> responseEntity = userController.findByUsernameContaining("asfiya");

        assertNotNull(responseEntity);
    }

    @Test
    final void testAddUser() throws Exception {

        when(userService.create(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        mvc.perform(post("/api/v1/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(userEntity)))
                .andExpect(status().isOk());

    }

    public static String toJson(final Object obj) {

        try {
            String json = new ObjectMapper().writeValueAsString(obj);
            return json;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
