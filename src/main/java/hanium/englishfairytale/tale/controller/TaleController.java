package hanium.englishfairytale.tale.controller;

import hanium.englishfairytale.tale.dto.TaleDTO;
import hanium.englishfairytale.tale.dto.TaleRequestDTO;
import hanium.englishfairytale.tale.service.TaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fairy-tale")
@Log4j2
@RequiredArgsConstructor
public class TaleController {
    private final TaleService taleService;

    @PostMapping("")
    public ResponseEntity<TaleDTO> register(@RequestBody TaleRequestDTO taleRequestDTO){
        log.info(taleRequestDTO);
        return new ResponseEntity<>(taleService.insert(taleRequestDTO), HttpStatus.OK);
    }
}
