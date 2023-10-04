package hanium.englishfairytale.tale.domain;

import hanium.englishfairytale.tale.application.dto.TaleImageInfo;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TaleImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NotNull
    @Column(name = "original_name")
    private String originalName;
    @NotNull
    @Column(name = "stored_name")
    private String storedName;
    @NotNull
    @Column(name = "image_url")
    private String imageUrl;

    public TaleImage(TaleImageInfo taleImageInfo) {
        this.originalName = taleImageInfo.getOriginalFileName();
        this.storedName = taleImageInfo.getStoredName();
        this.imageUrl = taleImageInfo.getImageUrl();
    }
}
