package com.dar.feign;

import com.dar.model.PaymentRequest;
import com.dar.model.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "client-payment-api")
public interface ClientPaymentFeign {

    @GetMapping("/payment/check")
    String checkPayment();

    @PostMapping("/payment")
    PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping("/payment/all")
    List<PaymentResponse> getAllPayments();

    @GetMapping("/payment")
    List<PaymentResponse> getPaymentByClientId(@RequestParam String clientId);

    @GetMapping("/payment")
    PaymentResponse getPaymentByPaymentId(@RequestParam String paymentId);
}
