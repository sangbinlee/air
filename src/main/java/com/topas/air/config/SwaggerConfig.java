package com.topas.air.config;

import java.util.Arrays;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;

@OpenAPIDefinition(info = @Info(title = "Couple App", description = "couple app api명세", version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build();
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("test").description("description").version("1.0.0").build();
//	}

//	@Bean
//	public GroupedOpenApi publicApi() {
//		return GroupedOpenApi.builder().group("springshop-public").pathsToMatch("/public/**").build();
//	}

//	@Bean
//	public GroupedOpenApi adminApi() {
//		return GroupedOpenApi.builder().group("springshop-admin").pathsToMatch("/admin/**")
//				.addOpenApiMethodFilter(method -> method.isAnnotationPresent(Admin.class)).build();
//	}

//	  @Bean
//	  public OpenAPI springShopOpenAPI() {
//	      return new OpenAPI()
//	              .info(new Info().title("SpringShop API")
//	              .description("Spring shop sample application")
//	              .version("v0.0.1")
//	              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
//	              .externalDocs(new ExternalDocumentation()
//	              .description("SpringShop Wiki Documentation")
//	              .url("https://springshop.wiki.github.org/docs"));
//	  }

	/**
	 * swagger 화면에 authorize 버튼 보이게 할때 사용 :  Available authorizations
	 * @return
	 */
	@Bean
	public OpenAPI openAPI() {
		SecurityScheme securityScheme = new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
				.bearerFormat("JWT").in(SecurityScheme.In.HEADER).name("Authorization");
		SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

		return new OpenAPI()
				.components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
				.security(Arrays.asList(securityRequirement))
				;
	}


	/**
	 * mapping vi 인 경우만 나오게 할때
	 * @return
	 */
	@Bean
	public GroupedOpenApi chatOpenApi() {
		String[] paths = { "/v1/**" };

		return GroupedOpenApi.builder().group("COUPLE API v1").pathsToMatch(paths).build();
	}

}
