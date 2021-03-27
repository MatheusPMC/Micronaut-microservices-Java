package com.matheusprado.udemy.auth;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/secured")
public class SecureEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(SecureEndpoint.class);

    @Get("/status")
    public List<Object> status(Principal principal) {
        Authentication details = (Authentication) principal;
        LOG.debug("User Details: {}", details);
        return Arrays.asList(
                details.getName(),
                details.getAttributes().get("hair_color"),
                details.getAttributes().get("language"));
    }
}
