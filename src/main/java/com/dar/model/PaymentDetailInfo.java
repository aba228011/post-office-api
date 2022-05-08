package com.dar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDetailInfo {
    private String paymentId;

    private ClientResponse client;

    private Date paymentDate;

    private double paymentAmount;

    private List<String> kindOfServices;
}
