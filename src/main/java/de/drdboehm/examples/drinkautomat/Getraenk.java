package de.drdboehm.examples.drinkautomat;

public class Getraenk implements KaufbaresGut {
	private int preis;
	private String name;
	
	private int menge;
	
	
	public Getraenk(int preis, String name) {
		super();
		this.preis = preis;
		this.name = name;
	}
	@Override
	public GetraenkUndWechselGeld kaufen(GetraenkeWunsch auswahl, Muenze... einzahlung) {
		
		
		return null;
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
		builder.append(", menge=");
		builder.append(menge);
		builder.append("]");
		return builder.toString();
	}
	/**
	 * @return the menge
	 */
	public int getMenge() {
		return menge;
	}
	/**
	 * @param menge the menge to set
	 */
	public void setMenge(int menge) {
		this.menge = menge;
	}
	
	

}
