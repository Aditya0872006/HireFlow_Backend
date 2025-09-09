package org.example.acceptresumeandjd.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobDescription {
    @Id
    @Column(nullable = false)
    private Long jobId;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String companyName;


    private String skills;

    private String experience;




    @Column(nullable = false)
    private String jobLocation;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String summary;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String responsibilities;


}
