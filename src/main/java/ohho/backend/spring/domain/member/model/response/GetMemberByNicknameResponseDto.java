package ohho.backend.spring.domain.member.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetMemberByNicknameResponseDto {

    private Long id;
    private String nickname;
}