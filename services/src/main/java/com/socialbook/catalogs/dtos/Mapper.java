package com.socialbook.catalogs.dtos;

import com.socialbook.catalogs.dtos.AlbumDto;
import com.socialbook.catalogs.dtos.CategoryDto;
import com.socialbook.catalogs.dtos.ImageDto;
import com.socialbook.catalogs.entities.Album;
import com.socialbook.catalogs.entities.Category;
import com.socialbook.catalogs.entities.Image;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static AlbumDto convertToDto(Album album) {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setId(album.getAlbum_id());
        albumDto.setTitle(album.getAlbumTitle());
        albumDto.setUserId(album.getAlbumUserReferenceId());
        albumDto.setCategory(convertToDto(album.getCategory()));
        albumDto.setImages(convertToImageDtos(album.getImages()));
        return albumDto;
    }

    public static List<AlbumDto> convertToAlbumDtos(List<Album> albums) {
        ArrayList<AlbumDto> albumDtos = new ArrayList<>();
        for (Album album : albums) {
            albumDtos.add(convertToDto(album));
        }
        return albumDtos;
    }

    public static ImageDto convertToDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setImageName(image.getImageName());
        imageDto.setImageSrc(image.getImageSrc());
        return imageDto;
    }

    public static List<ImageDto> convertToImageDtos(List<Image> images) {
        ArrayList<ImageDto> imageDtos = new ArrayList<>();
        for (Image image : images) {
            imageDtos.add(convertToDto(image));
        }
        return imageDtos;
    }

    public static CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setTitle(category.getCategoryTitle());
        categoryDto.setId(category.getId());
        return categoryDto;
    }

    public static List<CategoryDto> convertToCategoryDtos(List<Category> categories) {
        ArrayList<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryDtos.add(convertToDto(category));
        }
        return categoryDtos;
    }

    public static Category convertToDao(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryTitle(categoryDto.getTitle());
        category.setId(categoryDto.getId());
        return category;
    }

    public static List<Category> convertToCategoryDaos(List<CategoryDto> categoryDtos) {
        List<Category> categories = new ArrayList<>();
        for (CategoryDto categoryDto : categoryDtos) {
            categories.add(convertToDao(categoryDto));
        }
        return categories;
    }

    public static Album convertToDao(AlbumDto albumDto) {
        Album album = new Album();
        album.setCategory(convertToDao(albumDto.getCategory()));
        album.setAlbumTitle(albumDto.getTitle());
        album.setAlbumUserReferenceId(albumDto.getUserId());
        album.setImages(convertToImageDaos(albumDto.getImages()));
        return album;
    }

    public static List<Album> convertToAlbumDaos(List<AlbumDto> albumDtos) {
        List<Album> albums = new ArrayList<>();
        for (AlbumDto albumDto : albumDtos) {
            albums.add(convertToDao(albumDto));
        }
        return albums;
    }

    public static Image convertToDao(ImageDto imageDto) {
        Image image = new Image();
        image.setImageSrc(imageDto.getImageSrc());
        image.setImageName(imageDto.getImageName());
        return image;
    }

    public static List<Image> convertToImageDaos(List<ImageDto> imageDtos) {
        List<Image> images = new ArrayList<>();
        for (ImageDto imageDto : imageDtos) {
            images.add(convertToDao(imageDto));
        }
        return images;
    }
}
