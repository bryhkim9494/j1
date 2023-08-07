package org.zerock.j1.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration   // 이 클래스가 스프링의 설정 클래스임을 나타냅니다.
public class RootConfig {
    @Bean   // 스프링 컨테이너에 Bean을 등록하는 메서드임을 나타냅니다.
    public ModelMapper getMapper() {

        ModelMapper modelMapper = new ModelMapper();   // ModelMapper 인스턴스를 생성합니다.
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)   // 필드 매칭을 활성화합니다.
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)   // private 필드에 접근할 수 있도록 설정합니다.
                .setMatchingStrategy(MatchingStrategies.LOOSE);   // 매칭 전략을 LOOSE로 설정합니다.

        return modelMapper;   // 설정이 적용된 ModelMapper 인스턴스를 반환합니다.
    }
}
