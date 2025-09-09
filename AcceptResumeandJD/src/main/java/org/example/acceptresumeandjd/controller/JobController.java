package org.example.acceptresumeandjd.controller;

import org.example.acceptresumeandjd.Repositories.JDRepository;
import org.example.acceptresumeandjd.Repositories.ResumeRepository;
import org.example.acceptresumeandjd.dto.createJDDto;
import org.example.acceptresumeandjd.models.JobDescription;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin("*")
public class JobController {
    private final JDRepository jdRepository;

    public JobController(JDRepository jdRepository) {
        this.jdRepository = jdRepository;
    }
    @GetMapping("/getall")
    public ResponseEntity<List<JobDescription>> availableJobs() {
        return new ResponseEntity<>(jdRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteByJobId(@RequestParam Long jobId) {
        jdRepository.deleteById(jobId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
