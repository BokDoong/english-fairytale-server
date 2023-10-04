package hanium.englishfairytale.tale.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Getter
public class Image {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="taleImage_id")
    private TaleImage taleImage;

    void putTaleImage(TaleImage taleImage) {
        this.taleImage = taleImage;
    }
    public String getUrl() {
        return taleImage.getImageUrl();
    }
}
