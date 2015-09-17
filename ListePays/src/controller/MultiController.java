package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import requeteSOAP.EnvoiMessage;

@Controller
public class MultiController extends MultiActionController {

	private static String lister_pays = "getPays";
	private static String capitale = "getCapitale";
	private static String habitants = "getNombreHab";
	private static String destenvoi = "http://localhost:8080/ProjetWSPays/services/WSPays";
	private static String destination = "http://pays";

	private static EnvoiMessage unAppel = new EnvoiMessage();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView hello(@RequestParam(required = false) String pays) {

		// On récupère la liste des pays
		unAppel.connexion();
		unAppel.creationMessage(lister_pays, destination, "");
		unAppel.EmmissionReception(destenvoi, "");

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");

		// Faire l'appel au webservice
		List<String> villes = new ArrayList<String>();
		villes.add("France");
		villes.add("Portugal");
		villes.add("Italie");
		model.addObject("villes", villes);

		if (pays != null && pays != "") {
			// Faire l'appel au web service pour récuprer les données et les
			// ajouter à la page
			model.addObject("pays_sel", pays);
		}
		return model;

	}

}