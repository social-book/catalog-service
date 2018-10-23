package com.socialbook.catalogs.api.v1;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialbook.catalogs.entities.Album;
import com.socialbook.catalogs.entities.Category;
import com.socialbook.catalogs.entities.Image;
import com.socialbook.catalogs.coreServices.AlbumsBean;
import com.socialbook.catalogs.coreServices.CategoriesBean;
import com.socialbook.catalogs.coreServices.ImagesBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private ImagesBean imagesBean;

    @Inject
    private CategoriesBean categoriesBean;

    @Inject
    private AlbumsBean albumsBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<List> list = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Image> images = imagesBean.getImages();
        List<Album> albums = albumsBean.getAlbums();
        List<Category> categories = categoriesBean.getCategories();

        list.add(images);
        list.add(albums);
        list.add(categories);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String json = objectMapper.writeValueAsString(list);
        out.print(json);
        out.flush();
    }
}
