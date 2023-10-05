package hanium.englishfairytale.member.domain;

import hanium.englishfairytale.tale.domain.Tale;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tale> tales = new ArrayList<>();

    @Builder
    public Member(String name, String phoneNumber, String nickname, String email, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.createdTime = LocalDateTime.now();
        this.tales = new ArrayList<>();
    }

    public void addTale(Tale newTale) {
        this.tales.add(newTale);
    }
    public void putImage(MemberImage memberImage) {
        image.putMemberImage(memberImage);
    }
}
