package com.socialbook.catalogs.api.v1.mappers;

import com.socialbook.catalogs.errors.InvalidAlbumException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidAlbumExceptionMapper implements ExceptionMapper<InvalidAlbumException> {
    @Override
    public Response toResponse(InvalidAlbumException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"error-msg\":" + "\"" + exception.getMessage() + "\"}")
                .build();
    }
}
