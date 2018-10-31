package com.socialbook.catalogs.interceptors;

import com.socialbook.catalogs.configuration.AppProperties;
import com.socialbook.catalogs.services.RequestCollectorBean;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@CollectRequests
public class CollectRequestInterceptor {
    private Logger logger = Logger.getLogger(CollectRequestInterceptor.class.getName());

    @Inject
    RequestCollectorBean requestCollectorBean;

    @Inject
    AppProperties appProperties;

    @AroundInvoke
    public Object collectRequest(InvocationContext context) throws Exception {
        logger.info("collecting request");
        if (appProperties.isStatisticServiceEnabled()){
            logger.info("statistic enabled -- collecting");
            requestCollectorBean.sendStatistic();
        } else {
            logger.info("statistic disabled -- continue");
        }
        logger.info("interceptor proceeding context");
        return context.proceed();
    }
}
