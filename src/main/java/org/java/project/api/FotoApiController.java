package org.java.project.api;

import java.util.List;

import org.java.project.pojo.Foto;
import org.java.project.serv.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class FotoApiController {
	@Autowired
	private FotoService fotoService;
	
	@GetMapping("/foto")
	public ResponseEntity<List<Foto>> getFoto() {
		
		List<Foto> fotoList = fotoService.findAll();
		
		return new ResponseEntity<>(fotoList, HttpStatus.OK);
	}
}
