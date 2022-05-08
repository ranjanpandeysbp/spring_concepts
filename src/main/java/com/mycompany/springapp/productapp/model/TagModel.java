package com.mycompany.springapp.productapp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "TAG_TABLE")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TagModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TAG_NAME")
    private String name;

    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //To avoid circular referencing while deserialization
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL},mappedBy = "tags")
    private Set<PostModel> posts = new HashSet<>();

    public TagModel() {
    }

    public TagModel(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostModel> posts) {
        this.posts = posts;
    }
}
