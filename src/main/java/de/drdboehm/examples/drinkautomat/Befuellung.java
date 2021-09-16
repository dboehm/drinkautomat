package de.drdboehm.examples.drinkautomat;

import java.util.List;

public class Befuellung {
	
	private String name;
	private int anzahl_reihen;
	private int anzahl_spalten;
	
	private Nested nested;
	
	private List<Fach> faecher;
	
	private Getraenk getraenk;
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
		builder.append(", nested=");
		builder.append(nested);
		builder.append(", faecher=");
		builder.append(faecher);
		builder.append("]");
		return builder.toString();
	}
	/**
	 * @return the nested
	 */
	public Nested getNested() {
		return nested;
	}
	/**
	 * @param nested the nested to set
	 */
	public void setNested(Nested nested) {
		this.nested = nested;
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
	 * @return the getraenk
	 */
	public Getraenk getGetraenk() {
		return getraenk;
	}
	/**
	 * @param getraenk the getraenk to set
	 */
	public void setGetraenk(Getraenk getraenk) {
		this.getraenk = getraenk;
	}

}
