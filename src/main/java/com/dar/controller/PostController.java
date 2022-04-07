package com.dar.controller;

import com.dar.feign.ClientFeign;
import com.dar.feign.PostFeign;
import com.dar.model.ClientModel;
import com.dar.model.PostDetailInfo;
import com.dar.model.PostModel;
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
    public List<PostModel> getAllPostsFeign() {
        return postFeign.getAllPosts();
    }

    @GetMapping("/post/{postId}")
    public PostModel getPostByIdFeign(@PathVariable String postId) {
        return postFeign.getPostById(postId);
    }

//    CLIENT
    @GetMapping("/client/check")
    public String checkClientFeign() {
        return clientFeign.checkClient();
    }

    @GetMapping("/client/all")
    public List<ClientModel> getAllClientsFeign() {
        return clientFeign.getAllClients();
    }

    @GetMapping("/client/{clientId}")
    public ClientModel getClientByIdFeign(@PathVariable String clientId) {
        return clientFeign.getClientById(clientId);
    }

//  ---
    @GetMapping("/post-detail/{postId}")
    public PostDetailInfo getPostDetails(@PathVariable String postId) {
        PostModel sendPost = postFeign.getPostById(postId);
        System.out.println(sendPost);
        ClientModel sendClient = clientFeign.getClientById(sendPost.getClientId());
        ClientModel sendReceiver = clientFeign.getClientById(sendPost.getPostRecipientId());

        return new PostDetailInfo(postId, sendClient, sendReceiver, sendPost.getPostItem(), sendPost.getStatus());
    }
}
