package com.socialbook.catalogs.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import com.socialbook.catalogs.coreServices.CategoriesBean;
import com.socialbook.catalogs.dtos.CategoryDto;
import com.socialbook.catalogs.dtos.Mapper;
import com.socialbook.catalogs.entities.Album;
import com.socialbook.catalogs.entities.Category;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
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


    //READ deafultCategory
    public Category getDefaultCategory() {
        logger.info("reading default category");
        return categoriesBean.getCategory(1);
    }

    //READ all
    public List<CategoryDto> getAll(QueryParameters queryParameters) {
        logger.info("Reading all categories");
        List<Category> categories = JPAUtils.queryEntities(em, Category.class, queryParameters);
        return Mapper.convertToCategoryDtos(categories);
    }

    public Long getCategoriesCount(QueryParameters queryParameters) {
        return JPAUtils.queryEntitiesCount(em, Category.class, queryParameters);
    }
}
