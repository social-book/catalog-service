package com.socialbook.catalogs.coreServices;

import com.socialbook.catalogs.entities.Album;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RequestScoped
public class AlbumsBean {

    private static final String TAG = AlbumsBean.class.getName();
    private Logger logger = Logger.getLogger(TAG);

    @PersistenceContext(unitName = "catalog-service-jpa")
    private EntityManager em;

    //READ
    public List<Album> getAlbums() {
        return em.createNamedQuery("Album.getAll").getResultList();
    }

    //READ
    public List<Album> getCategory(Integer userId) {
        return em.createNamedQuery("Album.getUserAlbums").setParameter("albumUserReferenceId", userId).getResultList();
    }

    //READ
    public Album getAlbum(Integer id) {
        logger.info("Getting album with id: " + id);
        Album album = em.find(Album.class, id);
        if (album != null)
            return album;
        throw new NotFoundException("Album not found with id: " + id);
    }

    //READ
    public List<Album> getUserAlbums(String userId) {
        return em.createNamedQuery("Album.getUserAlbums").setParameter("albumUserReferenceId", userId).getResultList();
    }

    //CREATE
    @Transactional
    public void createAlbum(Album album) {
        logger.info("creating album");
        if (album != null){
            //em.getTransaction().begin();
            em.persist(album);
            em.flush();
            //em.getTransaction().commit();
        }
    }

    //UPDATE
    @Transactional
    public void updateAlbum(Album album, Integer id) {
//        em.getTransaction().begin();
        logger.info("updating album");
        Album albumOld = em.find(Album.class, id);
        albumOld.setAlbumTitle(album.getAlbumTitle());
//        albumOld.setImages(album.getImages());
        em.merge(albumOld);
//        em.flush();
        //em.getTransaction().commit();
    }

    //DELETE
    @Transactional
    public void deletAlbum(Integer id) {
        logger.info("deleting album with id: " + id);
        //em.getTransaction().begin();
        Album album = em.find(Album.class, id);
        if (album != null)
            em.remove(album);
        //em.getTransaction().commit();
    }
}
