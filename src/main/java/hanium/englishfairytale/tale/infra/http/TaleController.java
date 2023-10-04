package hanium.englishfairytale.tale.infra.http;

import hanium.englishfairytale.tale.application.TaleQueryService;
import hanium.englishfairytale.tale.application.dto.request.TaleCreateCommand;
import hanium.englishfairytale.tale.application.dto.response.TaleDetailInfo;
import hanium.englishfairytale.tale.application.dto.response.TalesInfo;
import hanium.englishfairytale.tale.application.dto.response.TaleCreateResponse;
import hanium.englishfairytale.tale.application.TaleCommandService;
import hanium.englishfairytale.tale.infra.http.dto.TaleCreateDto;
import hanium.englishfairytale.tale.infra.http.dto.TaleDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fairytale")
@RequiredArgsConstructor
public class TaleController {

    private final TaleQueryService taleQueryService;
    private final TaleCommandService taleService;
    private final TaleDtoConverter converter;

    @PostMapping("create")
    public ResponseEntity<TaleCreateResponse> create(@Validated @RequestPart TaleCreateDto taleCreateDto,
                                                     @RequestPart(required = false) MultipartFile image) {
        return new ResponseEntity<>(taleService.create(toCreateCommand(taleCreateDto, image)), HttpStatus.OK);
    }

    @DeleteMapping("/{taleId}")
    public void delete(@PathVariable Long taleId) {
        taleService.delete(taleId);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<TalesInfo>> findRecentTales(@RequestParam Long memberId) {
        return new ResponseEntity<>(taleQueryService.findRecentTales(memberId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TalesInfo>> findAllTales(@RequestParam Long memberId, @RequestParam int offset) {
        return new ResponseEntity<>(taleQueryService.findAllTales(memberId, offset), HttpStatus.OK);
    }

    @GetMapping("/{taleId}/detail")
    public ResponseEntity<TaleDetailInfo> findDetailTale(@PathVariable Long taleId) {
        return new ResponseEntity<>(taleQueryService.findDetailTale(taleId), HttpStatus.OK);
    }

    private TaleCreateCommand toCreateCommand(TaleCreateDto taleCreateDto, MultipartFile image) {
        return converter.toCommand(taleCreateDto, image);
    }
}
