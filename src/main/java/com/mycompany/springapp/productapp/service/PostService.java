package com.mycompany.springapp.productapp.service;

import com.mycompany.springapp.productapp.model.PostModel;
import com.mycompany.springapp.productapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository pr;

    public PostModel createPost(PostModel postModel)
    {
        postModel = pr.save(postModel);
        return postModel;
    }

    public Iterable<PostModel> findAllPosts()
    {
        Iterable<PostModel> posts = pr.findAll();
        return posts;
    }

    public PostModel deletePost(Long id)
    {
        PostModel postModel = null;
        Optional<PostModel> optPostModel = pr.findById(id);
        if(optPostModel.isPresent())
        {
            PostModel post = optPostModel.get();
            postModel = post;
            pr.delete(post);
        }
        else
        {
            System.out.println("No matching post found");
        }
        return postModel;
    }
    public PostModel updatePost(Long id, PostModel postModel)
    {
        pr.findById(id);
        return null;
    }
}
