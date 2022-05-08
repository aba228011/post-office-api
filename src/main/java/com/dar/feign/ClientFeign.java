package com.dar.feign;

import com.dar.model.ClientRequest;
import com.dar.model.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "client-core-api")
public interface ClientFeign {

    @GetMapping("/client/check")
    String checkClient();

    @PostMapping("/client")
    ClientResponse createClient(@RequestBody ClientRequest clientRequest, @RequestParam String clientId);

    @GetMapping("/client/all")
    List<ClientResponse> getAllClients();

    @GetMapping("/client")
    ClientResponse getClientById(@RequestParam String clientId);
}
