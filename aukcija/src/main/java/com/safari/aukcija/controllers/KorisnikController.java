package com.safari.aukcija.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.repository.KorisnikRepository;
import com.safari.aukcija.service.KorisnikService;

import model.Korisnik;

@RestController
@EntityScan("Model")
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	KorisnikRepository korisnikRepository;
	
	Korisnik ulogovan;
	
	@RequestMapping(value="/login", method={RequestMethod.POST,RequestMethod.GET}) 
	public String login(String e_mail,String password,Model m,HttpServletRequest request){
		ulogovan = korisnikRepository.login(e_mail, password);
		if(ulogovan != null){
			request.getSession().setAttribute("ulogovan", ulogovan);
		}else{
			m.addAttribute("porukaLogin", "Uneli ste pogresne podatke");
		}
		
		return "Logovanje";
	}
	
	@PostMapping(value="registracija")
	public String registracija(Korisnik k ,HttpServletRequest request){
		try{
			k=korisnikService.addKorisnik(k);
			if(k != null){
				request.getSession().setAttribute("porukaR", "Uspesno ste registrovani.");
			}else{
				request.getSession().setAttribute("porukaR", "Greska prilikom registracije"); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "Registracija";
	}
	
	@RequestMapping("/getAllKorinsik")
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
