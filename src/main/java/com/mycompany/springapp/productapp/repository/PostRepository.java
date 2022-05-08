package com.mycompany.springapp.productapp.repository;

import com.mycompany.springapp.productapp.model.PostModel;
import com.mycompany.springapp.productapp.model.TagModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends CrudRepository<PostModel, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM POST_TABLE pm, post_tags pt, TAG_TABLE tm WHERE pm.ID = pt.POST_ID AND tm.ID = pt.TAG_ID AND tm.ID IN (?1)")
    List<PostModel> findAllPostByTagName(Set<Long> tagIds);

    List<PostModel> findDistinctByTagsIn(Set<TagModel> tags);

    Long countDistinctByTagsIn(Set<TagModel> tags);
}
