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
import com.socialbook.catalogs.services.CategoriesManagerBean;
import com.socialbook.catalogs.services.ImagesManagerBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    @Inject
    CategoriesManagerBean categoriesManagerBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Object> list = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();


        AlbumDto albumDto = new AlbumDto();
        albumDto.setTitle("Testalbum");

        ImageDto imageDto = new ImageDto();
        imageDto.setImageName("TestImage");
        imageDto.setImageSrc("http://image-servlet/image?id=2");
        imageDto.setImageName("TestImage");
        imageDto.setAlbum(albumDto);


        List<ImageDto> images = new ArrayList<>();
        images.add(imageDto);
        albumDto.setImages(images);

        imagesManagerBean.postImage(imageDto);

        AlbumDto albumDto1 = imagesManagerBean.getAlbum(2);
//        List<Image> images2 = imagesBean.getImages();
        List<Album> albums = albumsBean.getAlbums();
//        List<Category> categories = categoriesBean.getCategories();
//
        list.add(albumDto1);
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
