package io.pandora.mall.config.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger {

    private final SwaggerProperties swaggerProperties;

    public Swagger(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket createManageApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后台管理 - API")
                .apiInfo(apiInfo())
                .select()
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //包下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("io.pandora.mall.manage"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(manage_security());
    }

    @Bean
    public Docket createTokenApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("接口授权 - API")
                .apiInfo(apiInfo())
                .select()
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //包下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("io.pandora.mall.module.security"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(manage_security());
    }

    @Bean
    public Docket createSocialApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("社交服务 - API")
                .apiInfo(apiInfo())
                .select()
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //包下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("io.pandora.mall.module.social"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(security());
    }

    @Bean
    public Docket createMallApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("商城服务 - API")
                .apiInfo(apiInfo())
                .select()
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //包下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("io.pandora.mall.module.shop"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(security());
    }

    @Bean
    public Docket createMessageApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("聊天服务 - API")
                .apiInfo(apiInfo())
                .select()
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //包下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("io.pandora.mall.module.message"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .contact(swaggerProperties.getContactName() + " - " + swaggerProperties.getContactEmail())
                .description(swaggerProperties.getDescription())
                .termsOfServiceUrl(swaggerProperties.getContactUrl())
                .version(swaggerProperties.getVersion())
                .build();
    }

    private List<ApiKey> security() {
        return Arrays.asList(new ApiKey("accessToken", "accessToken", "header"));
    }

    private List<ApiKey> manage_security() {
        return Arrays.asList(new ApiKey("Authorization", "Authorization", "header"));
    }

}
