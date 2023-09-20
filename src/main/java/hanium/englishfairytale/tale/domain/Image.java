package hanium.englishfairytale.tale.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class Image {
    @OneToOne
    @JoinColumn(name="taleImage_id")
    private TaleImage taleImage;

    protected Image() {
        this.taleImage = new TaleImage();
    }

    void putTaleImage(TaleImage newtaleImage) {
        taleImage = newtaleImage;
    }
}
