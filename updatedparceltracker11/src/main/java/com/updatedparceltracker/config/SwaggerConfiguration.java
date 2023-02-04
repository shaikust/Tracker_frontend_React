package com.updatedparceltracker.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "Parcel Tracker Application",
    description = "Tracking parcel",
    version = "1.0",

    license = @License(
      name = "Apache 2.0",
      url = "https://www.apache.org/licenses/LICENSE-2.0.html"
    )
  ),
  servers = @Server(
    url = "http://localhost:8080/",
    description = "Server"
  ),
  security = @SecurityRequirement(name = "JwtAuthentication")
)
public class SwaggerConfiguration {
}
