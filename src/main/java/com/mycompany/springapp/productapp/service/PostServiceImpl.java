package com.mycompany.springapp.productapp.service;

import com.mycompany.springapp.productapp.model.CommentModelNew;
import com.mycompany.springapp.productapp.model.PostModelNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostServiceImpl implements PostServiceNew {

    @Value("${external.api.url}")
    private String postApiBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    //@Cacheable
    public PostModelNew[] getAllPosts(){
        PostModelNew[] result = restTemplate.getForObject(postApiBaseUrl+"/posts", PostModelNew[].class);
        System.out.println(result);

        return result;
    }
    /*
    @Override
    public List<PostModelNew> getAllPosts(){
        List<PostModelNew> result = restTemplate.getForObject(postApiBaseUrl+"/posts", ArrayList.class);
        System.out.println(result);

        return result;
    }
    */
    @Override
    @Cacheable(cacheNames = "allCommentsCache", key = "#postId")
    public CommentModelNew[] getAllCommentsForAPost(Long postId) {
        CommentModelNew[] result = restTemplate.getForObject(postApiBaseUrl+"/posts/"+postId+"/comments", CommentModelNew[].class);
        //https://jsonplaceholder.typicode.com/posts/1/comments
        return result;
    }

    @Override
    public PostModelNew createPost(PostModelNew postModelNew) {

        PostModelNew model = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PostModelNew> httpEntity = new HttpEntity<>(postModelNew, headers);
        ResponseEntity<PostModelNew> newPostEntity = restTemplate.postForEntity(postApiBaseUrl+"/posts", httpEntity, PostModelNew.class);
        if(newPostEntity.getStatusCode() == HttpStatus.CREATED) {
            model = newPostEntity.getBody();
        }
        return model;
    }

    @Override
    public PostModelNew updatePost(PostModelNew postModelNew, Long id) {

        PostModelNew model = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PostModelNew> httpEntity = new HttpEntity<>(postModelNew, headers);
        ResponseEntity<PostModelNew> upPostEntity = restTemplate.exchange(postApiBaseUrl+"/posts/{id}", HttpMethod.PUT, httpEntity, PostModelNew.class, id);
        if(upPostEntity.getStatusCode() == HttpStatus.OK) {
            model = upPostEntity.getBody();
        }
        return model;
    }

    @Override
    public void deletePost(Long id) {

        restTemplate.delete(postApiBaseUrl+"/posts/{id}", id);
    }
}
