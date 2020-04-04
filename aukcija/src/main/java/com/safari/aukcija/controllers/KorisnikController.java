package com.safari.aukcija.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

import com.safari.aukcija.service.KorisnikService;

import model.Korisnik;

@RestController
@EntityScan("Model")
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;
	
//	Korisnik ulogovan;
//	
//	@RequestMapping(value="/login", method={RequestMethod.POST,RequestMethod.GET}, produces = { "application/json", "application/xml" }) 
//	public Korisnik login(String username,String password,Model m,HttpServletRequest request){
//		ulogovan = korisnikRepository.login(username, password);
//		if(ulogovan != null){
//			request.getSession().setAttribute("ulogovan", ulogovan);
//		}else{
//			m.addAttribute("porukaLogin", "Uneli ste pogresne podatke");
//		}
//		
//		return ulogovan;
//	}
//	
//	@PostMapping(value="registracija", produces = { "application/json", "application/xml" })
//	public boolean registracija(Korisnik k ,HttpServletRequest request){
//		try{
//			k=korisnikService.addKorisnik(k);
//			if(k != null){
//				request.getSession().setAttribute("porukaR", "Uspesno ste registrovani.");
//				return true;
//			}else{
//				request.getSession().setAttribute("porukaR", "Greska prilikom registracije"); 
//				return false;
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
	
	@RequestMapping("/getAllKorisnik")
	public List<Korisnik> getAllKorisnik(){
		return korisnikService.getAll();
	}
	
	
	
	@RequestMapping("/saveKorisnik")
	public Korisnik saveKorisnik(HttpServletRequest request) {
		String e_mail = request.getParameter("e_mail");
		String imeKorisnika = request.getParameter("imeKorisnika");
		String password = request.getParameter("password");
		String prezimeKorisnika = request.getParameter("prezimeKorisnika");
		String username = request.getParameter("username");
		Korisnik k = new Korisnik();
		k.setE_mail(e_mail);
		k.setImeKorisnika(imeKorisnika);
		k.setPassword(password);
		k.setPrezimeKorisnika(prezimeKorisnika);
		k.setUsername(username);

		return korisnikService.addKorisnik(k);
	}

}
