package com.ngaleano.ferreteria_ng.service;

import org.springframework.stereotype.Service;

import com.ngaleano.ferreteria_ng.model.Usuario;
import com.ngaleano.ferreteria_ng.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService {
    
    private UsuarioRepository usuarioRepository;


    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(
            () -> new RuntimeException("No se encontro el usuario con el id: "+ id));
    }


}
