package hanium.englishfairytale.report.service;

import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.report.dto.PostReportQueryDto;
import hanium.englishfairytale.tale.domain.Tale;

public interface PostReportService {
    void report(PostReportQueryDto postReportQueryDto);
    void createPostReport(Member member, Tale tale);
    Member verifyAndGetMember(Long member_id);
    Tale verifyAndGetTale(Long tale_id);
    void verifyExistedMember(Member member, Tale tale);
}
