package com.una.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.una.model.Bolo;
import com.una.model.Sabor;
import com.una.repository.SaborRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/sabor")
public class SaborController {

    private SaborRepository saborRepository;

    public SaborController(SaborRepository saborRepository) {
        this.saborRepository = saborRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("sabores", saborRepository.findAll());
        return "sabor/listar";
    }

    @GetMapping("/editar")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("sabor", saborRepository.findOne(id));
        return "sabor/formulario";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("sabor", new Sabor());
        return "sabor/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Sabor sabor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sabor/formulario";
        }
        saborRepository.save(sabor);
        return "redirect:/sabor";
    }

    @GetMapping("/excluir")
    public String excluir(Model model, @RequestParam Long id) {
        saborRepository.delete(id);
        return "redirect:/sabor";
    }
    @GetMapping("/buscar")
    public String buscar(Model model, @RequestParam String nome) {
        model.addAttribute("sabor", new Sabor());
        model.addAttribute("sabores",	saborRepository.findByNomeLike("%" + nome + '%'));
        return "sabor/listar";
    }
    
    
}