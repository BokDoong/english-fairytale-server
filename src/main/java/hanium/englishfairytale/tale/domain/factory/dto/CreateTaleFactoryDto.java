package hanium.englishfairytale.tale.domain.factory.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CreateTaleFactoryDto implements Serializable {
    private String role;
    private String content;

    @Builder
    public CreateTaleFactoryDto(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
