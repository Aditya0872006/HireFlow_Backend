package org.example.acceptresumeandjd.Repositories;

import org.example.acceptresumeandjd.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    public List<Resume> findByJobId(Long jobId);
}
