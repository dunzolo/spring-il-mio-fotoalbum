package org.java.project.serv;

import org.java.project.pojo.Messaggio;
import org.java.project.repo.MessaggioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessaggioService {
	
	@Autowired
	private MessaggioRepo messaggioRepo;
	
	public Messaggio save(Messaggio messaggio) {
		
		return messaggioRepo.save(messaggio);
	}
}
