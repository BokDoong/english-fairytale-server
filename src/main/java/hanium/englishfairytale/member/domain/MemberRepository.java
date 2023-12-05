package hanium.englishfairytale.member.domain;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findMemberById(Long userId);

    Optional<Member> findMemberByEmail(String email);

    Optional<Member> findMemberByPhoneNumber(String phoneNumber);

    Optional<Member> findMemberByNickname(String nickName);

    Optional<Member> findMemberAndImage(Long memberId);

    Long save(Member member);
}
