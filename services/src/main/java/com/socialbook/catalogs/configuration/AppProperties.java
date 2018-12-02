package com.socialbook.catalogs.configuration;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;

@ApplicationScoped
@ConfigBundle("app-properties")
public class AppProperties {
    @ConfigValue(value = "external-services.enabled", watch = true)
    private boolean externalServicesEnabled;

    @ConfigValue(value = "statistic-service.enabled", watch = true)
    private boolean statisticServiceEnabled;

    @ConfigValue(value = "healthy-service.enabled", watch = true)
    private boolean healthyServiceEnabled;

    public boolean isExternalServicesEnabled() {
        return externalServicesEnabled;
    }

    public void setExternalServicesEnabled(boolean externalServicesEnabled) {
        this.externalServicesEnabled = externalServicesEnabled;
    }

    public boolean isStatisticServiceEnabled() {
        return statisticServiceEnabled;
    }

    public void setStatisticServiceEnabled(boolean statisticServiceEnabled) {
        this.statisticServiceEnabled = statisticServiceEnabled;
    }

    public boolean isHealthyServiceEnabled() {
        return healthyServiceEnabled;
    }

    public void setHealthyServiceEnabled(boolean healthyServiceEnabled) {
        this.healthyServiceEnabled = healthyServiceEnabled;
    }
}
