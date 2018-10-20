package com.socialbook.catalogs.api.v1;


import com.socialbook.catalogs.entities.Image;
import com.socialbook.catalogs.services.AlbumsBean;
import com.socialbook.catalogs.services.CategoriesBean;
import com.socialbook.catalogs.services.ImagesBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

        List<Image> images = imagesBean.getImages();
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(images.toString());
        out.flush();


    }
}
