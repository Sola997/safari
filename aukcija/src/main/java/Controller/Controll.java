//package Controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.safari.aukcija.repository.PredmetRepository;
//import com.safari.aukcija.service.LicitacijaService;
//import com.safari.aukcija.service.OcenaService;
//import com.safari.aukcija.service.PredmetService;
//
//import model.Kategorija;
//import model.Licitacija;
//import model.Ocena;
//import model.Predmet;
//
//@RestController
//public class Controll {
//	
//	@Autowired
//	PredmetRepository predmetRepository;
//	
//	@Autowired
//	PredmetService predmetService;
//	
//	@Autowired
//	LicitacijaService licitacijaService;
//	
//	@Autowired
//	OcenaService ocenaService;
//	
//	
//	Ocena o;
//	Licitacija l;
//	Predmet p;
//
//	@RequestMapping(value = "/getPredmetKategorije" ,method = RequestMethod.POST,  consumes ="application/json", produces = "application/json")
//	public List<Predmet> getPredmetKategorije(Kategorija nazivKategorije){
//		List<Predmet> predmetKategorije = predmetRepository.predmetPoKategoriji(nazivKategorije);
//		return predmetKategorije;
//	}
//	
//	@RequestMapping(value = "/dodajKomnetarIOcenu" ,method = RequestMethod.POST,  consumes ="application/json", produces = "application/json")
//	public boolean dodajKomnetarIOcenu() {
//		Ocena o= new Ocena();
//		try {
//			if(o!=null) {
//				ocenaService.addOcena(o);
//				return true;
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
//	@RequestMapping(value = "/dodajLicitaciju" ,method = RequestMethod.POST,  consumes ="application/json", produces = "application/json")
//	public boolean dodajLicitaciju() {
//		Licitacija l= new Licitacija();
//		try {
//			if(l!=null) {
//				licitacijaService.addLicitacija(l);
//				return true;
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
//	@RequestMapping(value = "/dodajPredmetNaAukciju" ,method = RequestMethod.POST,  consumes ="application/json", produces = "application/json")
//	public boolean dodajPredmetNaAukciju() {
//		Predmet p= new Predmet();
//		try {
//			if(p!=null) {
//				predmetService.addPredmet(p);
//				return true;
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//}
