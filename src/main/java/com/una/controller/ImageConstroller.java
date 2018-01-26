package com.una.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.una.model.Image;
import com.una.repository.BoloRepository;
import com.una.repository.ImageRepository;


@Controller
@RequestMapping("/image")
public class ImageConstroller {
	final Path rootDir;
	final ImageRepository imageRepository;
	//private BoloRepository boloRepository;

	@Autowired
	public ImageConstroller(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	//	 this.boloRepository = boloRepository;
		this.rootDir = Paths.get(System.getProperty("user.dir") + "/image/");
	}

	@GetMapping("")
	public String form(Model model) {
		model.addAttribute("files", imageRepository.findAll());
	//	model.addAttribute("bolos", boloRepository.findAll());
		return "image/formulario";
	}
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException {

		Path filePath = rootDir.resolve(file.getOriginalFilename());
		try {
			Files.copy(file.getInputStream(), filePath);
			imageRepository.save(new Image(file.getOriginalFilename(), filePath.toString()));
		
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded " + file.getOriginalFilename() + "!");
		} catch (FileAlreadyExistsException e) {
			redirectAttributes.addFlashAttribute("message",
					"Erro arquivo já existe !!!" + file.getOriginalFilename() + "!");
		}
		return "redirect:/image";
	}

	@GetMapping("/imagens")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@RequestParam("path") String filename) throws MalformedURLException {
		UrlResource file = new UrlResource(rootDir.resolve(filename).toUri());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("/excluir/{nome:.+}")
	public String excluir(@PathVariable("nome") String nome) {

		File file = new File(System.getProperty("user.dir") + "/Image/" + nome);

		if(file.delete()) {
			System.out.println("apagado");
			Image image = imageRepository.findByName(nome);
			imageRepository.delete(image);
		}
		return "redirect:/image";
	}


}
