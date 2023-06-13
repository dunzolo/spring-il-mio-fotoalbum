package org.java.project.contr;

import java.util.List;
import java.util.Optional;

import org.java.project.auth.pojo.User;
import org.java.project.auth.serv.UserService;
import org.java.project.pojo.Categoria;
import org.java.project.pojo.Foto;
import org.java.project.serv.CategoriaService;
import org.java.project.serv.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class FotoController {
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String getFoto(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        
        Optional<User> optUser = userService.findByUsername(userName);
		User user = optUser.get();
		
		if(authentication.getAuthorities().stream()
	            .anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
			List<Foto> fotoList = user.getFoto();
	    	model.addAttribute("fotoList", fotoList);
		}
		else {
			List<Foto> fotoList = fotoService.findAll();
			model.addAttribute("fotoList", fotoList);
			
		}
		
		return "foto-index";
	}
	
	@PostMapping("/foto/titolo")
	public String getBookByTitle(Model model, @RequestParam(required = false) String titolo) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        
        if(authentication.getAuthorities().stream()
	            .anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
        	
			List<Foto> fotoList = fotoService.findByTitoloAndUserId(titolo, user.getId());
	    	model.addAttribute("fotoList", fotoList);
		}
		else {
			List<Foto> fotoList = fotoService.findByTitolo(titolo);
			model.addAttribute("fotoList", fotoList);
			
		}
		
		return "foto-index";
	}
	
	@GetMapping("/foto/{id}")
	public String getSingleFoto(Model model, @PathVariable("id") int id) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		
		model.addAttribute("foto", foto);
		
		return "foto-show";
	}
	
	@GetMapping("/foto/create")
	public String createFoto(Model model) {
		
		List<Categoria> categorie = categoriaService.findAll();
		
		model.addAttribute("categorie", categorie);
		
		model.addAttribute("foto", new Foto());
		
		return "foto-create";
	}
	
	@PostMapping("/foto/create")
	public String storeFoto(Model model, @Valid @ModelAttribute Foto foto,
			BindingResult bindingResult) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userService.findByUsername(userName).get();
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("foto", foto);
			model.addAttribute("errors", bindingResult);
			List<Categoria> categorie = categoriaService.findAll();
			model.addAttribute("categorie", categorie);
			
			return "foto-create";
		}
		
		foto.setUser(user);
		
		fotoService.save(foto);
		
		return "redirect:/";
	}
	
	@GetMapping("/foto/edit/{id}")
	public String editFoto(Model model, @PathVariable int id
		) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		
		List<Categoria> categorie = categoriaService.findAll();
		
		List<User> users = userService.findAll();
		
		model.addAttribute("foto", foto);
		model.addAttribute("categorie", categorie);
		model.addAttribute("users", users);
		
		return "foto-edit";
	}
	
	
	@PostMapping("/foto/edit/{id}")
	public String editFoto(Model model, @PathVariable int id,
			@Valid @ModelAttribute Foto foto, BindingResult bindingResult
		) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("foto", foto);
			model.addAttribute("errors", bindingResult);
			List<Categoria> categorie = categoriaService.findAll();
			model.addAttribute("categorie", categorie);
			List<User> users = userService.findAll();
			model.addAttribute("users", users);
			
			return "foto-edit";
		}
		
		fotoService.save(foto);
		
		return "redirect:/";
	}
	
	@GetMapping("/foto/delete/{id}")
	public String deleteFoto(@PathVariable int id
		) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		fotoService.delete(foto);
		
		return "redirect:/";
	}
}
