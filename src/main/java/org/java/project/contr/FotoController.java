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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class FotoController {
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/")
	public String getFoto(Model model) {
		
		List<Foto> fotoList = fotoService.findAll();
		
		model.addAttribute("fotoList", fotoList);
		
		return "foto-index";
	}
	
	@PostMapping("/foto/titolo")
	public String getBookByTitle(Model model, @RequestParam(required = false) String titolo) {
		
		List<Foto> fotoList = fotoService.findByTitolo(titolo);
		
		model.addAttribute("fotoList", fotoList);
		model.addAttribute("titolo", titolo);
		
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
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("foto", foto);
			model.addAttribute("errors", bindingResult);
			List<Categoria> categorie = categoriaService.findAll();
			model.addAttribute("categorie", categorie);
			
			return "foto-create";
		}
		
		fotoService.save(foto);
		
		return "redirect:/";
	}
	
	@GetMapping("/foto/edit/{id}")
	public String editFoto(Model model, @PathVariable int id
		) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		
		List<Categoria> categorie = categoriaService.findAll();
		
		model.addAttribute("foto", foto);
		model.addAttribute("categorie", categorie);
		
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
			
			return "foto-edit";
		}
		
		fotoService.save(foto);
		
		return "redirect:/";
	}
	
	@GetMapping("/foto/delete/{id}")
	public String deletePizza(@PathVariable int id
		) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		fotoService.delete(foto);
		
		return "redirect:/";
	}
}