package de.drdboehm.examples.drinkautomat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import de.drdboehm.examples.drinkautomat.entities.Muenze;

public class Wechselgeld{
	public Wechselgeld() {
		super();
	}

	private List<WechselgeldMuenzstatus> muenzStati = new ArrayList<>();
	
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
	public List<WechselgeldMuenzstatus> getMuenzStati() {
		return muenzStati;
	}

	/**
	 * @param muenzStati the muenzStati to set
	 */
	public void setMuenzStati(List<WechselgeldMuenzstatus> muenzStati) {
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
