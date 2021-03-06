package com.dar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDetailInfo {
    String postId;

    ClientResponse client;
    ClientResponse receiver;

    String postItem;
    String status;
}
