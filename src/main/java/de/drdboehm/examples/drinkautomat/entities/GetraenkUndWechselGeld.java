package de.drdboehm.examples.drinkautomat.entities;

public class GetraenkUndWechselGeld {
	Getraenk getraenk;
	Wechselgeld wechelgeld;
	
	Nachzahlung nachzahlung;
	public GetraenkUndWechselGeld(Getraenk getraenk, Nachzahlung nachzahlung) {
		super();
		this.getraenk = getraenk;
		this.nachzahlung = nachzahlung;
	}
	
	public GetraenkUndWechselGeld(Getraenk getraenk, Wechselgeld wechselgeld) {
		super();
		this.getraenk = getraenk;
		this.wechelgeld = wechselgeld;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetraenkUndWechselGeld [getraenk=");
		builder.append(getraenk);
		builder.append(", zurück=");
		builder.append(wechelgeld);
		builder.append(", nachzahlung=");
		builder.append(nachzahlung);
		builder.append("]");
		return builder.toString();
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

	
	/**
	 * @return the nachzahlung
	 */
	public Nachzahlung getNachzahlung() {
		return nachzahlung;
	}

	/**
	 * @param nachzahlung the nachzahlung to set
	 */
	public void setNachzahlung(Nachzahlung nachzahlung) {
		this.nachzahlung = nachzahlung;
	}

	/**
	 * @return the wechelgeld
	 */
	public Wechselgeld getWechelgeld() {
		return wechelgeld;
	}

	/**
	 * @param wechelgeld the wechelgeld to set
	 */
	public void setWechelgeld(Wechselgeld wechelgeld) {
		this.wechelgeld = wechelgeld;
	}
	
	
	
	

}
