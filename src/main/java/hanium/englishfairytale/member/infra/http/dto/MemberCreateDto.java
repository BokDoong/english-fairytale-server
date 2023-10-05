package hanium.englishfairytale.member.infra.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateDto {
    @NotNull
    @Size(min = 2, max = 6)
    private String name;
    @NotNull
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
    private String phoneNumber;
    @NotNull
    @Size(min = 2, max = 10)
    private String nickname;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @NotNull
    @Pattern(regexp = "^(?i)(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{2,10}$")
    private String password;
}
