package com.socialbook.catalogs.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.socialbook.catalogs.dtos.AlbumDto;
import com.socialbook.catalogs.dtos.CategoryDto;
import com.socialbook.catalogs.interceptors.ValidateAlbum;
import com.socialbook.catalogs.services.CategoriesManagerBean;
import com.socialbook.catalogs.services.ImagesManagerBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
@Path("/albums")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlbumsResource {

    @Context
    protected UriInfo uriInfo;

    @Inject
    ImagesManagerBean imagesManagerBean;

    @Inject
    CategoriesManagerBean categoriesManagerBean;


    @GET
    @Operation(description = "Returns list of albums.", summary = "Album list", tags = "Albums", responses = {
            @ApiResponse(responseCode = "200",
                    description = "List of albums",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = AlbumDto.class))),
                    headers = {@Header(name = "X-Total-Count",
                            schema = @Schema(type = "int"))})})
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
    public Response addNewAlbum(AlbumDto albumDto) {
        imagesManagerBean.createAlbum(albumDto);
        return Response.status(Response.Status.CREATED).build();
    }

    //add new image to album with service discovery
    @GET
    @Operation(summary = "Create Image url",
            tags = {"Images"},
            description = "Add Image url to image in particular album",
            responses = {@ApiResponse(description = "Image url added", responseCode = "201")}
    )
    @Path("/add/{userId}/{albumId}/{imageId}")
    public Response addImageSrcToAlbum(@PathParam("userId") String userId,
                                       @PathParam("albumId") Integer albumId,
                                       @PathParam("imageId") Integer imageId) {
        imagesManagerBean.addImageToAlbum(albumId, userId, imageId);
        return Response.status(Response.Status.CREATED).build();
    }

}
