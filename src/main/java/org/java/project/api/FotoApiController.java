package org.java.project.api;

import java.util.List;
import java.util.stream.Collectors;

import org.java.project.pojo.Foto;
import org.java.project.pojo.Messaggio;
import org.java.project.serv.FotoService;
import org.java.project.serv.MessaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class FotoApiController {
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private MessaggioService messaggioService;
	
	@GetMapping("/foto")
	public ResponseEntity<List<Foto>> getFoto() {
		
		List<Foto> fotoList = fotoService.findAll().stream()
				.filter(image -> image.getVisibile())
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(fotoList, HttpStatus.OK);
	}
	
	@GetMapping("/ricerca")
	public ResponseEntity<List<Foto>> getFotoByTitolo(
			@RequestParam(required = false) String titolo
		) {
		
		List<Foto> fotoList = fotoService.findByTitolo(titolo);
		
		return new ResponseEntity<>(fotoList, HttpStatus.OK);
	}
	
	@PostMapping("/messaggio")
	public ResponseEntity<Messaggio> getMessaggio(
			@RequestBody Messaggio messaggio) {
		
		messaggio = messaggioService.save(messaggio);
		
		return new ResponseEntity<>(messaggio, HttpStatus.OK);
	}
}
