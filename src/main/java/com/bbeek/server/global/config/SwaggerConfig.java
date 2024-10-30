package com.bbeek.server.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("BBeek")
                .description("이 API는 자체 로그인과 소셜 로그인(구글, 네이버, 카카오)을 지원합니다. "
                    + "엑세스 토큰과 리프레시 토큰은 HttpOnly 쿠키로 반환됩니다.\n\n"
                    + "### 소셜 로그인 API 엔드포인트:\n"
                    + "- 카카오 로그인: `/oauth2/authorization/kakao`\n"
                    + "- 네이버 로그인: `/oauth2/authorization/naver`\n"
                    + "- 구글 로그인: `/oauth2/authorization/google`\n\n"
                    + "### 상태 코드 및 오류 메시지 형식:\n"
                    + "| 상태 코드 | 오류 코드 | 설명 |\n"
                    + "| --- | --- | --- |\n"
                    + "| 400 | BAD_REQUEST | 요청이 잘못되었습니다. 필수 입력값이 누락되었거나 잘못된 데이터 형식입니다. |\n"
                    + "| 401 | EMPTY_AUTHORIZATION_HEADER | 요청 헤더에 인증 토큰이 없습니다. 유효한 토큰을 포함시켜 주세요. |\n"
                    + "| 401 | INVALID_TOKEN | 제공된 토큰이 유효하지 않습니다. 올바른 토큰을 사용해 다시 시도하세요. |\n"
                    + "| 401 | EXPIRED_TOKEN | 토큰의 유효기간이 만료되었습니다. 새로운 토큰을 발급받아야 합니다. |\n"
                    + "| 401 | SECRET_KEY_MISMATCH | 시크릿 키가 일치하지 않습니다. 보안 설정을 다시 확인하세요. |\n"
                    + "| 401 | AUTH_REQUIRED | 인증이 필요합니다. 로그인이 되어 있지 않거나 권한이 부족합니다. |\n"
                    + "| 404 | USER_NOT_FOUND | 해당 유저 정보를 찾을 수 없습니다. 요청한 유저가 존재하지 않거나 삭제되었습니다. |\n"
                    + "| 403 | INSUFFICIENT_POINTS | 포인트가 부족하여 요청을 처리할 수 없습니다. 필요한 포인트를 충전하고 다시 시도하세요. |\n"
                    + "| 409 | USER_EXISTED | 동일한 정보로 이미 등록된 유저가 존재합니다. 중복된 이메일 또는 ID를 확인해 주세요. |\n"
                    + "| 404 | ITEM_NOT_FOUND | 요청한 아이템을 찾을 수 없습니다. 잘못된 아이템 ID를 확인하고 다시 시도하세요. |\n"
                    + "| 404 | IMAGE_NOT_FOUND | 이미지 파일을 찾을 수 없습니다. 이미지 경로 또는 ID를 다시 확인하세요. |\n"
                    + "| 404 | CATEGORY_NOT_FOUND | 요청한 카테고리가 존재하지 않습니다. 올바른 카테고리 이름을 사용하세요 (예: head, theme). |\n"
                    + "| 404 | INVENTORY_NOT_FOUND | 사용자의 인벤토리에 해당 구매한 아이템이 없습니다. 아이템이 구매되었는지 확인하세요. |\n"
                    + "| 500 | INTERNAL_SERVER_ERROR | 서버 내부 오류가 발생했습니다. 서버 로그를 확인하고 문제를 해결하세요." + "\n\n"
                    + "### 오류 메시지 예시:\n"
                    + "```json\n"
                    + "{\n"
                    + "  \"status\": 401,\n"
                    + "  \"errorCode\": \"EMPTY_AUTHORIZATION_HEADER\",\n"
                    + "  \"message\": \"요청 헤더에 인증 토큰이 없습니다.\",\n"
                    + "  \"timestamp\": \"2024-09-29T10:00:00\"\n"
                    + "}\n"
                    + "```"))  // API 기본 정보를 설정합니다.

            // JWT 보안 요구 사항을 추가합니다.
            .addSecurityItem(new SecurityRequirement().addList("jwtAuth"))

            // Security Scheme 설정을 추가합니다.
            .components(new Components()
                .addSecuritySchemes("jwtAuth", new SecurityScheme()
                    .name("JWT Authentication")
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .description("엑세스 토큰과 리프레시 토큰은 HttpOnly 쿠키로 설정됩니다. "
                        + "API 호출 시 클라이언트는 쿠키로 인증이 진행됩니다.")))

            .addServersItem(new Server().url("/"));
    }
}
