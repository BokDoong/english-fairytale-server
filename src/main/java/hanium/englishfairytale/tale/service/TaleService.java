package hanium.englishfairytale.tale.service;

import hanium.englishfairytale.tale.dto.TaleDTO;
import hanium.englishfairytale.tale.dto.TaleRequestDTO;
import hanium.englishfairytale.tale.entity.Tale;

public interface TaleService {
    TaleDTO insert(TaleRequestDTO taleRequestDTO);

    default Tale dtoToEntity(TaleDTO taleDTO){
        return Tale.builder()
                .id(taleDTO.getId())
                .title(taleDTO.getTitle())
                .content(taleDTO.getContent())
                .kor(taleDTO.getKor())
                //.keyword(taleDTO.getKeyword())
                .build();
    }

    default TaleDTO entityToDto(Tale tale){
        return TaleDTO.builder()
                .id(tale.getId())
                .title(tale.getTitle())
                .content(tale.getContent())
                .kor(tale.getKor())
                //.keyword(tale.getKeyword())
                .build();
    }
}
