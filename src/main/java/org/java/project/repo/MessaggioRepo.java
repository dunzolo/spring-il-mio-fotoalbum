package org.java.project.repo;

import org.java.project.pojo.Messaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessaggioRepo extends JpaRepository<Messaggio, Integer> {
	
}
