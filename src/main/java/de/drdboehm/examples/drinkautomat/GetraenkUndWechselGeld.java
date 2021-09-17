package de.drdboehm.examples.drinkautomat;

import de.drdboehm.examples.drinkautomat.entities.Getraenk;

public class GetraenkUndWechselGeld {
	Getraenk getraenk;
	Wechselgeld zurück;
	public GetraenkUndWechselGeld(Getraenk getraenk, Wechselgeld zurück) {
		super();
		this.getraenk = getraenk;
		this.zurück = zurück;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetraenkUndWechselGeld [getraenk=");
		builder.append(getraenk);
		builder.append(", zurück=");
		builder.append(zurück);
		builder.append("]");
		return builder.toString();
	}
	
	

}
