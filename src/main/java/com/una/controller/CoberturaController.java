package com.una.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.una.model.Bolo;
import com.una.model.Cobertura;
import com.una.repository.CoberturaRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/cobertura")
public class CoberturaController {
    private CoberturaRepository coberturaRepository;

    public CoberturaController(CoberturaRepository coberturaRepository) {
        this.coberturaRepository = coberturaRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("coberturas", coberturaRepository.findAll());
        return "cobertura/listar";
    }

    @GetMapping("/editar")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("cobertura", coberturaRepository.findOne(id));
        return "cobertura/formulario";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("cobertura", new Cobertura());
        return "cobertura/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cobertura cobertura, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cobertura/formulario";
        }
        coberturaRepository.save(cobertura);
        return "redirect:/cobertura";
    }

    @GetMapping("/excluir")
    public String excluir(Model model, @RequestParam Long id) {
        coberturaRepository.delete(id);
        return "redirect:/cobertura";
    }
    @GetMapping("/buscar")
    public String buscar(Model model, @RequestParam String nome) {
        model.addAttribute("cobertura", new Bolo());
        model.addAttribute("coberturas",	coberturaRepository.findByNomeLike("%" + nome + '%'));
        return "cobertura/listar";
    }
    
    
}
