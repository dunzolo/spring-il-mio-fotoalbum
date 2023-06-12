package org.java.project.repo;

import java.util.List;

import org.java.project.pojo.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepo extends JpaRepository<Foto, Integer>{
	public List<Foto> findByTitoloContaining(String titolo);
}
