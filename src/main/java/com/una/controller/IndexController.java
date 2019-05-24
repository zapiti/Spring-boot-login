package com.una.controller;

import com.una.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.una.model.User;
import com.una.repository.BoloRepository;
import com.una.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private UserService userService;
    private BoloRepository boloRepository;
	
	//@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public String list() {
        return "redirect:/bolo";
    }
	@RequestMapping(value="/teste", method = RequestMethod.GET)
    public String test(){
		
		return "test/test";
	}
    	
    	
    
}
