package com.socialbook.catalogs.coreServices;

import com.socialbook.catalogs.entities.Category;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class CategoriesBean {

    private static final String TAG = CategoriesBean.class.getName();
    private Logger logger = Logger.getLogger(TAG);

    @PersistenceContext(unitName = "catalog-service-jpa")
    private EntityManager em;

    //READ
    public List<Category> getCategories() {
        return em.createNamedQuery("Category.getAll").getResultList();
    }

    //READ
    @Timed
    public Category getCategory(Integer categoryId) {
        em.createNamedQuery("Category.getCategory").setParameter("category_id", categoryId);
        return em.getReference(Category.class, categoryId);
    }

    //CREATE
    @Transactional
    public void createCategory(Category category) {
        logger.info("adding new category");
        if (category != null){
            //em.getTransaction().begin();
            em.persist(category);
            //em.getTransaction().commit();
        }
    }

    //UPDATE
    @Transactional
    public void updateCategory(Category category, Integer id) {
        logger.info("updating category with id: " + id);
        //em.getTransaction().begin();
        Category categoryOld = em.find(Category.class, id);
        categoryOld.setCategoryTitle(category.getCategoryTitle());
        em.persist(categoryOld);
       // em.getTransaction().commit();
    }

    //DELETE
    @Transactional
    public void remoteCategory(Integer id) {
        //em.getTransaction().begin();
        Category category = em.find(Category.class, id);
        em.remove(category);
        //em.getTransaction().commit();
    }
}
