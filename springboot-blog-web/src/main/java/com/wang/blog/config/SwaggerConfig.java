package com.wang.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/2 9:51
 */
@Configuration
//开启swagger
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                //配置一些ui上显示的数据
                .apiInfo(apiInfo())
                // 选择哪些接口作为swagger的doc发布
                .select()
                //基于？去扫描
                .apis(RequestHandlerSelectors.any())
                //过滤路径 上面配置了扫描的包 这里配置了还需满足这个路径的请求才会被扫描
//                .paths(PathSelectors.ant("/wang/*"))
                .build()


                ;
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //标题
                .title("王承杨的swagger")
                //描述
                .description("这是配置swagger")
                //版本号
                .version("1.0")
                //这是能显示一个网址 组织网址 公司的网址之类的
                .termsOfServiceUrl("")
                //作者信息
                .contact(new Contact("王承杨","","2837288678@qq.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build()
                ;
    }
}
