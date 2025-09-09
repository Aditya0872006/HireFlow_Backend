package org.example.acceptresumeandjd.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class createJDDto {
    private Long jobId;


    private String jobTitle;


    private String companyName;


    private String skills;

    private String experience;

    private String education;


    private String jobLocation;

    private String summary;

    private String jobDescription;
    private String responsibilities;
}
