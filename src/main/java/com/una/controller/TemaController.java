package com.una.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.una.model.Bolo;
import com.una.model.Tema;
import com.una.repository.TemaRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/tema")
public class TemaController {
    private TemaRepository temaRepository;

    public TemaController(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("temas", temaRepository.findAll());
        return "tema/listar";
    }

    @GetMapping("/editar")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("tema", temaRepository.findOne(id));
        return "tema/formulario";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tema", new Tema());
        return "tema/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Tema tema, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tema/formulario";
        }
        temaRepository.save(tema);
        return "redirect:/tema";
    }

    @GetMapping("/excluir")
    public String excluir(Model model, @RequestParam Long id) {
        temaRepository.delete(id);
        return "redirect:/tema";
    }
    @GetMapping("/buscar")
    public String buscar(Model model, @RequestParam String descricao) {
        model.addAttribute("tema", new Tema());
        model.addAttribute("temas",	temaRepository.findByDescricaoLike("%" + descricao + '%'));
        return "tema/listar";
    }
    
    
}
