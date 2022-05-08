package com.dar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponse {
    String postId;
    String clientId;
    String postRecipientId;
    String postItem;
    String status;
}
