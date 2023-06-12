package org.java.project;

import org.java.project.pojo.Categoria;
import org.java.project.pojo.Foto;
import org.java.project.serv.CategoriaService;
import org.java.project.serv.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FotoAlbumApplication implements CommandLineRunner {

	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	public static void main(String[] args) {
		SpringApplication.run(FotoAlbumApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria("campagna");
		Categoria c2 = new Categoria("montagna");
		Categoria c3 = new Categoria("mare");
		Categoria c4 = new Categoria("calcio");
		Categoria c5 = new Categoria("festa");
		
		categoriaService.save(c1);
		categoriaService.save(c2);
		categoriaService.save(c3);
		categoriaService.save(c4);
		categoriaService.save(c5);
		
		Foto f1 = new Foto("foto1", 
				"descrizione foto 1", 
				"https://cc-prod.scene7.com/is/image/CCProdAuthor/Night-Photography-pod-02-720x411?$pjpeg$&jpegSize=200&wid=720",
				true,
				c2);
		Foto f2 = new Foto("foto2", 
				"descrizione foto 2", 
				"https://marchecountryhomes.com/wp-content/uploads/2021/04/vivere-in-campagna-1.jpeg",
				true,
				c1);
		Foto f3 = new Foto("foto3", 
				"descrizione foto 3", 
				"https://thumbs.dreamstime.com/b/concetto-estivo-di-soccer-beach-kid-un-ragazzo-che-gioca-calcio-al-mare-durante-il-tramonto-turisti-o-allenamenti-la-gente-167549723.jpg",
				true,
				c3, c4);
		Foto f4 = new Foto("foto4", 
				"descrizione foto 4", 
				"https://www.touringclub.it/sites/default/files/styles/gallery_full/public/immagini_georiferite/gettyimages-649067370.jpg?itok=BgjbldQE",
				true,
				c3);
		Foto f5 = new Foto("foto5", 
				"descrizione foto 5", 
				"https://it.exaude.com/wp-content/uploads/2016/06/feste-sagre-italia-assurde-strane.jpg",
				true,
				c5);
		
		fotoService.save(f1);
		fotoService.save(f2);
		fotoService.save(f3);
		fotoService.save(f4);
		fotoService.save(f5);
	}

}
