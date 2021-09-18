package de.drdboehm.examples.drinkautomat.entities;

import java.util.ArrayList;
import java.util.List;

public class Nachzahlung {
	private List<Muenze> nachzahlungen = new ArrayList<>();

	/**
	 * @return the nachzahlungen
	 */
	public List<Muenze> getNachzahlungen() {
		return nachzahlungen;
	}

	/**
	 * @param nachzahlungen the nachzahlung to set
	 */
	public void setNachzahlungen(List<Muenze> nachzahlungen) {
		this.nachzahlungen = nachzahlungen;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nachzahlung [nachzahlungen=");
		builder.append(nachzahlungen);
		builder.append("]");
		return builder.toString();
	}

	
}
