package com.safari.aukcija.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.safari.aukcija.service.KorisnikService;

import model.Korisnik;



@Service("customUserDetailsService")
public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Korisnik user = korisnikService.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User 404");
		}
		UserDetailsImpl userDetails =new UserDetailsImpl();
		userDetails.setUsername(user.getUsername());
		userDetails.setPassword(user.getPassword());
		userDetails.setUloga(user.getUloga());
		
		return userDetails;
	}

}
