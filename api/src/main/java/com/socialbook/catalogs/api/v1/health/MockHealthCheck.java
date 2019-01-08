package com.socialbook.catalogs.api.v1.health;

import com.socialbook.catalogs.configuration.AppProperties;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped
public class MockHealthCheck implements HealthCheck {

    @Inject
    private AppProperties appProperties;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder healthCheckResponseBuilder =
                HealthCheckResponse.named(MockHealthCheck.class.getName());

        if (appProperties.isHealthyServiceEnabled())
            return healthCheckResponseBuilder.up().build();
        return healthCheckResponseBuilder.down().build();
    }
}
