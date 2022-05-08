package com.mycompany.springapp.productapp.service;

import com.mycompany.springapp.productapp.model.CommentModelNew;
import com.mycompany.springapp.productapp.model.PostModelNew;

import java.util.List;

public interface PostServiceNew {

    public PostModelNew[] getAllPosts();
    //public List<PostModelNew> getAllPosts();
    public CommentModelNew[] getAllCommentsForAPost(Long postId);

    public PostModelNew createPost(PostModelNew postModelNew);
    public PostModelNew updatePost(PostModelNew postModelNew, Long postId);
    public void deletePost(Long postId);
}
