package de.drdboehm.examples.drinkautomat.businesslogic;

import java.util.Date;
import java.util.List;

import de.drdboehm.examples.drinkautomat.entities.Fach;
import de.drdboehm.examples.drinkautomat.entities.Startgeld;
import de.drdboehm.examples.drinkautomat.state.Befuellung;

public class Kassensturz {
	private List<Fach> faecher;

	private List<Startgeld> startgeld;

	private String name;

	private Date date;

	public Kassensturz(Befuellung befuellung) {
		super();
		this.faecher = befuellung.getFaecher();
		this.startgeld = befuellung.getStartgeld();
		this.name = befuellung.getName();
		this.date = new Date();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Kassensturz [faecher=");
		builder.append(faecher);
		builder.append(", startgeld=");
		builder.append(startgeld);
		builder.append(", name=");
		builder.append(name);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}

}
