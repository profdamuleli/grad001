package com.enviro.assessment.grad001.lutendodamuleli.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Lutendo Damuleli",
                        email = "lutendo.damuleli@outlook.com"
                ),
                description = "OpenApi documentation for spring application",
                title = "OpenApi specification - Blastza",
                version = "1.0"

        )
)
public class OpenApiConfig {
}
