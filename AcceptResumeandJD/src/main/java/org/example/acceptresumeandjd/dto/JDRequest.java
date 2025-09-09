package org.example.acceptresumeandjd.dto;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JDRequest {

    private Long jobId;

    private String filters;
}
