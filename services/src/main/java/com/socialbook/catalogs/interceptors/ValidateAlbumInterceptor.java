package com.socialbook.catalogs.interceptors;

import com.socialbook.catalogs.coreServices.AlbumsBean;
import com.socialbook.catalogs.dtos.AlbumDto;
import com.socialbook.catalogs.entities.Album;
import com.socialbook.catalogs.errors.InvalidAlbumException;
import com.socialbook.catalogs.services.ImagesManagerBean;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@ValidateAlbum
public class ValidateAlbumInterceptor {

    private Logger logger = Logger.getLogger(ValidateAlbumInterceptor.class.getName());

    @AroundInvoke
    public Object validateAlbum(InvocationContext context) throws Exception {
        logger.info("collecting request");
        AlbumDto album = (AlbumDto) context.getParameters()[0];
        if (album.getImages().size() != 0){
            logger.severe("throwing exception");
            throw new InvalidAlbumException("album has images specified... not allowed!");
        } else {
            logger.info("album ok... continuing!");
        }
        logger.info("interceptor proceeding context");
        return context.proceed();
    }

}
