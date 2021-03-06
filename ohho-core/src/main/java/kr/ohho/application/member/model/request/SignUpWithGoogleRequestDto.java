package kr.ohho.application.member.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpWithGoogleRequestDto {

    String googleIdToken;
    String nickname;
    String gender;
    String age;
}
