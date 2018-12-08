package com.socialbook.catalogs.api.v1.resources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.security.annotations.Secure;
import com.socialbook.catalogs.dtos.AlbumDto;
import com.socialbook.catalogs.dtos.CategoryDto;
import com.socialbook.catalogs.dtos.ImageDto;
import com.socialbook.catalogs.interceptors.CollectRequests;
import com.socialbook.catalogs.interceptors.ValidateAlbum;
import com.socialbook.catalogs.services.CategoriesManagerBean;
import com.socialbook.catalogs.services.ImagesManagerBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

//@Secure
@Log
@RequestScoped
@Path("/albums")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin
public class AlbumsResource {

    @Context
    protected UriInfo uriInfo;

    @Inject
    ImagesManagerBean imagesManagerBean;

    @Inject
    CategoriesManagerBean categoriesManagerBean;


    //@RolesAllowed({"skrbnik", "administrator"})
    @GET
    @Operation(description = "Returns list of albums.", summary = "Album list", tags = "Albums", responses = {
            @ApiResponse(responseCode = "200",
                    description = "List of albums",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = AlbumDto.class))),
                    headers = {@Header(name = "X-Total-Count",
                            schema = @Schema(type = "int"))})})
    @CollectRequests
    public Response getAllAlbums() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .defaultOffset(0)
                .defaultLimit(10)
                .build();
        List<AlbumDto> albums = imagesManagerBean.getAllAlbums(queryParameters);
        if (albums != null) {
            return Response.ok(albums).header("X-Total-Count", imagesManagerBean.getAlbumsCount(queryParameters)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Operation(summary = "Get user albums",
            tags = {"Albums"},
            description = "Returns users albums.",
            responses =
                    {
                            @ApiResponse(description = "Album details", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AlbumDto.class)))),
                            @ApiResponse(description = "Validation error", responseCode = "406")
                    }
    )
    @Path("{userId}")
    @CollectRequests
    public Response getAlbum(@PathParam("userId") String userId) {
        List<AlbumDto> albums = imagesManagerBean.getUserAlbums(userId);
        if (albums != null) {
            return Response.ok(albums).header("X-Total-Count", albums.size()).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @GET
    @Operation(summary = "Get all categories",
            tags = {"Categories"},
            description = "Return all available categories",
            responses = {
                    @ApiResponse(description = "All categories", responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
                    @ApiResponse(description = "No categories", responseCode = "404")
            }
    )
    @Path("/categories")
    @CollectRequests
    public Response getCategories() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .defaultOffset(0)
                .defaultLimit(10)
                .build();
        List<CategoryDto> categoryDtos = categoriesManagerBean.getAll(queryParameters);
        if (categoryDtos != null) {
            return Response.ok(categoryDtos).header("X-Total-Count", categoriesManagerBean.getCategoriesCount(queryParameters)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Operation(summary = "Create new album",
            tags = {"Albums"},
            description = "Create new album for particular user",
            responses = {@ApiResponse(description = "Album created", responseCode = "201")}
    )
    @CollectRequests
    public Response addNewAlbum(AlbumDto albumDto) {
        imagesManagerBean.createAlbum(albumDto);
        return Response.status(Response.Status.CREATED).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization, body")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity("")
                .build();
    }

    @GET
    @Path("/add")
    @CollectRequests
    public Response addNewAlbumGet(@QueryParam("categoryId") Integer categoryId,
                                   @QueryParam("title") String title,
                                   @QueryParam("userId") String userId) {
        AlbumDto albumDto = new AlbumDto();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryId);
        albumDto.setCategory(categoryDto);
        albumDto.setTitle(title);
        ArrayList<ImageDto> tmp = new ArrayList<>();
        albumDto.setImages(tmp);
        albumDto.setUserId(userId);
        return addNewAlbum(albumDto);
    }

    //add new image to album with service discovery
    @GET
    @Operation(summary = "Create Image url",
            tags = {"Images"},
            description = "Add Image url to image in particular album",
            responses = {@ApiResponse(description = "Image url added", responseCode = "201")}
    )
    @Path("/add/{userId}/{albumId}/{imageId}")
    @CollectRequests
    public Response addImageSrcToAlbum(@PathParam("userId") String userId,
                                       @PathParam("albumId") Integer albumId,
                                       @PathParam("imageId") Integer imageId) {
        imagesManagerBean.addImageToAlbum(albumId, userId, imageId);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/dummy")
    @CollectRequests
    public Response getDummyFallback() {
        String response = imagesManagerBean.testFallback();
        return Response.ok(response).build();
    }

}
