package hanium.englishfairytale.tale.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    protected TaleImage(Tale tale, String originalName, String storedName) {
        this.originalName = originalName;
        this.storedName = storedName;
        tale.putImage(this);
    }

}
