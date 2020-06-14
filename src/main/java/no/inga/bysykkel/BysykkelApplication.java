package no.inga.bysykkel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ComponentScan(basePackageClasses = BysykkelApplication.class)
@SpringBootApplication
public class BysykkelApplication {
    @Value("${bysykkel.baseurl}")
    private String bysykkelBaseUrl;

    public static void main(String[] args) {
        SpringApplication.run(BysykkelApplication.class, args);
    }

    @Bean
    public WebClient createBysykkelClient() {
        return WebClient
                .builder()
                .baseUrl(bysykkelBaseUrl)
                .defaultHeader("Client-Identifier", "inga-bysykkeloversikt")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
