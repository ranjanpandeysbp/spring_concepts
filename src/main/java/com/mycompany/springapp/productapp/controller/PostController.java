package com.mycompany.springapp.productapp.controller;

import com.mycompany.springapp.productapp.model.PostModel;
import com.mycompany.springapp.productapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService ps;

    @PostMapping("/posts")
    public ResponseEntity<PostModel> createPost(@RequestBody PostModel postModel)
    {
        postModel = ps.createPost(postModel);
        ResponseEntity<PostModel> res = new ResponseEntity<>(postModel, HttpStatus.CREATED);
        return res;
    }

    @GetMapping("/posts")
    public ResponseEntity<Iterable<PostModel>> findAllPosts()
    {
        Iterable<PostModel> posts = ps.findAllPosts();
        ResponseEntity<Iterable<PostModel>> res = new ResponseEntity<>(posts, HttpStatus.OK);
        return res;
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<PostModel> deletePost(@PathVariable("id") Long id)
    {
        PostModel postModel = ps.deletePost(id);
        ResponseEntity<PostModel> res = new ResponseEntity<>(postModel, HttpStatus.NO_CONTENT);
        return res;
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostModel> updatePost(@PathVariable("id") Long id,
                                                @RequestBody PostModel postModel)
    {
        PostModel postModel1 = ps.updatePost(id, postModel);
        ResponseEntity<PostModel> res = new ResponseEntity<>(postModel1, HttpStatus.OK);
        return res;
    }
}
