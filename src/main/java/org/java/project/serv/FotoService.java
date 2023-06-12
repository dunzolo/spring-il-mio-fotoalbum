package org.java.project.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.project.pojo.Foto;
import org.java.project.repo.FotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class FotoService {
	@Autowired
	private FotoRepo fotoRepo;
	
	public List<Foto> findAll() {
		
		return fotoRepo.findAll();
	}
	public Foto save(Foto foto) {
		
		return fotoRepo.save(foto);
	}
	public Optional<Foto> findById(int id) {
		
		return fotoRepo.findById(id);
	}
	public List<Foto> findByTitolo(String titolo) {
		
		return fotoRepo.findByTitoloContaining(titolo);
	}
	public void delete(Foto foto) {
		
		fotoRepo.delete(foto);
	}
	@Transactional
	public Optional<Foto> findByIdWithCategories(int id) {
		
		Optional<Foto> optFoto = fotoRepo.findById(id);
		Hibernate.initialize(optFoto.get().getCategorie());
		
		return optFoto;
	}
}
