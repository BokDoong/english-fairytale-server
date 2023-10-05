package hanium.englishfairytale.tale.domain;

public interface ImageRepository {

    String save(TaleImage taleImage);

    void delete(Long taleImageId);
}
