package de.drdboehm.examples.drinkautomat.state;

import java.util.List;
import java.util.Optional;

import de.drdboehm.examples.drinkautomat.entities.Fach;
import de.drdboehm.examples.drinkautomat.entities.Startgeld;

public class Befuellung {

	private String name;
	private int anzahl_reihen;
	private int anzahl_spalten;

	private List<Fach> faecher;

	private List<Startgeld> startgeld;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the anzahl_reihen
	 */
	public int getAnzahl_reihen() {
		return anzahl_reihen;
	}

	/**
	 * @param anzahl_reihen the anzahl_reihen to set
	 */
	public void setAnzahl_reihen(int anzahl_reihen) {
		this.anzahl_reihen = anzahl_reihen;
	}

	/**
	 * @return the anzahl_spalten
	 */
	public int getAnzahl_spalten() {
		return anzahl_spalten;
	}

	/**
	 * @param anzahl_spalten the anzahl_spalten to set
	 */
	public void setAnzahl_spalten(int anzahl_spalten) {
		this.anzahl_spalten = anzahl_spalten;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Befuellung [name=");
		builder.append(name);
		builder.append(", anzahl_reihen=");
		builder.append(anzahl_reihen);
		builder.append(", anzahl_spalten=");
		builder.append(anzahl_spalten);
		builder.append(", faecher=");
		builder.append(faecher);
		builder.append(", startgeld=");
		builder.append(startgeld);
		builder.append("]");
		return builder.toString();
	}


	/**
	 * @return the faecher
	 */
	public List<Fach> getFaecher() {
		return faecher;
	}

	/**
	 * @param faecher the faecher to set
	 */
	public void setFaecher(List<Fach> faecher) {
		this.faecher = faecher;
	}
	
	/**
	 * @return the startgeld
	 */
	public List<Startgeld> getStartgeld() {
		return startgeld;
	}

	/**
	 * @param startgeld the startgeld to set
	 */
	public void setStartgeld(List<Startgeld> startgeld) {
		this.startgeld = startgeld;
	}

		
}
