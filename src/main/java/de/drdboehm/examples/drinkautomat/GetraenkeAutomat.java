package de.drdboehm.examples.drinkautomat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import de.drdboehm.examples.drinkautomat.businesslogic.VerkaufkaufController;
import de.drdboehm.examples.drinkautomat.entities.Fach;
import de.drdboehm.examples.drinkautomat.entities.Getraenk;
import de.drdboehm.examples.drinkautomat.entities.Muenze;

public class GetraenkeAutomat {

	private Befuellung befuellung;

	public static void main(String[] args) {
		// implement commons CLI for
		GetraenkeAutomat automat = new GetraenkeAutomat();
		VerkaufkaufController controller = new VerkaufkaufController(automat.befuelleAutomat());
		automat.show();
		System.out.println("Wählen Sie ein Getränk durch Eingabe der Fach-Namen");
		try (Scanner sc = new Scanner(System.in)) {
			String next = sc.next();
			Optional<Fach> fachOpt = automat.befuellung.identifiziereFachUeberName(next);
			if (fachOpt.isPresent() && controller.istGetraenkeWunschInFachVorhanden(fachOpt.get())) {
				Muenze[] muenzen = {Muenze.EURO_2};
				Optional<GetraenkUndWechselGeld> kaufen = controller.kaufen(fachOpt.get(), muenzen);
				System.out.println(kaufen.get());
			}
		}
	}

	private void show() {
		for (Fach fach : befuellung.getFaecher()) {
			System.out.println(fach);
		}
	}

	private Befuellung befuelleAutomat() {
		Gson gson = new Gson();
		try (JsonReader reader = new JsonReader(new FileReader(new File("beschickung.json")))) {
			befuellung = gson.fromJson(reader, Befuellung.class);
		} catch (FileNotFoundException e1) {
			return null;
		} catch (IOException e1) {
			return null;
		}
		return befuellung;
	}

}
