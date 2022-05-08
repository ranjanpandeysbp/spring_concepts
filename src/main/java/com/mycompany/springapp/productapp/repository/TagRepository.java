package com.mycompany.springapp.productapp.repository;

import com.mycompany.springapp.productapp.model.PostModel;
import com.mycompany.springapp.productapp.model.TagModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends CrudRepository<TagModel, Long> {
}
