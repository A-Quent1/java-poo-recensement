package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exception.CodeDepException;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws CodeDepException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		
		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		
		if (!NumberUtils.isDigits(saisieMin)) {
			throw new CodeDepException ("La valeur minimale entré n'est pas valide");
		}
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();
		
		if (!NumberUtils.isDigits(saisieMax)) {
			throw new CodeDepException ("La valeur maximale entré n'est pas valide");
		}
		
		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;
		
		if ((min<0 || max<0) || min<max) {
			
			throw new CodeDepException ("La valeur saisie n'est pas valide car soit elle est négative"
					+ " ou la valeur maximale est inférieur à la minimale");
		}
		boolean trouver = false;
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
		
		if (!trouver) {
			throw new CodeDepException ("Le département saisi n'est pas reconnu");
		}
	}

}
