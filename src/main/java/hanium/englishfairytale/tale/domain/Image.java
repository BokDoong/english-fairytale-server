package hanium.englishfairytale.tale.domain;

import lombok.Getter;

import javax.persistence.*;

@Embeddable
@Getter
public class Image {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="taleImage_id")
    private TaleImage taleImage;

    void putTaleImage(TaleImage newtaleImage) {
        taleImage = newtaleImage;
    }
    public String getUrl() {
        return taleImage.getImageUrl();
    }
}
