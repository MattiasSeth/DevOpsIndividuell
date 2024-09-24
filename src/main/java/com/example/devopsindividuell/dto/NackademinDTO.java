package com.example.devopsindividuell.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NackademinDTO {

    private long id;
    private String courseName;
    private String solution;
}
