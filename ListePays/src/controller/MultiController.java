package controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPElement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.w3c.dom.NodeList;

import requeteSOAP.EnvoiMessage;

@Controller
public class MultiController extends MultiActionController {
	// Fonctions du webservices à appeler
	private static String LISTER_PAYS = "getPays";
	private static String CAPITAL = "getCapitale";
	private static String HABITANTS = "getNombreHab";

	// adresse du web service
	private static String DESTENVOI = "http://localhost:8080/ProjetPaysTest/services/WSPays";

	private static String DESTINATION = "http://pays";

	private static EnvoiMessage unAppel = new EnvoiMessage();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView hello(@RequestParam(required = false) String pays) {
		List<String> lesPays = new ArrayList<String>();
		// On récupère la liste des pays via le web service
		unAppel.connexion();
		unAppel.creationMessage(LISTER_PAYS, DESTINATION, "");

		// Parse le message reçu pour récuperer tous les pays
		SOAPElement body = unAppel.EmmissionReception(DESTENVOI, "");
		NodeList listNodes = body.getFirstChild().getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			lesPays.add(body.getFirstChild().getChildNodes().item(i).getTextContent());
		}

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		// ajoute les pays à la vue
		model.addObject("pays", lesPays);

		// On vérifie si on a un paramètre "pays" dans l'url
		if (pays != null && pays != "") {
			String capitale = null;
			int nb_habs = 0;

			// ajoute le pays séléctionné dans la vue
			model.addObject("pays_sel", pays);

			// On récupère la capitale du pays via le webservice
			unAppel.connexion();
			unAppel.creationMessage(CAPITAL, DESTINATION, pays);
			SOAPElement body_capitale = unAppel.EmmissionReception(DESTENVOI, pays);
			capitale = body_capitale.getFirstChild().getChildNodes().item(0).getTextContent();
			// ajoute capitale dans la vue
			model.addObject("capitale", capitale);

			// On récupère le nb d'ahbitants du pays via le webservice
			unAppel.connexion();
			unAppel.creationMessage(HABITANTS, DESTINATION, pays);
			SOAPElement body_nbHabs = unAppel.EmmissionReception(DESTENVOI, pays);
			nb_habs = Integer.parseInt(body_nbHabs.getFirstChild().getChildNodes().item(0).getTextContent());
			// ajoute nb habitants dans la vue
			model.addObject("nb_habs", nb_habs);
		}
		return model;

	}

}