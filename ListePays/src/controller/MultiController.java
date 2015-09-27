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

	private static String lister_pays = "getPays";
	private static String capitale = "getCapitale";
	private static String habitants = "getNombreHab";
	private static String destenvoi = "http://localhost:8080/ProjetPaysTest/services/WSPays";
	private static String destination = "http://pays";

	private static EnvoiMessage unAppel = new EnvoiMessage();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView hello(@RequestParam(required = false) String pays) {
		List<String> villes = new ArrayList<String>();
		// On récupère la liste des pays
		unAppel.connexion();
		unAppel.creationMessage(lister_pays, destination, "");
		SOAPElement body = unAppel.EmmissionReception(destenvoi, "");
		System.out.println(body.getFirstChild().getChildNodes().item(3).getTextContent());
		NodeList listNodes = body.getFirstChild().getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			villes.add(body.getFirstChild().getChildNodes().item(i).getTextContent());
		}

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("villes", villes);

		if (pays != null && pays != "") {
			// Faire l'appel au web service pour récuprer les données et les
			// ajouter à la page
			model.addObject("pays_sel", pays);
		}
		return model;

	}

}