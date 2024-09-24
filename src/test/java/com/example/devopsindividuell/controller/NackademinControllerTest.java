package com.example.devopsindividuell.controller;

import com.example.devopsindividuell.repository.NackademinRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class NackademinControllerTest {

    @Autowired
    private NackademinController controller;

    @MockBean
    private NackademinRepo mockRepo;

    @Test
    public void contextLoads(){
        assertThat(controller).isNotNull();
    }

}