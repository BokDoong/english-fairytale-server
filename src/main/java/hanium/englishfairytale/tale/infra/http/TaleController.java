package hanium.englishfairytale.tale.infra.http;

import hanium.englishfairytale.tale.application.TaleQueryService;
import hanium.englishfairytale.tale.application.dto.*;
import hanium.englishfairytale.tale.application.TaleCommandService;
import hanium.englishfairytale.tale.infra.http.dto.TaleCreateDto;
import hanium.englishfairytale.tale.infra.http.dto.TaleDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fairytale")
@RequiredArgsConstructor
public class TaleController {

    private final TaleQueryService taleQueryService;
    private final TaleCommandService taleCommandService;
    private final TaleDtoConverter converter;

    @PostMapping("create")
    public TaleCreateResponse create(@Validated @RequestPart TaleCreateDto taleCreateDto,
                                                     @RequestPart(required = false) MultipartFile image) {
        return taleCommandService.create(toCreateCommand(taleCreateDto, image));
    }

    @GetMapping("/recent")
    public List<TalesInfo> findRecentTales(@RequestParam Long memberId) {
        return taleQueryService.findRecentTales(memberId);
    }

    @GetMapping("/all")
    public List<TalesInfo> findAllTales(@RequestParam Long memberId, @RequestParam int offset) {
        return taleQueryService.findAllTales(memberId, offset);
    }

    @GetMapping("/{taleId}/detail")
    public TaleDetailInfo findDetailTale(@PathVariable Long taleId) {
        return taleQueryService.findDetailTale(taleId);
    }

    @DeleteMapping("/{taleId}")
    public void delete(@PathVariable Long taleId) {
        taleCommandService.deleteTale(taleId);
    }

    @PutMapping("/{taleId}/image")
    public void updateImage(@PathVariable Long taleId, @RequestPart MultipartFile image) {
        taleCommandService.updateTaleImage(toUpdateCommand(taleId, image));
    }

    @DeleteMapping("/{taleId}/image")
    public void deleteImage(@PathVariable Long taleId, @RequestParam String imageStatus) {
        taleCommandService.deleteTaleImage(taleId, imageStatus);
    }

    private TaleCreateCommand toCreateCommand(TaleCreateDto taleCreateDto, MultipartFile image) {
        return converter.toCreateCommand(taleCreateDto, image);
    }

    private TaleUpdateCommand toUpdateCommand(Long taleId, MultipartFile image) {
        return converter.toUpdateCommand(taleId, image);
    }
}
