package de.drdboehm.examples.drinkautomat.entities;


public class Getraenk {
	private int preis;
	private String name;
	
	public Getraenk(int preis, String name) {
		super();
		this.preis = preis;
		this.name = name;
	}
	
	/**
	 * @return the preis
	 */
	public int getPreis() {
		return preis;
	}
	/**
	 * @param preis the preis to set
	 */
	public void setPreis(int preis) {
		this.preis = preis;
	}
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

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Getraenk [preis=");
		builder.append(preis);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
