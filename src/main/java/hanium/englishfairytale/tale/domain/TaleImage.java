package hanium.englishfairytale.tale.domain;

import hanium.englishfairytale.common.files.ImageInfo;
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

    public TaleImage(ImageInfo imageInfo) {
        this.originalName = imageInfo.getOriginalFileName();
        this.storedName = imageInfo.getStoredName();
        this.imageUrl = imageInfo.getImageUrl();
    }
}
