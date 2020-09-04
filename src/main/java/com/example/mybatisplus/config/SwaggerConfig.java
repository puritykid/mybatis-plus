package com.example.mybatisplus.config;


import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI  //第三方swagger增强API注解
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        /**
         * @Author wuxw
         * @Description //关于分组接口,可后期根据多模块,拆解为根据模块来管理API文档
         * @Date 10:18 2019/3/15
         * @Param []
         * @return springfox.documentation.spring.web.plugins.Docket
         **/
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("mybaits-plus demo")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.mybatisplus.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("mybatisplus")
                .description("一个demo")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}


