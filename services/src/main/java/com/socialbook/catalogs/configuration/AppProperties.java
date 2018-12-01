package com.socialbook.catalogs.configuration;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;

@RequestScoped
@ConfigBundle("app-properties")
public class AppProperties {
    @ConfigValue(value = "external-services.enabled", watch = true)
    private boolean externalServicesEnabled;

    @ConfigValue(value = "statistic-service.enabled", watch = true)
    private boolean statisticServiceEnabled;

    @ConfigValue(value = "healthy.enabled", watch = true)
    private boolean isHealthy = true;

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

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }
}
