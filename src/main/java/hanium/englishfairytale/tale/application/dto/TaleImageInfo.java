package hanium.englishfairytale.tale.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaleImageInfo {
    String originalFileName;
    String storedName;
    String imageUrl;
}
