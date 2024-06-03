package com.TalentPool.TalentPool.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    @NotEmpty
    private String name;
    @Column(unique = true)
    private String rg;
    @NotNull
    private String email;
    @ManyToOne
    private Job job;

}
