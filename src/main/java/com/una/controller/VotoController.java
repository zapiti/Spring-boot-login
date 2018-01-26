package com.una.controller;



import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.una.model.Bolo;
import com.una.model.Voto;
import com.una.repository.BoloRepository;
import com.una.repository.VotoRepository;

@Controller
@RequestMapping("/voto")
public class VotoController {
	 	private BoloRepository boloRepository;
	    private VotoRepository votoRepository;
	    
	    public VotoController(BoloRepository boloRepository,VotoRepository votoRepository ) {
	        this.boloRepository = boloRepository;
	        this.votoRepository = votoRepository;
	        
	    }

	    @GetMapping
	    public String list(Model model) {
	        model.addAttribute("voto", new Voto());
	        model.addAttribute("bolos", boloRepository.findAll());
	        return "voto/formulario";
	    }

	   


	    @PostMapping("/salvar")
	    public String salvar(@Valid Voto voto, BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	        //	model.addAttribute("bolos", boloRepository.findAll());
	            return "voto/formulario";
	        }
	        votoRepository.save(voto);
	        return "redirect:/bolo";
	    }

	    
	    @ModelAttribute("bolos")
	    public Iterable<Bolo> getBolo(){
	    	return boloRepository.findAll();
	    }
    
    
}
