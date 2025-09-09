package org.example.acceptresumeandjd.controller;

import org.example.acceptresumeandjd.Repositories.JDRepository;
import org.example.acceptresumeandjd.dto.createJDDto;
import org.example.acceptresumeandjd.models.JobDescription;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/jd")
@CrossOrigin("*")
public class JDController {

    private final JDRepository jdRepository;

    public JDController(JDRepository jdRepository) {
        this.jdRepository = jdRepository;
    }

    @PostMapping("/postjob")
    public ResponseEntity<String> createJob(@RequestBody createJDDto createJDDto) {
        if(createJDDto.getJobLocation()==null || createJDDto.getCompanyName()==null ||  createJDDto.getJobId()==null || createJDDto.getJobTitle()==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
         JobDescription jobDescription=JobDescription.builder()
                 .jobId(createJDDto.getJobId())
                 .responsibilities(createJDDto.getResponsibilities())
                 .skills(createJDDto.getSkills())
                 .companyName(createJDDto.getCompanyName())
                 .summary(createJDDto.getSummary())
                 .experience(createJDDto.getExperience())
                 .jobLocation(createJDDto.getJobLocation())
                 .jobTitle(createJDDto.getJobTitle())
                 .build();

         jdRepository.save(jobDescription);

         return ResponseEntity.ok("Job posted successfully");



    }
    @GetMapping("/parse/{jobId}")
    public ResponseEntity<JobDescription> fetchJobById(@PathVariable Long jobId) {
        Optional<JobDescription> jobDescription=jdRepository.findById(jobId);
        return new  ResponseEntity<>(jobDescription.get(), HttpStatus.OK);
    }
}
