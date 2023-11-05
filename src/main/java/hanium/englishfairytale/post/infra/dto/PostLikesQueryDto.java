package hanium.englishfairytale.post.infra.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PostLikesQueryDto {

    @JsonIgnore
    private Long taleId;
    private Long memberId;

    public PostLikesQueryDto(Long taleId, Long memberId) {
        this.taleId = taleId;
        this.memberId = memberId;
    }
}
