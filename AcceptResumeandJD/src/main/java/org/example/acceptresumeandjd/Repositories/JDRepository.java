package org.example.acceptresumeandjd.Repositories;

import org.example.acceptresumeandjd.models.JobDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JDRepository  extends JpaRepository<JobDescription, Long> {



    Optional<JobDescription> findByJobId(Long jobId);
  List<JobDescription> findAll();
}
