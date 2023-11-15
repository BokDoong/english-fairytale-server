package hanium.englishfairytale.report.controller;

import hanium.englishfairytale.report.dto.PostReportQueryDto;
import hanium.englishfairytale.report.dto.UserReportQueryDto;
import hanium.englishfairytale.report.service.PostReportService;
import hanium.englishfairytale.report.service.UserReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/community/report")
@RequiredArgsConstructor
public class ReportController {
    private final PostReportService postReportService;
    private final UserReportService userReportService;

    @PostMapping("post")
    public void postReport(@Validated @RequestPart PostReportQueryDto postReportQueryDto) {
        postReportService.report(postReportQueryDto);
    }
    @PostMapping("user")
    public void userReport(@Validated @RequestPart UserReportQueryDto userReportQueryDto){
        userReportService.report(userReportQueryDto);
    }
}
