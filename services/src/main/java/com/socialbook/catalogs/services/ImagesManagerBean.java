package com.socialbook.catalogs.services;

import com.socialbook.catalogs.coreServices.AlbumsBean;
import com.socialbook.catalogs.coreServices.CategoriesBean;
import com.socialbook.catalogs.coreServices.ImagesBean;
import com.socialbook.catalogs.dtos.AlbumDto;
import com.socialbook.catalogs.dtos.ImageDto;
import com.socialbook.catalogs.dtos.Mapper;
import com.socialbook.catalogs.entities.Album;
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
//            album.setCategory(imageDto.getAlbum().getCategory());
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
    public List<AlbumDto> getAllAlbums() {
        List<Album> albums = albumsBean.getAlbums();
        return Mapper.convertToAlbumDtos(albums);
    }

    //READ
    public List<AlbumDto> getUserAlbums(String userId) {
        List<Album> albums = albumsBean.getUserAlbums(userId);
        return Mapper.convertToAlbumDtos(albums);
    }

    //CREATE
    @Transactional
    public void createAlbum(AlbumDto albumDto) {
        em.getTransaction().begin();
        albumsBean.createAlbum(Mapper.convertToDao(albumDto));
        em.flush();
        em.getTransaction().commit();
    }

    @Transactional
    public void addImageToAlbum(Integer albumId, String userId) {
        em.getTransaction().begin();
        Album album = albumsBean.getAlbum(albumId);
        List<Image> images = album.getImages();
        Image image = new Image();
        image.setImageName("NONE");//TODO pass it by params
        image.setImageSrc("http://localhost:8082/upload-image?userId=" + userId + "&albumId=" + albumId);
        image.setAlbum(album);
        em.persist(image);
        em.flush();
        images.add(image);
        album.setImages(images);
        em.persist(album);
        em.flush();
        em.getTransaction().commit();
    }
}


