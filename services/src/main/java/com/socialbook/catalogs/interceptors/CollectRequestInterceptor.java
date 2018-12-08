package com.socialbook.catalogs.interceptors;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.socialbook.catalogs.configuration.AppProperties;
import com.socialbook.catalogs.services.RequestCollectorBean;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.logging.Logger;

@Interceptor
@CollectRequests
public class CollectRequestInterceptor {
    private Logger logger = Logger.getLogger(CollectRequestInterceptor.class.getName());

    @Inject
    AppProperties appProperties;
    @Inject
    @DiscoverService("statistic-service")
    private Optional<String> baseUrl;

    @AroundInvoke
    public Object collectRequest(InvocationContext context) throws Exception {
        logger.info("collecting request");
        if (appProperties.isStatisticServiceEnabled()){
            logger.info("statistic enabled -- collecting");
            collectIt("catalog-service");
        } else {
            logger.info("statistic disabled -- continue");
        }

        logger.info("interceptor proceeding context");
        return context.proceed();
    }

    public void collectIt(String serviceName) {
        Client httpClient = ClientBuilder.newClient();
        try {
            httpClient
                    .target(baseUrl.get() + "/v1/statistics/collect/" + serviceName)
                    .request().get(new GenericType<String>() {
            });

        } catch (WebApplicationException | ProcessingException e) {
            logger.severe(e.getMessage());
        }
    }
}
