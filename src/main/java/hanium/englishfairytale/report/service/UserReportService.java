package hanium.englishfairytale.report.service;

import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.report.dto.UserReportQueryDto;

public interface UserReportService {
    void report(UserReportQueryDto userReportQueryDto);
    Member getMember(Long member_id);
}
