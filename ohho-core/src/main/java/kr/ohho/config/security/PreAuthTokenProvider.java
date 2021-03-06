package kr.ohho.config.security;

import java.util.Collections;
import kr.ohho.application.member.MemberService;
import kr.ohho.domain.member.Member;
import lombok.RequiredArgsConstructor;

import kr.ohho.config.jwt.JwtService;
import kr.ohho.config.security.exception.TokenMissingException;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@RequiredArgsConstructor
public class PreAuthTokenProvider implements AuthenticationProvider {

    private final MemberService memberService;
    private final JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        if (authentication instanceof PreAuthenticatedAuthenticationToken) {
            String token = authentication.getPrincipal().toString();
            Long memberId = jwtService.decode(token);
            // TODO: 멤버 조회 실패하는 경우, AuthenticationException 으로 예외번역
            Member member = memberService.getMember(memberId);
            return new PreAuthenticatedAuthenticationToken(
                member.getId(),
                "",
                Collections.singletonList(
                    new SimpleGrantedAuthority(SecurityConfig.MEMBER_ROLE_NAME))
            );
        }
        throw new TokenMissingException("Invalid token");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
