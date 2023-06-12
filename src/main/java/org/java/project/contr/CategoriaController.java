package org.java.project.contr;

import java.util.List;
import java.util.Optional;

import org.java.project.pojo.Categoria;
import org.java.project.pojo.Foto;
import org.java.project.serv.CategoriaService;
import org.java.project.serv.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private FotoService fotoService;
	
	@GetMapping("/categorie")
	public String getgetCategorie(Model model) {
		
		List<Categoria> categorie = categoriaService.findAll();
		
		model.addAttribute("categorie", categorie);
		
		return "categorie-index";
	}
	
	@GetMapping("/categorie/create")
	public String createCategoria(Model model) {
		
		model.addAttribute("categoria", new Categoria());
		
		return "categoria-create";
	}
	
	@PostMapping("/categorie/create")
	public String storeCategoria(Model model, @Valid @ModelAttribute Categoria categoria,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("categoria", categoria);
			model.addAttribute("errors", bindingResult);
			
			return "categoria-create";
		}
		
		categoriaService.save(categoria);
		
		return "redirect:/categorie";
	}
	
	@GetMapping("/categorie/edit/{id}")
	public String editCategoria(Model model, @PathVariable int id
		) {
		
		Optional<Categoria> optCategoria = categoriaService.findById(id);
		Categoria categoria = optCategoria.get();
		
		model.addAttribute("categoria", categoria);
		
		return "categoria-edit";
	}
	
	
	@PostMapping("/categorie/edit/{id}")
	public String editCategoria(Model model, @PathVariable int id,
			@Valid @ModelAttribute Categoria categoria, BindingResult bindingResult
		) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("categoria", categoria);
			model.addAttribute("errors", bindingResult);
			
			return "categoria-edit";
		}
		
		categoriaService.save(categoria);
		
		return "redirect:/categorie";
	}
	
	@GetMapping("/categorie/delete/{id}")
	public String deleteCategoria(@PathVariable int id
		) {
		
		Optional<Categoria> optCategoria = categoriaService.findById(id);
		Categoria categoria = optCategoria.get();
		
		for(Foto foto : categoria.getFoto()) {
			foto.removeCategoria(categoria);
			fotoService.save(foto);
		}
		
		categoriaService.save(categoria);
		
		return "redirect:/categorie";
	}
}
