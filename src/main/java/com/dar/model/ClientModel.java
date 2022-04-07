package com.dar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientModel {
    String clientId;

    @NotNull(message = "FullName can`t be null")
    String fullName;

    @NotNull(message = "email address can`t be null")
    @Email
    String email;
}
