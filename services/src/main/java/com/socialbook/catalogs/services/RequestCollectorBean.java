package com.socialbook.catalogs.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.logging.Logger;

@RequestScoped
public class RequestCollectorBean {
    private static final String TAG = RequestCollectorBean.class.getName();
    private Logger logger = Logger.getLogger(TAG);

    private Client httpClient;

    @PostConstruct
    private void init() {
        logger.info("Initialization of bean...");
        httpClient = ClientBuilder.newClient();
    }

    @PreDestroy
    private void destroy() {
        logger.info("Destroying bean...");
    }

    public void sendStatistic() {
        logger.info("sending stat!");
        //TODO implement
//        if (appProperties.isExternalServicesEnabled()) {
//            try {
//                return httpClient
//                        .target(baseUrl.get() + "/v1/orders")
//                        .request().get(new GenericType<Image>(){});
//            }catch (WebApplicationException | ProcessingException e2) {
//                logger.severe(e2.getMessage());
//                throw new InternalServerErrorException(e2);
//            }
//        }
//        return null;
    }

}
