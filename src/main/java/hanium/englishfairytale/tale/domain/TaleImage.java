package hanium.englishfairytale.tale.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
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

    public static TaleImage createTaleImage(Tale tale, String originalName, String storedName, String imageUrl) {
        TaleImage taleImage = setTaleImage(originalName, storedName, imageUrl);
        tale.putImage(taleImage);
        return taleImage;
    }

    private static TaleImage setTaleImage(String originalName, String storedName, String imageUrl) {
        return TaleImage.builder()
                .originalName(originalName)
                .storedName(storedName)
                .imageUrl(imageUrl)
                .build();
    }
}
