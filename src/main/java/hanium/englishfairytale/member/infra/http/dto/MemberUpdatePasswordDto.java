package hanium.englishfairytale.member.infra.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdatePasswordDto {
    @NotNull
    private Long memberId;
    @NotNull
    @Pattern(regexp = "^(?i)(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{2,10}$")
    private String password;
}
