package hanium.englishfairytale.tale.service;

import hanium.englishfairytale.tale.dto.TaleDTO;
import hanium.englishfairytale.tale.dto.TaleRequestDTO;
import hanium.englishfairytale.tale.entity.Tale;
import hanium.englishfairytale.tale.repository.TaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TaleServiceImpl implements TaleService{
    @Autowired
    private ChatGptService chatGptService;
    @Autowired
    private TaleRepository repository;
    @Override
    public TaleDTO insert(TaleRequestDTO taleRequestDTO) {
        String model = taleRequestDTO.getModel(); // "gpt-3.5-turbo"
        List<String> keyword = taleRequestDTO.getKeyword(); // ["key", "word"]

        List<String> content = chatGptService.post(model, keyword);
        TaleDTO taleDTO = TaleDTO.builder()
                .content(content.get(0))
                .kor(content.get(1))
                .title(content.get(2))
                .keyword(keyword)
                .build();
        Tale tale = dtoToEntity(taleDTO);
        repository.save(tale);

        return entityToDto(tale);
    }
}
