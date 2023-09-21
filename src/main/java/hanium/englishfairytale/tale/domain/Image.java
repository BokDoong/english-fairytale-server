package hanium.englishfairytale.tale.domain;

import javax.persistence.*;

@Embeddable
public class Image {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="taleImage_id")
    private TaleImage taleImage;

    void putTaleImage(TaleImage newtaleImage) {
        taleImage = newtaleImage;
    }
}
