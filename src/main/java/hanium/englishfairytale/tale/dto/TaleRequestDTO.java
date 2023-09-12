package hanium.englishfairytale.tale.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaleRequestDTO {
    private String model;
    private List<String> keyword;
}
