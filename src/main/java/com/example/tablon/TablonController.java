/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.tablon;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TablonController {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @GetMapping("/")
    public String mostrarAnuncios(Model model, HttpSession session) {
        if (session.getAttribute("mensajeBienvenida") == null) {
            model.addAttribute("mensajeBienvenida", "Bienvenidos!");
            session.setAttribute("mensajeBienvenida", true);
        }
        model.addAttribute("anuncios", anuncioRepository.findAll());
        return "index";
    }

    @GetMapping("/new")
    public String nuevoAnuncio(Model model, HttpSession session) {
        String nombreGuardado = (String) session.getAttribute("nombreUsuario");
        model.addAttribute("nombreUsuario", nombreGuardado != null ? nombreGuardado : "");
        return "new";
    }

    @PostMapping("/insert")
    public String insertarAnuncio(
        @RequestParam String nombre,
        @RequestParam String asunto,
        @RequestParam String comentario,
        HttpSession session,
        Model model
    ) {
        session.setAttribute("nombreUsuario", nombre);

        // Guardar el anuncio
        Anuncio anuncio = new Anuncio(nombre, asunto, comentario);
        anuncioRepository.save(anuncio);

        model.addAttribute("mensaje", "Anuncio Almacenado con Éxito!");
        return "insert";
    }

    @GetMapping("/show")
    public String mostrarDetalle(@RequestParam("numAnuncio") Long id, Model model) {
        Anuncio anuncio = anuncioRepository.findById(id).orElse(null);
        model.addAttribute("anuncio", anuncio);
        return "show";
    }
}