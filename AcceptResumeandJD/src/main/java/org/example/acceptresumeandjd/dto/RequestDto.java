package org.example.acceptresumeandjd.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private Long jobId;
    private StringBuilder jobDescription ;
    private String filters;
}
