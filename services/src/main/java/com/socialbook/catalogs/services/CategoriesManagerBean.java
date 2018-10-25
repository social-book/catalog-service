package com.socialbook.catalogs.services;

import com.socialbook.catalogs.coreServices.CategoriesBean;
import com.socialbook.catalogs.dtos.CategoryDto;
import com.socialbook.catalogs.entities.Category;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@ApplicationScoped
public class CategoriesManagerBean {


    private static final String TAG = CategoriesManagerBean.class.getName();
    private Logger logger = Logger.getLogger(TAG);

    @PersistenceContext(unitName = "catalog-service-jpa")
    private EntityManager em;

    @Inject
    private CategoriesBean categoriesBean;

    @PostConstruct
    private void init() {
        logger.info("Initialization of bean...");
    }

    @PreDestroy
    private void destroy() {
        logger.info("Destroying bean...");
    }

    //CREATE category
    @Transactional
    public void createCategory(CategoryDto categoryDto) {
        logger.info("creating category...");
        Category category = new Category();
        category.setCategoryTitle(categoryDto.getTitle());
        categoriesBean.createCategory(category);
    }
}
