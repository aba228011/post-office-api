package com.dar.controller;

import com.dar.feign.ClientFeign;
import com.dar.feign.PostFeign;
import com.dar.model.ClientResponse;
import com.dar.model.PostDetailInfo;
import com.dar.model.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post-office")
public class PostController {

    @Autowired
    PostFeign postFeign;

    @Autowired
    ClientFeign clientFeign;

    @Autowired
    Environment env;

    @GetMapping("/check")
    public String check() {
        return "post-office-api is working "  + env.getProperty("local.server.port");
    }

//    POST
    @GetMapping("/post/check")
    public String checkPostFeign() {
        return postFeign.checkPost();
    }

    @GetMapping("/post/all")
    public List<PostResponse> getAllPostsFeign() {
        return postFeign.getAllPosts();
    }

    @GetMapping("/post/{postId}")
    public PostResponse getPostByIdFeign(@PathVariable String postId) {
        return postFeign.getPostById(postId);
    }

//  ---
    @GetMapping("/post-detail/{postId}")
    public PostDetailInfo getPostDetails(@PathVariable String postId) {
        PostResponse sendPost = postFeign.getPostById(postId);
        System.out.println(sendPost);
        ClientResponse sendClient = clientFeign.getClientById(sendPost.getClientId());
        ClientResponse sendReceiver = clientFeign.getClientById(sendPost.getPostRecipientId());

        return new PostDetailInfo(postId, sendClient, sendReceiver, sendPost.getPostItem(), sendPost.getStatus());
    }
}
