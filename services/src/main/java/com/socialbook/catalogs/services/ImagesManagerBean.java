package com.socialbook.catalogs.services;

import com.socialbook.catalogs.coreServices.AlbumsBean;
import com.socialbook.catalogs.coreServices.CategoriesBean;
import com.socialbook.catalogs.coreServices.ImagesBean;
import com.socialbook.catalogs.dtos.AlbumDto;
import com.socialbook.catalogs.dtos.ImageDto;
import com.socialbook.catalogs.entities.Album;
import com.socialbook.catalogs.entities.Category;
import com.socialbook.catalogs.entities.Image;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ImagesManagerBean {

    private static final String TAG = ImagesManagerBean.class.getName();
    private Logger logger = Logger.getLogger(TAG);


    @PersistenceContext(unitName = "catalog-service-jpa")
    private EntityManager em;


    @Inject
    private ImagesBean imagesBean;

    @Inject
    private AlbumsBean albumsBean;

    @Inject
    private CategoriesBean categoriesBean;

    @Inject
    private CategoriesManagerBean categoriesManagerBean;


    @PostConstruct
    private void init() {
        logger.info("Initialization of bean...");
    }

    @PreDestroy
    private void destroy() {
        logger.info("Destroying bean...");
    }


    //User can add new image with category if not set then default is taken!
    //CREATE -> add image into album as solo image, if category specified otherwise default will be taken
    @Transactional
    public void postImage(ImageDto imageDto) {
        Image image = new Image();
        image.setImageName(imageDto.getImageName());
        image.setImageSrc(imageDto.getImageSrc());

        em.getTransaction().begin();
        Album album;
        if (imageDto.getAlbum().getId() == null) {
            album = createAlbum(imageDto);
            albumsBean.createAlbum(album);
        } else {
            album = albumsBean.getAlbum(imageDto.getAlbum().getId());
        }
        image.setAlbum(album);
        imagesBean.addImage(image);
        em.flush();
        em.getTransaction().commit();
    }

    @Transactional
    public void postImages(List<ImageDto> imageDtos) {
        Album album;
        ImageDto imageDto = imageDtos.get(0);

        if (imageDto.getAlbum().getId() == null) {
            album = createAlbum(imageDto);
            albumsBean.createAlbum(album);
        } else {
            album = albumsBean.getAlbum(imageDto.getAlbum().getId());
        }

        List<Image> images = new ArrayList<>();
        for (ImageDto imageDtoE : imageDtos) {
            Image image = new Image();
            image.setImageName(imageDtoE.getImageName());
            image.setImageSrc(imageDtoE.getImageSrc());
            image.setAlbum(album);
            images.add(image);
        }
        for (Image image : images) {
            imagesBean.addImage(image);
        }
    }

    private Album createAlbum(ImageDto imageDto) {
        Album album = new Album();
        album.setAlbumTitle(imageDto.getAlbum().getTitle());
        album.setAlbumUserReferenceId(imageDto.getAlbum().getUserId());
        if (imageDto.getAlbum().getCategory() != null) {
            album.setCategory(imageDto.getAlbum().getCategory());
        } else {
            album.setCategory(categoriesManagerBean.getDefaultCategory());
        }
        return album;
    }

    //READ all images from one album with id
    public List<ImageDto> getAlbumImages(Integer albumId) {
        Album album = albumsBean.getAlbum(albumId);
        List<ImageDto> imageDtos = new ArrayList<>();
        for (Image image: album.getImages()) {
            ImageDto imageDto = new ImageDto();
            imageDto.setAlbum(null);
            imageDto.setImageSrc(image.getImageSrc());
            imageDto.setImageName(image.getImageName());
            imageDtos.add(imageDto);
        }
        return imageDtos;
    }

    //READ
    public AlbumDto getAlbum(Integer albumId) {
        Album album = albumsBean.getAlbum(albumId);
        AlbumDto albumDto = new AlbumDto();
        List<ImageDto> imageDtos = new ArrayList<>();
        for (Image image: album.getImages()) {
            ImageDto imageDto = new ImageDto();
            imageDto.setImageName(image.getImageName());
            imageDtos.add(imageDto);
        }
        albumDto.setImages(imageDtos);
        albumDto.setTitle(album.getAlbumTitle());
        albumDto.setId(albumId);
        return albumDto;
    }

    //READ
//    public List<AlbumDto> getUsersAlbum(String userId) {
//        List<Album> albums = albumsBean.getUserAlbums(userId);
//        List<AlbumDto> albumDtos = new ArrayList<>();
//        for (Album album : albums) {
//            AlbumDto albumDto = new AlbumDto();
//            albumDto.setTitle(album.getAlbumTitle());
//            albumDto.setCategory(album.getCategory());
//            albumDto.setId(album.getAlbum_id());
//
//            List<ImageDto> imageDtos2 = new ArrayList<>();
//            for (Image image: album.getImages()) {
//                ImageDto imageDto = new ImageDto();
//                imageDto.setImageName(image.getImageName());
//                imageDto.setImageData(image.getImageData());
//                imageDtos2.add(imageDto);
//            }
//            albumDto.setImages(imageDtos2);
//            albumDtos.add(albumDto);
//        }
//        return albumDtos;
//    }
}


