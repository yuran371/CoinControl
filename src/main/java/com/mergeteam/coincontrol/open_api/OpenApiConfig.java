package com.mergeteam.coincontrol.open_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "CoinControl",
                description = "finance project", version = "1.0.0",
                contact = @Contact(
                        name = "Radchenko Elena",
                        email = "elena.radchenko.9@mail.ru")
        )
)
public class OpenApiConfig {

}