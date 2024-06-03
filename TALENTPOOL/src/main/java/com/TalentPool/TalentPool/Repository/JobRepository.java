package com.TalentPool.TalentPool.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.TalentPool.TalentPool.Model.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

    Job findByCode(long code);

    List<Job> findByName(String name);

}
