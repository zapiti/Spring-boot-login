package com.una.controller;



import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.una.model.Bolo;
import com.una.model.Encomenda;
import com.una.repository.BoloRepository;
import com.una.repository.EncomendaRepository;
import com.una.repository.MassaRepository;

@Controller
@RequestMapping("/encomenda")
public class EncomendaController {
	 	private BoloRepository boloRepository;
	 	
	    private EncomendaRepository encomendaRepository;
	    
	   

	    public EncomendaController(BoloRepository boloRepository, 
				EncomendaRepository encomendaRepository) {
		
			this.boloRepository = boloRepository;
			
			this.encomendaRepository = encomendaRepository;
		}
		@GetMapping
	    public String list(Model model) {
	        model.addAttribute("encomenda", new Encomenda());
	        model.addAttribute("bolos", boloRepository.findAll());
	        return "encomenda/formulario";
	    }
	    @GetMapping("/lista")
	    public String listar(Model model) {
	        model.addAttribute("encomendas", encomendaRepository.findAll());
	        return "encomenda/listar";
	    }

	   


	    @PostMapping("/salvar")
	    public String salvar(@Valid Encomenda encomenda, BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	        //	model.addAttribute("bolos", boloRepository.findAll());
	            return "/encomenda/formulario";
	        }
	        encomendaRepository.save(encomenda);
	        return "redirect:/bolo";
	    }

	    
	    @ModelAttribute("bolos")
	    public Iterable<Bolo> getBolo(){
	    	return boloRepository.findAll();
	    }
	    
	    
	    @GetMapping("/editar")
	    public String edit(Model model, @RequestParam Long id) {
	        model.addAttribute("encomenda", encomendaRepository.findOne(id));
	        model.addAttribute("bolos", boloRepository.findAll());
	
	        return "encomenda/formulario";
	    }
	    
	    @GetMapping("/excluir")
	    public String excluir(Model model, @RequestParam Long id) {
	    	 encomendaRepository.delete(id);
	        return "redirect:/";
	    }
	    
	    @RequestMapping(value = "/relatorio", method = RequestMethod.GET)
	     public String relatorio(Model model, @RequestParam(defaultValue = "pdf") String format, HttpServletResponse response) {
	         model.addAttribute("datasource", encomendaRepository.findAll());
	         
	         model.addAttribute("format", format);
	         return "reports/encomendas";
	    }
	    
	    @GetMapping("/buscar")
	    public String buscar(Model model, @RequestParam Double valor) {
	        model.addAttribute("encomenda", new Bolo());
	        model.addAttribute("encomendas",encomendaRepository.findByValorLike(valor));
	        return "encomenda/listar";
	    }
	    
	    
	    
    
}
