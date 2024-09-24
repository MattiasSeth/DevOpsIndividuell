package com.example.devopsindividuell.service;

import com.example.devopsindividuell.dto.NackademinDTO;
import com.example.devopsindividuell.model.Nackademin;
import com.example.devopsindividuell.repository.NackademinRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class NackademinServiceTest {

    @Mock
    private NackademinRepo nackademinRepo;

    @InjectMocks
    private NackademinService nackademinService;

    @Autowired
    private NackademinRepo nackademinRepoH2;

    @Autowired
    private NackademinService nackademinServiceH2;

    @Test
    void h2Test(){
        Nackademin n1 = new Nackademin(1L,"Java","123");
        Nackademin n2 = new Nackademin(2L,"Python","321");

        nackademinRepoH2.save(n1);
        nackademinRepoH2.save(n2);
        List<NackademinDTO> allCourses = nackademinServiceH2.getAllCourses();

        assertEquals(2,allCourses.size());
        assertEquals("Java",allCourses.get(0).getCourseName());
        assertEquals("321",allCourses.get(1).getSolution());

        assertNotEquals(3L,allCourses.get(1).getId());
        assertNotEquals("Hej123",allCourses.get(0).getSolution());
        assertNotEquals(1L,allCourses.get(0).getCourseName());

    }

    @BeforeEach
    public void init(){
        Nackademin n1 = new Nackademin(1L,"Java","111");
        Nackademin n2 = new Nackademin(2L,"Python","222");
        Nackademin n3 = new Nackademin(3L,"C","333");

        when(nackademinRepo.findById(1L)).thenReturn(Optional.of(n1));
        when(nackademinRepo.findById(2L)).thenReturn(Optional.of(n2));
        when(nackademinRepo.findById(3L)).thenReturn(Optional.of(n3));
        when(nackademinRepo.findAll()).thenReturn(Arrays.asList(n1,n2,n3));
    }

    Nackademin nackademin = new Nackademin(1L,"Java","111");

    @Test
    void getAllCourses() {
        List<NackademinDTO> resultList = nackademinService.getAllCourses();
        assertEquals(3, resultList.size());

        assertEquals("Java", resultList.get(0).getCourseName());
        assertEquals("Python", resultList.get(1).getCourseName());
        assertEquals("C", resultList.get(2).getCourseName());

        assertNotEquals(2L,resultList.get(0).getId());
        assertNotEquals(2L,resultList.get(2).getId());
        assertNotEquals(1L,resultList.get(1).getId());
    }

    @Test
    void convertToDto() {
        NackademinService service = new NackademinService(nackademinRepo);
        NackademinDTO converted = service.convertToDto(nackademin);

        assertEquals(converted.getId(), nackademin.getId());
        assertEquals(converted.getSolution(), nackademin.getSolution());
        assertEquals(converted.getCourseName(), nackademin.getCourseName());
    }

    @Test
    void convertToDtoNotEquals(){
        NackademinService service = new NackademinService(nackademinRepo);
        NackademinDTO converted = service.convertToDto(nackademin);

        assertNotEquals(converted.getId(),2L);
        assertNotEquals(converted.getCourseName(),"Python");
        assertNotEquals(converted.getSolution(),"Not 111");
    }
}