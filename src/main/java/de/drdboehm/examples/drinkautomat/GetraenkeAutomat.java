package de.drdboehm.examples.drinkautomat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class GetraenkeAutomat {

	private Befuellung befuellung;

	public static void main(String[] args) {
		// implement commons CLI 
		GetraenkeAutomat automat = new GetraenkeAutomat();
		automat.befuelleAutomat();
		automat.show();
	}

	private void show() {
		for (Fach fach : befuellung.getFaecher()) {
			System.out.println(fach);
		}
	}

	private void befuelleAutomat() {
		Gson gson = new Gson();
		try (JsonReader reader = new JsonReader(new FileReader(new File("beschickung.json")))) {
			befuellung = gson.fromJson(reader, Befuellung.class);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
