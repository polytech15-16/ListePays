package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller
public class MultiController extends MultiActionController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView hello(@RequestParam(required = false) String pays) {
		ModelAndView model = new ModelAndView();
		model.setViewName("hello");

		// Faire la connexion à la BDD pour récupérer les villes
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