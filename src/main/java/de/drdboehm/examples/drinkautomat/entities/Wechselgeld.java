package de.drdboehm.examples.drinkautomat.entities;

import java.util.ArrayList;
import java.util.List;

import de.drdboehm.examples.drinkautomat.state.WechselgeldMuenzState;

public class Wechselgeld{
	public Wechselgeld() {
		super();
	}

	private List<WechselgeldMuenzState> muenzStati = new ArrayList<>();
	
	private List<Muenze> zurueck = new ArrayList<>();
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Wechselgeld [muenzStati=");
		builder.append(muenzStati);
		builder.append(", zurueck=");
		builder.append(zurueck);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the muenzStati
	 */
	public List<WechselgeldMuenzState> getMuenzStati() {
		return muenzStati;
	}

	/**
	 * @param muenzStati the muenzStati to set
	 */
	public void setMuenzStati(List<WechselgeldMuenzState> muenzStati) {
		this.muenzStati = muenzStati;
	}

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




}
