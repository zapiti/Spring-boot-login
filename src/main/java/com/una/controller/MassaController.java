package com.una.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.una.model.Bolo;
import com.una.model.Massa;
import com.una.repository.MassaRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/massa")
public class MassaController {
    private MassaRepository massaRepository;

    public MassaController(MassaRepository massaRepository) {
        this.massaRepository = massaRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("massas", massaRepository.findAll());
        return "massa/listar";
    }

    @GetMapping("/editar")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("massa", massaRepository.findOne(id));
        return "massa/formulario";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("massa", new Massa());
        return "massa/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Massa massa, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "massa/formulario";
        }
        massaRepository.save(massa);
        return "redirect:/massa";
    }

    @GetMapping("/excluir")
    public String excluir(Model model, @RequestParam Long id) {
        massaRepository.delete(id);
        return "redirect:/massa";
    }
    
    @GetMapping("/buscar")
    public String buscar(Model model, @RequestParam String cor) {
        model.addAttribute("massa", new Massa());
        model.addAttribute("massas",	massaRepository.findByCorLike("%" + cor + '%'));
        return "massa/listar";
    }
    
}
