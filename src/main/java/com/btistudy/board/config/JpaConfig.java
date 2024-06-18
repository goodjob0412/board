package com.btistudy.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.swing.*;
import java.util.Optional;

/* 할일: 이전에 만든거는 인증기능이 없어서 무조건 bitstudy가 들어가게 했었다.
        인증기능(시큐리티) 만들거니까 실제로 인증된 사용자로부터 정보를 받을수 있게 될거다.
        그래스 그 부분을 구현할거임
 */

/* @Configuration 이라고 하면 JPA가 '이건 설정파일' 이라고 인식해서 Configration Bean 으로 등록함 */
//@Configuration
//@EnableJpaAuditing
/* JPA 에서 auditing 을 가능하게 하는 어노테이션
                        jpa auditing: 감시, 감사
                                      Spring Data Jpa 에서 자동으로 값 넣어주는 기능
 */

public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware() {

/* 삭제 */
        // 람다식(자바스크립트에서 화살표 함수 같은거)
        return ()-> Optional.of("bitstudy");
        // 이렇게 하면 앞으로 JPA Auditing 할때마나 사람 이름은 bitstudy 로 넣게 됨
        // TODO: 나중에 스프링 시큐리티로 인증 기능 붙일때 수정할거임
    }

//    /* 추가 */
//    @Bean
//    public AuditorAware<SecurityContext> auditorAware() {
//        return ()-> Optional.ofNullable(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication)
//                .filter(Authentication::isAuthenticated)
//                .map(Authentication::getPrincipal)
//
//
//    }
}









