package org.example.acceptresumeandjd.controller;

import org.example.acceptresumeandjd.Repositories.JDRepository;
import org.example.acceptresumeandjd.Repositories.ResumeRepository;
import org.example.acceptresumeandjd.models.JobDescription;
import org.example.acceptresumeandjd.models.Resume;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")
public class ResumeController {

    private final ResumeRepository resumeRepository;
    private final JDRepository jdRepository;

    public ResumeController(ResumeRepository resumeRepository, JDRepository jdRepository) {
        this.resumeRepository = resumeRepository;
        this.jdRepository = jdRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file,@RequestParam Long jobId) throws IOException {
        Optional<JobDescription> jd = jdRepository.findById(jobId);


            try{
                Resume resume = new  Resume();
                resume.setFileType(file.getContentType());
                resume.setFileName(file.getOriginalFilename());
                resume.setFileData(file.getBytes());
                resume.setJobId(jobId);


                resumeRepository.save(resume);
                System.out.println("completed");
                return ResponseEntity.ok().body("Your Resume has been uploaded successfully");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }




    }

