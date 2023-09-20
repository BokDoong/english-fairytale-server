package hanium.englishfairytale.tale.domain.factory.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CreateTaleDto implements Serializable {
    private String role;
    private String content;

    @Builder
    public CreateTaleDto(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
