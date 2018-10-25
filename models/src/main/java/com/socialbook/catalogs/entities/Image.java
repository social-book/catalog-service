package com.socialbook.catalogs.entities;


import javax.persistence.*;
import java.util.List;

@Entity(name = "image_table")
@NamedQueries(value = {
        @NamedQuery(name = "Image.getAll",
                query = "SELECT image from image_table image"),
        @NamedQuery(name = "Image.getImage",
                query = "SELECT image FROM image_table image WHERE image.image_id = :image_id")
})
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer image_id;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_src")
    private String imageSrc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    public Integer getId() {
        return image_id;
    }

    public void setId(Integer id) {
        this.image_id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
