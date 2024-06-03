package com.TalentPool.TalentPool.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.TalentPool.TalentPool.Model.Candidate;
import com.TalentPool.TalentPool.Model.Job;


public interface  CandidateRepository extends CrudRepository<Candidate, Long> {

    Candidate findByRg(String rg);
    Candidate findById(long id);
    List<Candidate> findByName(String name);
    List<Candidate> findByJob(Job job);
    
}
