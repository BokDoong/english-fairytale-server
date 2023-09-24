package hanium.englishfairytale.member.domain;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findById(Long userId);

    Optional<Member> findByPhoneNumber(String phoneNumber);

    Optional<Member> findByNickname(String nickName);

    Long save(Member member);
}
