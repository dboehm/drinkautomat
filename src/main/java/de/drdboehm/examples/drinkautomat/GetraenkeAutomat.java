package de.drdboehm.examples.drinkautomat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import de.drdboehm.examples.drinkautomat.businesslogic.VerkaufController;
import de.drdboehm.examples.drinkautomat.entities.Fach;
import de.drdboehm.examples.drinkautomat.entities.Getraenk;
import de.drdboehm.examples.drinkautomat.entities.Muenze;

public class GetraenkeAutomat {

	private VerkaufController controller; 

	public static void main(String[] args) {
		// implement commons CLI for
		GetraenkeAutomat automat = new GetraenkeAutomat();
		try {
			automat.controller = new VerkaufController(new File("beschickung.json"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		automat.show();
		System.out.println("Wählen Sie ein Getränk durch Eingabe der Fach-Namen");
//		try (Scanner sc = new Scanner(System.in)) {
//			String next = sc.next();
			Optional<Fach> fachOpt = automat.controller.getBefuellung().identifiziereFachUeberName("A1");
			if (fachOpt.isPresent() && automat.controller.istGetraenkeWunschInFachVorhanden(fachOpt.get())) {
				Muenze[] muenzen = {Muenze.EURO_1};
				Optional<GetraenkUndWechselGeld> kaufen = automat.controller.kaufen(fachOpt.get(), muenzen);
				System.out.println(kaufen.get());
			}
//		}
	}

	private void show() {
		for (Fach fach : controller.getBefuellung().getFaecher()) {
			System.out.println(fach);
		}
	}
}
