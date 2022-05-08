package com.mycompany.springapp.productapp.controller;

import com.mycompany.springapp.productapp.model.PostModel;
import com.mycompany.springapp.productapp.model.TagModel;
import com.mycompany.springapp.productapp.repository.PostRepository;
import com.mycompany.springapp.productapp.repository.TagRepository;
import com.mycompany.springapp.productapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    private TagService ts;

    @Autowired
    private PostRepository pr;

    @PostMapping("/tags")
    public ResponseEntity<TagModel> createTag(@RequestBody TagModel tagModel)
    {
        tagModel = ts.createTag(tagModel);
        ResponseEntity<TagModel> res = new ResponseEntity<>(tagModel, HttpStatus.CREATED);
        return res;
    }

    @GetMapping("/tags")
    public ResponseEntity<Iterable<TagModel>> getAllTags()
    {
        Iterable<TagModel> tags = ts.getAllTags();
        ResponseEntity<Iterable<TagModel>> res = new ResponseEntity<>(tags, HttpStatus.OK);
        return res;
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<TagModel> deleteTag(@PathVariable("id") Long id)
    {
        TagModel tagModel = ts.deleteTag(id);
        ResponseEntity<TagModel> res = new ResponseEntity<>(tagModel, HttpStatus.NO_CONTENT);
        return res;
    }

    @PostMapping("/tags/posts")
    public ResponseEntity<List<PostModel>> getAllPostsByTag(@RequestBody Set<TagModel> tagModels)
    //public ResponseEntity<List<PostModel>> getAllPostsByTag(@RequestBody Set<Long> tagIds)
    {
        System.out.println(pr.countDistinctByTagsIn(tagModels));
        //List<PostModel> postModelList = pr.findAllPostByTagName(tagIds);
        List<PostModel> postModelList = pr.findDistinctByTagsIn(tagModels);
        //List<PostModel> postModelList = pr.findAllPostByTagName(tagIds);
        ResponseEntity<List<PostModel>> res = new ResponseEntity<>(postModelList, HttpStatus.OK);
        return res;
    }
}
