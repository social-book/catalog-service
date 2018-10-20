package com.socialbook.catalogs.services;

import com.socialbook.catalogs.entities.Image;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ImagesBean {

    private Logger logger = Logger.getLogger(ImagesBean.class.getName());

    @PersistenceContext(unitName = "catalog-service-jpa")
    private EntityManager em;

    public List<Image> getImages() {
        return (List<Image>) em.createNamedQuery("Image.getAll").getResultList();
    }
}
