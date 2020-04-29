package com.ljj.mail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * SwaggerConfig
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:32
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket mailApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("邮件管理")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ljj.mail.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("邮件管理系统Swagger2在线API文档")
                .termsOfServiceUrl("https;//baidu.com")
                .contact(new Contact("LeeJack", "https://blog.LeeJack.com", "545430154@qq.com"))
                .version("1.1")
                .build();
    }
}
