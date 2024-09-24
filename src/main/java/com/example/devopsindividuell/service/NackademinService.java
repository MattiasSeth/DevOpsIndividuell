package com.example.devopsindividuell.service;

import com.example.devopsindividuell.dto.NackademinDTO;
import com.example.devopsindividuell.model.Nackademin;
import com.example.devopsindividuell.repository.NackademinRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@Service
public class NackademinService {

    private final NackademinRepo nackademinRepo;

    public NackademinService(NackademinRepo nackademinRepo){
        this.nackademinRepo = nackademinRepo;
    }

    public List<NackademinDTO> getAllCourses(){
        return nackademinRepo.findAll().stream().map(this::convertToDto).toList();
    }

    public NackademinDTO convertToDto(Nackademin nackademin){
        String tempCourseName = nackademin.getCourseName();

        if (tempCourseName.equals("Designm”nster, analys och design")){
            tempCourseName = "Designmönster, analys och design";
        } else if (tempCourseName.equals("IT-S„kerhet")) {
            tempCourseName = "IT-Säkerhet";
        }
        return NackademinDTO.builder()
                .id(nackademin.getId())
                .courseName(tempCourseName)
                .solution(nackademin.getSolution())
                .build();
    }
}
