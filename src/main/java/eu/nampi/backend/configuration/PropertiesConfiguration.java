package eu.nampi.backend.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ConfigurationProperties("nampi")
public class PropertiesConfiguration {

    private String fusekiUrl;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setNullValue("");
        return configurer;
    }

    public String getFusekiUrl() {
        return this.fusekiUrl;
    }

    public void setFusekiUrl(String fusekiUrl) {
        this.fusekiUrl = fusekiUrl;
    }

}