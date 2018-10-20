package com.socialbook.catalogs.services;

import com.socialbook.catalogs.entities.Image;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ImagesBean {

    private Logger logger = Logger.getLogger(ImagesBean.class.getName());

    @PersistenceContext(unitName = "catalog-service-jpa")
    private EntityManager em;

    public List<Image> getImages() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Image> query = criteriaBuilder.createQuery(Image.class);
        Root<Image> from = query.from(Image.class);
        query.select(from);
        return em.createQuery(query).getResultList();
    }

    public Image getImage(Integer imageId) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Image> query = criteriaBuilder.createQuery(Image.class);
        Root<Image> c = query.from(Image.class);
        ParameterExpression<Integer> p = criteriaBuilder.parameter(Integer.class);
        query.select(c).where(criteriaBuilder.gt(c.get("image_id"), p));
        em.createNamedQuery("Image.getImage").setParameter("image_id", imageId);
        return em.createQuery(query).getResultList().get(0);
    }
}
