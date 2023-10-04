package hanium.englishfairytale.tale.infra.http;

import hanium.englishfairytale.tale.application.TaleQueryService;
import hanium.englishfairytale.tale.application.dto.*;
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
                                                     @RequestPart MultipartFile image) {
        return new ResponseEntity<>(taleService.create(toCreateCommand(taleCreateDto, image)), HttpStatus.OK);
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

    @PutMapping("/{taleId}")
    public void update(@PathVariable Long taleId, @RequestPart MultipartFile image) {
        taleService.update(toUpdateCommand(taleId, image));
    }

    @DeleteMapping("/{taleId}")
    public void delete(@PathVariable Long taleId) {
        taleService.delete(taleId);
    }

    private TaleCreateCommand toCreateCommand(TaleCreateDto taleCreateDto, MultipartFile image) {
        return converter.toCreateCommand(taleCreateDto, image);
    }

    private TaleUpdateCommand toUpdateCommand(Long taleId, MultipartFile image) {
        return converter.toUpdateCommand(taleId, image);
    }
}
