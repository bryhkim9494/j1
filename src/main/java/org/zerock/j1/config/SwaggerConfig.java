package org.zerock.j1.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "Zerock App", version = "v1"))  // OpenAPI 정보를 설정합니다. API 문서에 대한 기본 정보를 지정합니다.
@RequiredArgsConstructor   // 필수 생성자를 자동으로 생성합니다.
@Configuration   // 이 클래스가 스프링의 설정 클래스임을 나타냅니다.
public class SwaggerConfig {  //스프링부트 3버전 swagger설정
    @Bean   // 스프링 컨테이너에 Bean을 등록하는 메서드입니다.
    public GroupedOpenApi chatOpenApi() {

        String[] paths = {"/**"};   // API 문서를 생성할 경로를 지정합니다. 여기서는 모든 경로를 대상으로 합니다.

        return GroupedOpenApi.builder()
                .group("Zerock OPEN API v1")   // API 그룹 이름을 지정합니다.
                .pathsToMatch(paths)   // 설정한 경로에 대한 API 문서를 생성합니다.
                .build();   // 생성된 GroupedOpenApi 객체를 반환합니다.
    }
}