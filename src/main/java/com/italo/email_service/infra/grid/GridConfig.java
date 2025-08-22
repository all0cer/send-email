package com.italo.email_service.infra.grid;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GridConfig {
     @Value("${gridkey}")
     private String accessKey;


     @Bean
     public SendGrid gridClient() {
            return new SendGrid(accessKey); 
     }

}
