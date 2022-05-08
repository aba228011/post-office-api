package com.dar.controller;

import com.dar.feign.ClientFeign;
import com.dar.feign.ClientPaymentFeign;
import com.dar.model.*;
import com.dar.repository.ClientRepository;
import com.dar.repository.PaymentRepository;
import com.dar.repository.ServiceTypeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client-office")
public class ClientController {

    @Autowired
    ClientFeign clientFeign;

    @Autowired
    ClientPaymentFeign clientPaymentFeign;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    Environment env;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping("/check")
    public String check() {
        return "client-office-api is working " + env.getProperty("local.server.port");
    }

    //    CLIENT
    @GetMapping("/client/check")
    public String checkClientFeign() {
        return clientFeign.checkClient();
    }

    @PostMapping("/client")
    public ClientResponse createClientFeign(@RequestBody ClientRequest clientRequest, @RequestParam String clientId) {
        return clientFeign.createClient(clientRequest, clientId);
    }

    @GetMapping("/client/all")
    public List<ClientResponse> getAllClientsFeign() {
        return clientFeign.getAllClients();
    }

    @GetMapping("/client")
    public ClientResponse getClientByIdFeign(@RequestParam String clientId) {
        return clientFeign.getClientById(clientId);
    }

    //  client payment

    @GetMapping("/payment/check")
    public String checkClientPaymentFeign() {
        return clientPaymentFeign.checkPayment();
    }

    @PostMapping("/payment")
    public PaymentResponse createPaymentFeign(@RequestBody PaymentRequest paymentRequest) {
        return clientPaymentFeign.createPayment(paymentRequest);
    }

    @GetMapping("/payment/all")
    public List<PaymentResponse> getAllClientPaymentsFeign() {
        return clientPaymentFeign.getAllPayments();
    }

    @GetMapping("/payment")
    public List<PaymentResponse> getClientPaymentByClientIdFeign(@RequestParam String clientId) {
        return paymentRepository.getPaymentEntitiesByClientId(clientId).stream()
                .map(el -> modelMapper.map(el, PaymentResponse.class)).collect(Collectors.toList());
    }

    @GetMapping("/payment/detail")
    public PaymentDetailInfo getPaymentDetailInfo(@RequestParam String paymentId) {
        PaymentResponse paymentResponse = modelMapper.map(paymentRepository.getPaymentEntityByPaymentId(paymentId), PaymentResponse.class);

        String serviceTypes = paymentResponse.getTypeOfServices();
        List<String> kindOfServices = Arrays.stream(serviceTypes.split(","))
                .map(service -> modelMapper.map(serviceTypeRepository.getServiceTypeEntityByCode(service).getName_ru(), String.class))
                .collect(Collectors.toList());

        ClientResponse clientInfo = modelMapper.map(clientRepository.getClientEntityByClientId(paymentResponse.getClientId()), ClientResponse.class);

        return new PaymentDetailInfo(paymentResponse.getPaymentId(),
                clientInfo, paymentResponse.getPaymentDate(),
                paymentResponse.getPaymentAmount(),
                kindOfServices);
    }
}
