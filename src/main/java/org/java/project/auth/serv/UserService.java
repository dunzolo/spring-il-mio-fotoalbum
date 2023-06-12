package org.java.project.auth.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.project.auth.pojo.User;
import org.java.project.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	public List<User> findAll() {
		
		return userRepo.findAll();
	}
	public Optional<User> findById(int id) {
		
		return userRepo.findById(id);
	}
	public User save(User user) {
		
		return userRepo.save(user);
	}
	
	@Transactional
	public Optional<User> findByUsername(String username) {
		Optional<User> optUser = userRepo.findByUsername(username);
		Hibernate.initialize(optUser.get().getFoto());
		return optUser;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if (userOpt.isEmpty()) throw new UsernameNotFoundException("Username not found");
		
		return userOpt.get();
	}
}
