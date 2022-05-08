package com.mycompany.springapp.productapp.service;

import com.mycompany.springapp.productapp.model.TagModel;
import com.mycompany.springapp.productapp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tr;

    public TagModel createTag(TagModel tagModel)
    {
        tagModel = tr.save(tagModel);
        return tagModel;
    }

    public Iterable<TagModel> getAllTags()
    {
        Iterable<TagModel> tags = tr.findAll();
        return tags;
    }

    public TagModel deleteTag(Long id)
    {
        TagModel tagModel = null;
        Optional<TagModel> optTagModel = tr.findById(id);
        if(optTagModel.isPresent())
        {
            TagModel tag = optTagModel.get();
            tagModel = tag;
            tr.delete(tag);
        }
        else
        {
            System.out.println("No matching Tag found");
        }
        return tagModel;
    }
}
