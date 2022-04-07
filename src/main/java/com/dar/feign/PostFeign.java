package com.dar.feign;

import com.dar.model.PostModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "post-core-api")
public interface PostFeign {

    @GetMapping("/post/check")
    String checkPost();

    @GetMapping("/post/all")
    List<PostModel> getAllPosts();

    @GetMapping("/post/{postId}")
    PostModel getPostById(@PathVariable String postId);
}
