/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.tablon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anuncios")
public class AnuncioRestController {

    @Autowired
    private AnuncioRepository anuncioRepository;

    // Consulta de todos los anuncios
    @GetMapping
    public List<Anuncio> obtenerTodos() {
        return anuncioRepository.findAll();
    }

    // Consulta de un anuncio en base a su ID
    @GetMapping("/{id}")
    public Anuncio obtenerPorId(@PathVariable Long id) {
        return anuncioRepository.findById(id).orElse(null);
    }

    // Creación de un anuncio
    @PostMapping
    public Anuncio crearAnuncio(@RequestBody Anuncio anuncio) {
        return anuncioRepository.save(anuncio);
    }
}