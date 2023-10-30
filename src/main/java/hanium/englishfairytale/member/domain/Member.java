package hanium.englishfairytale.member.domain;

import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.application.dto.MemberLoginCommand;
import hanium.englishfairytale.post.domain.Likes;
import hanium.englishfairytale.tale.domain.Tale;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Embedded
    private Image image;
    @Column(name = "created_date")
    private LocalDateTime createdTime;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Tale> tales = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    @Builder
    public Member(String name, String phoneNumber, String nickname, String email, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.createdTime = LocalDateTime.now();
        this.image = new Image();
        this.tales = new ArrayList<>();
    }

    public void addTale(Tale newTale) {
        this.tales.add(newTale);
    }
    public void putImage(MemberImage memberImage) {
        image.putMemberImage(memberImage);
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateMemberImage(MemberImage memberImage) {
        if (image == null) {
            this.image = new Image();
            this.image.putMemberImage(memberImage);
        } else {
            image.putMemberImage(memberImage);
        }
    }

    public void checkImageEmpty() {
        if (image == null) {
            throw new BusinessException(ErrorCode.MEMBER_IMAGE_NON_EXISTED);
        }
    }

    public Long getImageId() {
        return this.image.getMemberImage().getId();
    }

    public void makeImageNull() {
        this.image = null;
    }

    public void verifyMemberEmailAndPassword(MemberLoginCommand memberLoginCommand) {
        if (!Objects.equals(password, memberLoginCommand.getPassword()) || !Objects.equals(email, memberLoginCommand.getEmail())) {
            throw new BusinessException(ErrorCode.LOGIN_FAILED);
        }
    }

    public void verifyMemberPassword(String originalPassword) {
        if (!Objects.equals(password, originalPassword)) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);
        }
    }
}
