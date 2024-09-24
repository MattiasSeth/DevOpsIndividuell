package com.example.devopsindividuell.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression", "JpaDataSourceORMInspection"})
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="nackademin")
public class Nackademin {

    @Id
    @GeneratedValue
    private long id;

    @Column(name ="coursename")
    private String courseName;
    @Column(name = "solution")
    private String solution;

}
