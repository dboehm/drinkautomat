package de.drdboehm.examples.drinkautomat;

import de.drdboehm.examples.drinkautomat.entities.Getraenk;

public class GetraenkUndWechselGeld {
	Getraenk getraenk;
	Wechselgeld zurück;
	
	Nachzahlung nachzahlung;
	public GetraenkUndWechselGeld(Getraenk getraenk, Nachzahlung nachzahlung) {
		super();
		this.getraenk = getraenk;
		this.nachzahlung = nachzahlung;
	}
	
	public GetraenkUndWechselGeld(Getraenk getraenk, Wechselgeld wechselgeld) {
		super();
		this.getraenk = getraenk;
		this.zurück = wechselgeld;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetraenkUndWechselGeld [getraenk=");
		builder.append(getraenk);
		builder.append(", zurück=");
		builder.append(zurück);
		builder.append(", nachzahlung=");
		builder.append(nachzahlung);
		builder.append("]");
		return builder.toString();
	}
	
	

}
