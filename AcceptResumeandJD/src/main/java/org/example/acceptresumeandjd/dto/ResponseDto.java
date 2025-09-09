package org.example.acceptresumeandjd.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    Long resumeId;

    String candidateName;

    @Override
    public String toString() {
        return "ResponseDto{" +
                "resumeId=" + resumeId +
                ", candidateName='" + candidateName + '\'' +
                '}';
    }
}
