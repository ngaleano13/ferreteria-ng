package com.ngaleano.ferreteria_ng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AuthController {
    

    @GetMapping("/")
    public String redireccionarPaginaPrincipal(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/buscarTicket")
    public String buscarTicket(){
        return "buscarTicket";
    }

}
