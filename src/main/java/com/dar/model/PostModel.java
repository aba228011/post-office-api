package com.dar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostModel {
    String postId;
    @NotNull(message = "clientId can`t be null")
    String clientId;
    @NotNull(message = "postRecipientId can`t be null")
    String postRecipientId;
    String postItem;
    String status;
}
