package com.javainuse.springbootsecurity.controller;

import com.javainuse.springbootsecurity.config.JwtUtil;
import com.javainuse.springbootsecurity.model.entities.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class ResourceController {

    //    String purchaseService ="http://purchase-consumer:3333";
    String purchaseService = "http://purchase-consumer";

    @Autowired
    JwtUtil jwtUtil;


    @Autowired
    private RestTemplate restTemplate;
    // TODO: 12/08/2021 crear constantes

    @RequestMapping("/hello_user")
    public String getUser() {
        return "Hello User";
    }

    @RequestMapping("/hello_admin")
    public String getAdmin() {
        return "Hello Admin";
    }


    // TODO: 18/08/2021 Crear endpoints contra eureka para comunicar los diferentes servicios
    // Al llamar a estos sevicios funcionara a modo de gateway permitiendo que entren unas paginas o no.
    // Al pasar por el filtro, se realizara una llamada contra el servicio necesario.
    //	Ejemplo pseudocodigo
    //
    @RequestMapping(value = "/purchase-ticket", method = RequestMethod.POST)
    public ResponseEntity<Object> buyTicket(String token, @RequestBody Ticket ticket) {
        log.info("Call TO: PurchaseService.buyTicket");

        ResponseEntity response;
        // TODO: 19/08/2021 deberiamos meterlo en un tryCatch
        ticket = restTemplate.postForObject(purchaseService + "/purchase-ticket", ticket, Ticket.class);
        // TODO: 20/08/2021 Modificar para recibir objeto
        response = restTemplate.postForObject(purchaseService + "/purchase-ticket", ticket, ResponseEntity.class);


        return response;
    }
}
