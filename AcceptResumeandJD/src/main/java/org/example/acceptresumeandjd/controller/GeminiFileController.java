package org.example.acceptresumeandjd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.acceptresumeandjd.Repositories.JDRepository;
import org.example.acceptresumeandjd.Repositories.ResumeRepository;
import org.example.acceptresumeandjd.Services.FileToTextExtractor;
import org.example.acceptresumeandjd.dto.JDRequest;
import org.example.acceptresumeandjd.models.JobDescription;
import org.example.acceptresumeandjd.models.Resume;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jd")
@CrossOrigin("*")
public class GeminiFileController {

    private final JDRepository jdRepository;

    private final String GEMINI_URL="https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=<Gemini Api Key>";

    private final RestTemplate restTemplate;

    private final FileToTextExtractor fileToTextExtractor;
    private final ResumeRepository resumeRepository;


    public GeminiFileController(JDRepository jdRepository, ResumeRepository resumeRepository) {
        this.jdRepository = jdRepository;
        this.fileToTextExtractor = new  FileToTextExtractor();

        this.restTemplate = new  RestTemplate();
        this.resumeRepository = resumeRepository;
    }

    public StringBuilder fetchJD(Long jobId) throws JsonProcessingException {

        StringBuilder jd=new StringBuilder();
        Optional<JobDescription> jobDescription=jdRepository.findByJobId(jobId);
        jd.append("JobTitle:").append(jobDescription.get().getJobTitle());
        jd.append("\nJobLocation:").append(jobDescription.get().getJobLocation());
        jd.append("\nRequired Experience:").append(jobDescription.get().getExperience());
        jd.append("\nRequired Skills:").append(jobDescription.get().getSkills());
        jd.append("\nSummary:").append(jobDescription.get().getSummary());
        return jd;
    }
    @PostMapping("/uploadall")
    public ResponseEntity<String> uploadresumesandjd (@RequestBody JDRequest jdRequest) throws JsonProcessingException {

        Long jobId=jdRequest.getJobId();
        String filters=jdRequest.getFilters();
        StringBuilder jobDescription = fetchJD(jobId);


           List<Resume> relatedResumes=resumeRepository.findByJobId(jobId);

           StringBuilder resumes=new StringBuilder();
           for(Resume resume:relatedResumes){
               String text=fileToTextExtractor.extractText(resume.getFileData());
               resumes.append("\n|-----Resume "+resume.getId()+"-----|\n").append(text);
           }

           String prompt="Job Description: "+ jobDescription.toString() +
                   "\nFilters: "+filters+
                   "\nResumes: "+resumes+
                   "\n\nPlease analyze ,if description and filter doesn't match with resume then eliminate otherwise sort resumes based on job description, filters, give me resume id and name of candidate in array in form of json string.";



        HttpHeaders headers = new HttpHeaders();
           headers.setContentType(MediaType.APPLICATION_JSON);

           String requestBody = """
        {
          "contents": [{
            "parts": [{
              "text": "%s"
            }]
          }]
        }
        """.formatted(prompt);

           HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);

           ResponseEntity<String> response = restTemplate.exchange(GEMINI_URL, HttpMethod.POST, entity, String.class);

           ObjectMapper mapper = new ObjectMapper();
          JsonNode root=mapper.readTree(response.getBody());
          String output=mapper.writeValueAsString(root);

        System.out.println(output);

       return new ResponseEntity<>(response.getBody(), HttpStatus.OK);



    }



}
