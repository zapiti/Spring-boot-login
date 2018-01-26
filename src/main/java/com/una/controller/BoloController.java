package com.una.controller;


import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.una.model.Bolo;
import com.una.repository.BoloRepository;
import com.una.repository.CoberturaRepository;
import com.una.repository.MassaRepository;
import com.una.repository.SaborRepository;
import com.una.repository.TemaRepository;


import javax.servlet.http.HttpServletResponse;
 import javax.validation.Valid;


@Controller
@RequestMapping(value={"/bolo"})

public class BoloController {
    private BoloRepository boloRepository;
    private MassaRepository massaRepository;
    private TemaRepository temaRepository;
    private CoberturaRepository coberturaRepository;
    private SaborRepository saborRepository;
    

    public BoloController(BoloRepository boloRepository, MassaRepository massaRepository,CoberturaRepository coberturaRepository,TemaRepository temaRepository, SaborRepository saborRepository) {
        this.boloRepository = boloRepository;
        this.massaRepository = massaRepository;
        this.temaRepository = temaRepository;
        this.coberturaRepository = coberturaRepository;
        this.saborRepository = saborRepository;
    }

    @GetMapping
    public String list(Model model,@PageableDefault(size = 5) Pageable pageable) {
        model.addAttribute("bolos", boloRepository.findAll(pageable));
        return "bolo/listar";
    }

    @GetMapping("/editar")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("bolo", boloRepository.findOne(id));
        model.addAttribute("massas", massaRepository.findAll());
        model.addAttribute("temas", temaRepository.findAll());
        model.addAttribute("coberturas", coberturaRepository.findAll());
        model.addAttribute("sabores", saborRepository.findAll());
        return "bolo/formulario";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("bolo", new Bolo());
        model.addAttribute("massas", massaRepository.findAll());
        model.addAttribute("temas", temaRepository.findAll());
        model.addAttribute("coberturas", coberturaRepository.findAll());
        model.addAttribute("sabores", saborRepository.findAll());
        return "bolo/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Bolo bolo, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
        	  model.addAttribute("massas", massaRepository.findAll());
              model.addAttribute("temas", temaRepository.findAll());
              model.addAttribute("coberturas", coberturaRepository.findAll());
              model.addAttribute("sabores", saborRepository.findAll());
            return "bolo/formulario";
        }
        boloRepository.save(bolo);
        return "redirect:/bolo";
    }
    
    @RequestMapping(value = "/relatorio", method = RequestMethod.GET)
     public String relatorio(Model model, @RequestParam(defaultValue = "pdf") String format, HttpServletResponse response) {
         model.addAttribute("datasource", boloRepository.findAll());
         model.addAttribute("format", format);
         return "reports/bolos";
    }
    
    @GetMapping("/excluir")
    public String excluir(Model model, @RequestParam Long id) {
    	boloRepository.delete(id);
        return "redirect:/bolo";
    }

    
    @GetMapping("/buscar")
    public String buscar(Model model, @RequestParam String nome,@PageableDefault(size = 5) Pageable pageable) {
        model.addAttribute("bolo", new Bolo());
        model.addAttribute("bolos",	boloRepository.findByNomeLike("%" + nome + "%",pageable));
        return "bolo/listar";
    }

	@GetMapping("/encomenda")
	public String encomenda(Model model, @RequestParam String nome,@PageableDefault(size = 5) Pageable pageable) {
	    model.addAttribute("bolos", boloRepository.findByNomeLike(nome, pageable));
	  
	    return "encomenda/formulario";
	}
	
   
    

    

}
