package hanium.englishfairytale.report.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class UserReportQueryDto {
    @NotNull
    private Long reportedId;
    @NotNull
    private Long reporterId;

    public UserReportQueryDto(Long reportedId, Long reporterId) {
        this.reportedId = reportedId;
        this.reporterId = reporterId;
    }
}
