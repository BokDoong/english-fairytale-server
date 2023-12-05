package hanium.englishfairytale.report.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostReportQueryDto {
    @NotNull
    private Long taleId;
    @NotNull
    private Long memberId;

    public PostReportQueryDto(Long taleId, Long memberId) {
        this.taleId = taleId;
        this.memberId = memberId;
    }
}