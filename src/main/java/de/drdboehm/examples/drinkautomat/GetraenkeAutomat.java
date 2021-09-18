package de.drdboehm.examples.drinkautomat;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Optional;

import de.drdboehm.examples.drinkautomat.businesslogic.VerkaufController;
import de.drdboehm.examples.drinkautomat.entities.Fach;
import de.drdboehm.examples.drinkautomat.entities.Muenze;
import de.drdboehm.examples.drinkautomat.entities.Startgeld;

public class GetraenkeAutomat {
	private static final Logger logger = LogManager.getLogger(GetraenkeAutomat.class);

	private VerkaufController controller;

	public static void main(String[] args) {
		// TODO: implement commons CLI for Befuellungs-File (JSON)
		GetraenkeAutomat automat = new GetraenkeAutomat();
		try {
			automat.controller = new VerkaufController(new File("beschickung.json"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		automat.showFaecherByLog();
		logger.info("{}", "Wählen Sie ein Getränk durch Eingabe der Fach-Namen");
		String next = "A2";
		Muenze[] muenzen = { Muenze.EURO_1};
		Optional<Fach> fachOpt = automat.controller.getBefuellung().identifiziereFachUeberName(next);
		if (fachOpt.isPresent() && automat.controller.istGetraenkeWunschInFachVorhanden(fachOpt.get())) {
			Optional<GetraenkUndWechselGeld> kaufen = automat.controller.kaufen(fachOpt.get(), muenzen);
			logger.info("{}", kaufen);
			automat.showFaecherByLog();
			automat.showStartGeldWithLogger();
		} else {
			logger.error("Das Fach '{}' existiert nicht.", next);
		}
	}

	private void showFaecherByLog() {
		for (Fach fach : controller.getBefuellung().getFaecher()) {
			logger.info("{}", fach);
		}
	}
	
	private void showStartGeldWithLogger() {
		Befuellung befuellung = controller.getBefuellung();
		for (Startgeld l_startgeld : befuellung.getStartgeld()) {
			logger.info("{}", l_startgeld);
		}
	}
}
