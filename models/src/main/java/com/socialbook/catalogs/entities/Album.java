package com.socialbook.catalogs.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "album_table")
@NamedQueries(value = {
        @NamedQuery(name = "Album.getAll",
                query = "SELECT album FROM album_table album"),
        @NamedQuery(name = "Album.getUserAlbums",
                query = "SELECT album FROM album_table album WHERE album.albumUserReferenceId = :albumUserReferenceId")
})
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer album_id;

    @Column(name = "album_title")
    private String albumTitle;

    @Column(name = "album_user_referenceId")
    private String albumUserReferenceId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getAlbumUserReferenceId() {
        return albumUserReferenceId;
    }

    public void setAlbumUserReferenceId(String albumUserReferenceId) {
        this.albumUserReferenceId = albumUserReferenceId;
    }

    public Integer getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Integer album_id) {
        this.album_id = album_id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
