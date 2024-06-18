package com.btistudy.board.config;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defaultTemplateResolver,
            Thymeleaf3Properties thymeleaf3Properties
            
            /* thymeleafTemplateResolver 라는 빈을 등록할건데 리턴타입은 SpringResourceTemplateResolver 다
                그런데 스프링부트 프로젝트에 넣었을때 auto_configration 가 자동으로 잡힐거다
             */
    ) {

        /* 그런데 Decoupled 로직을 세팅하는건 이미 만들어져 있음. 그런데 외부 프로퍼티 이기 때문에 인식을 못한다.
            그래서 application.yaml 파일 가서 맨 아래 'thymeleaf3' 부분처럼 설정할거임
         */
        defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.isDecoupledLogic());
        return defaultTemplateResolver;
    }

    @RequiredArgsConstructor
    @Getter
//    @ConstructorBinding
    @ConfigurationProperties("spring.thymeleaf3") /* prefix 로 이름을 thymeleaf3 로 설정함(yaml 에서 사용할 이름) */
    public static class Thymeleaf3Properties {
        private final boolean decoupledLogic;

//        @ConstructorBinding
//        public Thymeleaf3Properties(boolean decoupledLogic) {
//            this.decoupledLogic = decoupledLogic;
//        }
//        public boolean isDecoupledLogic() {
//            return decoupledLogic;
//        }

        /* 다 하면 BoardApplication 에 @ConfigurationPropertiesScan 달고
            yaml 맨 아래 'thymeleaf3' 추가
        *  */
    }
}
