package hanium.englishfairytale.tale.domain.factory;

import hanium.englishfairytale.tale.domain.ImageStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreatedTale {
    String title;
    String engTale;
    String korTale;
}
