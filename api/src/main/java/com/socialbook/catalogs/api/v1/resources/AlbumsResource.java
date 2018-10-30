package com.socialbook.catalogs.api.v1.resources;

import com.socialbook.catalogs.coreServices.AlbumsBean;
import com.socialbook.catalogs.dtos.AlbumDto;
import com.socialbook.catalogs.dtos.CategoryDto;
import com.socialbook.catalogs.entities.Album;
import com.socialbook.catalogs.services.CategoriesManagerBean;
import com.socialbook.catalogs.services.ImagesManagerBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/albums")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlbumsResource {

    @Inject
    ImagesManagerBean imagesManagerBean;

    @Inject
    CategoriesManagerBean categoriesManagerBean;

    //DONE Get all albums
    //DONE Get user albums
    //DONE Get all categories
    //DONE Create album with category for user
    //DONE Add image in album

    @GET
    public Response getAllAlbums() {
        List<AlbumDto> albums = imagesManagerBean.getAllAlbums();
        if (albums != null) {
            return Response.ok(albums).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("{userId}")
    public Response getAlbum(@PathParam("userId") String userId) {
        List<AlbumDto> albums = imagesManagerBean.getUserAlbums(userId);
        if (albums != null){
            return Response.ok(albums).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/categories")
    public Response getCategories() {
        List<CategoryDto> categoryDtos = categoriesManagerBean.getAll();
        if (categoryDtos != null) {
            return Response.ok(categoryDtos).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewAlbum(AlbumDto albumDto) {
        imagesManagerBean.createAlbum(albumDto);
        return Response.status(Response.Status.OK).build();
    }

    //add new image to album with service discovery
    @GET
    @Path("/add/{userId}/{albumId}")
    public Response addImageSrcToAlbum(@PathParam("userId") String userId, @PathParam("albumId") Integer albumId) {
        imagesManagerBean.addImageToAlbum(albumId, userId);
        return Response.status(Response.Status.OK).build();
    }

}
