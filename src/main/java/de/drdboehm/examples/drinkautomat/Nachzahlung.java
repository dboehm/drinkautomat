package de.drdboehm.examples.drinkautomat;

import java.util.ArrayList;
import java.util.List;

import de.drdboehm.examples.drinkautomat.entities.Muenze;

public class Nachzahlung {
	private List<Muenze> nachzahlung = new ArrayList<>();

	/**
	 * @return the nachzahlung
	 */
	public List<Muenze> getNachzahlung() {
		return nachzahlung;
	}

	/**
	 * @param nachzahlung the nachzahlung to set
	 */
	public void setNachzahlung(List<Muenze> nachzahlung) {
		this.nachzahlung = nachzahlung;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nachzahlung [nachzahlung=");
		builder.append(nachzahlung);
		builder.append("]");
		return builder.toString();
	}

	
}
