package com.mycompany.springapp.productapp.controller;

import com.mycompany.springapp.productapp.model.CommentModelNew;
import com.mycompany.springapp.productapp.model.PostModelNew;
import com.mycompany.springapp.productapp.service.PostServiceNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PostControllerNew {

    @Autowired
    private PostServiceNew postServiceNew;

    @GetMapping("/posts")
    public PostModelNew[] getAllPosts(){
        PostModelNew[] response = postServiceNew.getAllPosts();
        return response;
    }
    /*
    @GetMapping("/posts")
    public List<PostModelNew> getAllPosts(){
        List<PostModelNew> response = postServiceNew.getAllPosts();
        return response;
    }
    */
    @GetMapping("/posts/{postId}/comments")
    public CommentModelNew[] getAllCommentsForAPost(@PathVariable("postId") Long postId){
        return postServiceNew.getAllCommentsForAPost(postId);
    }

    @PostMapping("/posts")
    public PostModelNew createPost(@RequestBody PostModelNew postModelNew){
        PostModelNew model = postServiceNew.createPost(postModelNew);
        return model;
    }

    @PutMapping("/posts/{postId}")
    public PostModelNew updatePost(@RequestBody PostModelNew postModelNew, @PathVariable("postId") Long postId){
        PostModelNew model = postServiceNew.updatePost(postModelNew, postId);
        return model;
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable("postId") Long postId){
        postServiceNew.deletePost(postId);
    }
}
