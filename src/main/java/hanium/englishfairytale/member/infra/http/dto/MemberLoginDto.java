package hanium.englishfairytale.member.infra.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
