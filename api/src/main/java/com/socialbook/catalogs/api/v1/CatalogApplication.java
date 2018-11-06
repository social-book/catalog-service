package com.socialbook.catalogs.api.v1;

import com.kumuluz.ee.discovery.annotations.RegisterService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@RegisterService
//@SecurityScheme(name = "openid-connect", type = SecuritySchemeType.OPENIDCONNECT,
//        openIdConnectUrl = "http://auth-server-url/.well-known/openid-configuration")
@OpenAPIDefinition(info = @Info(title = "Rest API", version = "v1", contact = @Contact(), license = @License(), description = "JavaSI API for managing conference."), security = @SecurityRequirement(name = "openid-connect"), servers = @Server(url ="http://localhost:8081/v1"))
@ApplicationPath("/v1")
public class CatalogApplication extends Application {}
