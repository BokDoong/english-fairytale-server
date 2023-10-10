package hanium.englishfairytale.tale.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="taleImage_id")
    private TaleImage taleImage;

    @Enumerated(EnumType.STRING)
    private ImageStatus imageStatus;

    public Image(ImageStatus imageStatus) {
        this.imageStatus = imageStatus;
    }

    void putTaleImage(TaleImage taleImage) {
        this.taleImage = taleImage;
    }

    void updateImageStatus() {
        this.imageStatus = ImageStatus.CUSTOMIZED;
    }

    public String getUrl() {
        return taleImage.getImageUrl();
    }
}
