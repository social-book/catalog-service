package com.socialbook.catalogs.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "category_table")
@NamedQueries({
        @NamedQuery(name = "Category.getCategory",
                query = "SELECT category.categoryTitle FROM category_table category WHERE category.category_id = :category_id"),
        @NamedQuery(name = "Category.getAll",
                query = "SELECT category FROM category_table category")
})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer category_id;

    @Column(name = "category_title")
    private String categoryTitle;

    @OneToMany
    @JoinColumn(name = "album_id")
    private List<Album> albums;

    public Integer getId() {
        return category_id;
    }

    public void setId(Integer id) {
        this.category_id = id;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
