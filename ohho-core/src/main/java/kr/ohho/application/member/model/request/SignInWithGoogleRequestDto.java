package kr.ohho.application.member.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInWithGoogleRequestDto {

    private String googleIdToken;
}
