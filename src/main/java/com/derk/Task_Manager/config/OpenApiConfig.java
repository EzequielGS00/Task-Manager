package com.derk.Task_Manager.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI taskManagerOpenApi(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Task Manager API")
                                .description(
                                        "API REST para la gestion de tareas, estados y prioridades."
                                )
                                .version("1.0.0")
                );
    }
}
