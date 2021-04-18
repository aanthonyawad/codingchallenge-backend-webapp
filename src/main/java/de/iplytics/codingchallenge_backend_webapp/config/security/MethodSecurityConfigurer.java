package de.iplytics.codingchallenge_backend_webapp.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Profile(value = {"development", "production"})
public class MethodSecurityConfigurer
        extends GlobalMethodSecurityConfiguration {
}