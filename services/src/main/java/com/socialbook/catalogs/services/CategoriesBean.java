package com.socialbook.catalogs.services;

import com.socialbook.catalogs.entities.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class CategoriesBean {

    private Logger logger = Logger.getLogger(CategoriesBean.class.getName());

    @PersistenceContext(unitName = "catalog-service-jpa")
    private EntityManager em;

    public List<Category> getCategories() {
        return em.createNamedQuery("Category.getAll").getResultList();
    }

    public Category getCategory(Integer categoryId) {
        em.createNamedQuery("Category.getCategory").setParameter("category_id", categoryId);
        return em.getReference(Category.class, categoryId);
    }
}
