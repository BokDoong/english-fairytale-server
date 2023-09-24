package hanium.englishfairytale.tale.infra.http.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class TaleCreateDto {
    @NotNull
    private Long memberId;
    @NotBlank
    private String model;
    @NotEmpty
    private List<String> keywords;

    public TaleCreateDto(Long memberId, String model, List<String> keywords) {
        this.memberId = memberId;
        this.model = model;
        this.keywords = keywords;
    }
}