package com.dar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {
    private String paymentId;

    private String clientId;

    private Date paymentDate;

    private double paymentAmount;

    private String typeOfServices;
}
