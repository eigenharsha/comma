package org.comma.actuate.swagger;

import org.comma.annotation.SwaggerDiscoverable;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.PathSelectors.ant;
import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Autowired
    private SwaggerProperties props;

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(props.getTitle())
                .description(props.getDescription())
                .version(props.getVersion())
//                .termsOfServiceUrl(props.getTermsOfServiceUrl())
                .contact(new Contact(props.getContact(), "github.com/kharsha-oyo", "kumar.harsha@oyorooms.com"))
                .license(props.getLicense())
                .licenseUrl(props.getLicenseUrl())
                .build();

        AlternateTypeRule alternateTypeRule = newRule(
                typeResolver.resolve(DeferredResult.class,
                        typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                typeResolver.resolve(WildcardType.class));

        List<ResponseMessage> responseMessages = Lists.newArrayList(
                new ResponseMessageBuilder().code(500).message("500 internal server error").responseModel(new ModelRef("string"))
                        .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(props.getGroup())
                .apiInfo(apiInfo)
                .select()

                // RequestHandlerSelectors has multiple api section mechanism,
                // like any, withClassAnnotation etc
                // if we want to discover all controller api then put it as
                // .apis(RequestHandlerSelectors.any())
                // otherwise, use SwaggerDiscoverable annotation on the class where you'
                // need to discover api in swagger.
                // here, SwaggerDiscoverable annotation configured for api discovery in swagger.
                .apis(withClassAnnotation(SwaggerDiscoverable.class))
                .paths(excludedPathSelector())
                .build().pathMapping("/")
                .directModelSubstitute(Date.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(alternateTypeRule)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .forCodeGeneration(true)
                .ignoredParameterTypes(SwaggerDiscoverable.class)
                .enableUrlTemplating(true);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
                .ignoreUnknownPathExtensions(true)
                .favorParameter(false)
                .ignoreAcceptHeader(true)
                .useJaf(false)
                .defaultContentType(MediaType.APPLICATION_JSON);

        // this is the key to make spring mvc respond with json result only!
        configurer.defaultContentTypeStrategy(nativeWebRequest -> {
            List<MediaType> medias = new ArrayList<>();
            medias.add(MediaType.APPLICATION_JSON);
            return medias;
        });
    }

    /**
     * Predicate to exclude the api path from swagger discovery
     * @return
     */
    protected Predicate<String> excludedPathSelector() {
        if (Strings.isNullOrEmpty(props.getExcludes())) {
            return any();
        }

        return and(Iterables.
                transform(Splitter.on(',').
                                trimResults().
                                omitEmptyStrings().
                                split(props.getExcludes()),
                        s -> not(ant(s.trim()))));

    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
