package de.drdboehm.examples.drinkautomat.entities;

import java.util.Objects;

public class Startgeld implements Comparable<Startgeld>{

	private Muenze muenze;

	private Integer anzahl;

	public Startgeld(Muenze muenze, Integer anzahl) {
		super();
		this.muenze = muenze;
		this.anzahl = anzahl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Startgeld [muenze=");
		builder.append(muenze);
		builder.append(", anzahl=");
		builder.append(anzahl);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * erzwingt absteigende Ordnung der {@link Muenze}n basierend auf ihrem value
	 */
	@Override
	public int compareTo(Startgeld o) {
		return o.getMuenze().getValue().compareTo(this.getMuenze().getValue());
	}

	/**
	 * @return the muenze
	 */
	public Muenze getMuenze() {
		return muenze;
	}

	/**
	 * @param muenze the muenze to set
	 */
	public void setMuenze(Muenze muenze) {
		this.muenze = muenze;
	}

	/**
	 * @return the anzahl
	 */
	public Integer getAnzahl() {
		return anzahl;
	}

	/**
	 * @param anzahl the anzahl to set
	 */
	public void setAnzahl(Integer anzahl) {
		this.anzahl = anzahl;
	}

	@Override
	public int hashCode() {
		return Objects.hash(muenze);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Startgeld other = (Startgeld) obj;
		return muenze == other.muenze;
	}
	
	

}
