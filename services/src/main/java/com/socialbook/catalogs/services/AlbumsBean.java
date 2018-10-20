package com.socialbook.catalogs.services;

import com.socialbook.catalogs.entities.Album;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class AlbumsBean {

    private Logger logger = Logger.getLogger(AlbumsBean.class.getName());

    @PersistenceContext(unitName = "catalog-service-jpa")
    private EntityManager em;

    public List<Album> geAlbums() {
        return em.createNamedQuery("Album.getAll").getResultList();
    }

}
