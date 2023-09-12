package hanium.englishfairytale.tale.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaleDTO {
    private Long id;
    private String title;
    private String content;
    private String kor;
    private List<String> keyword;
}
