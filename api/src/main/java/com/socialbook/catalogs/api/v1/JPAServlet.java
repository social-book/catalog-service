package com.socialbook.catalogs.api.v1;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialbook.catalogs.dtos.AlbumDto;
import com.socialbook.catalogs.dtos.ImageDto;
import com.socialbook.catalogs.entities.Album;
import com.socialbook.catalogs.entities.Category;
import com.socialbook.catalogs.entities.Image;
import com.socialbook.catalogs.coreServices.AlbumsBean;
import com.socialbook.catalogs.coreServices.CategoriesBean;
import com.socialbook.catalogs.coreServices.ImagesBean;
import com.socialbook.catalogs.services.ImagesManagerBean;

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

    @Inject
    ImagesManagerBean imagesManagerBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<List> list = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();


        ImageDto imageDto = new ImageDto();
        imageDto.setImageName("TestImage");
        imageDto.setImageSrc("fakingUrl");
        AlbumDto albumDto = new AlbumDto();
        albumDto.setTitle("Testalbum");
        imageDto.setAlbum(albumDto);
        //TODO category????????

        imagesManagerBean.postImage(imageDto);

        List<Image> images = imagesBean.getImages();
//        List<Album> albums = albumsBean.getAlbums();
//        List<Category> categories = categoriesBean.getCategories();
//
        list.add(images);
//        list.add(albums);
//        list.add(categories);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String json = objectMapper.writeValueAsString(list);
        out.print(json);
        out.flush();
    }
}
