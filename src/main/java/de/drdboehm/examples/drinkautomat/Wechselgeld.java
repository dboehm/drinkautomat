package de.drdboehm.examples.drinkautomat;

import java.util.ArrayList;
import java.util.List;

import de.drdboehm.examples.drinkautomat.entities.Muenze;

public class Wechselgeld{
	private List<Muenze> zurueck = new ArrayList<>();

	/**
	 * @return the zurueck
	 */
	public List<Muenze> getZurueck() {
		return zurueck;
	}

	/**
	 * @param zurueck the zurueck to set
	 */
	public void setZurueck(List<Muenze> zurueck) {
		this.zurueck = zurueck;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Wechselgeld [zurueck=");
		builder.append(zurueck);
		builder.append("]");
		return builder.toString();
	}


}
