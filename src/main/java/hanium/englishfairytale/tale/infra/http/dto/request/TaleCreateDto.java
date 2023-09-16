package hanium.englishfairytale.tale.infra.http.dto.request;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class
TaleCreateDto {
    private String model;
    private List<String> keywords;
}