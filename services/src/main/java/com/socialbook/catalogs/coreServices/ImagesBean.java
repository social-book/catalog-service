package com.socialbook.catalogs.coreServices;

import com.socialbook.catalogs.entities.Image;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class ImagesBean {

    private static final String TAG = ImagesBean.class.getName();
    private Logger logger = Logger.getLogger(TAG);

    //TODO add image... by default of no attr create album and set category of none?

    @PersistenceContext(unitName = "catalog-service-jpa")
    private EntityManager em;


    //DTOs, kompleksni beani.. todos itd.

    //Uploader service
    //Like/Comment se združita
    //Uploader komunicira s CatalogServiceom ---- UserId pa pride že s frontenda
    //Vsak request ko ga dobimo pošlje še dodatni request statistki kjer se stvar posodobi!!!

    //Messaging odpade, ker je to zdaj front-end


    //to je prvi del naloge!!!
    @PostConstruct
    private void init() {
        logger.info("Initialization of bean: " + TAG + ", UUID: " + this.toString());
    }

    @PreDestroy
    private void closure() {
        logger.info("Closing bean: " + TAG + ", UUID: " + this.toString());
    }

    //READ
    public List<Image> getImages() {
        logger.info(TAG + " Retrieving images");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Image> query = criteriaBuilder.createQuery(Image.class);
        Root<Image> from = query.from(Image.class);
        query.select(from);
        return em.createQuery(query).getResultList();
    }

    //READ
    public Image getImage(Integer imageId) {
        logger.info(TAG + " Retrieving image");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Image> query = criteriaBuilder.createQuery(Image.class);
        Root<Image> c = query.from(Image.class);
        ParameterExpression<Integer> p = criteriaBuilder.parameter(Integer.class);
        query.select(c).where(criteriaBuilder.gt(c.get("image_id"), p));
        em.createNamedQuery("Image.getImage").setParameter("image_id", imageId);
        return em.createQuery(query).getResultList().get(0);
    }

    //CREATE
    @Transactional
    public void addImage(Image image) {
        logger.info(TAG + ": adding new image");
        if (image != null){
//            em.getTransaction().begin();
            em.persist(image);
//            em.getTransaction().commit();
        }
    }

    //UPDATE
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void updateImage(Image image, int id) { //TODO here must be ImageDTO!!!!! as attr
        logger.info(TAG + ": updating image");
        em.getTransaction().begin();
        Image imageOld = em.find(Image.class, id);
        imageOld.setImageName(image.getImageName());
        imageOld.setImageSrc(image.getImageSrc());
        em.persist(imageOld);
        em.getTransaction().commit();
    }

    //DELETE
    @Transactional
    public void deleteImage(Integer id) {
        logger.info(TAG + ": deleting image with id: " + id);
        em.getTransaction().begin();
        Image imageToDel = em.find(Image.class, id);
        em.remove(imageToDel);
        em.getTransaction().commit();
    }
}
