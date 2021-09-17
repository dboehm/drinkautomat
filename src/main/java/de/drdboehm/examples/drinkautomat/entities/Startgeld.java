package de.drdboehm.examples.drinkautomat.entities;

public class Startgeld {
	

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









}
