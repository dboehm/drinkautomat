package de.drdboehm.examples.drinkautomat.entities;

import de.drdboehm.examples.drinkautomat.businesslogic.Verkaeuflich;

public class Fach {
	private Integer id;
	private String name;
	private Integer reihe;
	private Integer spalte;
	private Integer kapazitaet;
	private Getraenk getraenk;
	
	private int menge;
	
	
	
	/**
	 * @return the reihe
	 */
	public Integer getReihe() {
		return reihe;
	}



	/**
	 * @param reihe the reihe to set
	 */
	public void setReihe(Integer reihe) {
		this.reihe = reihe;
	}



	/**
	 * @return the spalte
	 */
	public Integer getSpalte() {
		return spalte;
	}



	/**
	 * @param spalte the spalte to set
	 */
	public void setSpalte(Integer spalte) {
		this.spalte = spalte;
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
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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



	/**
	 * @return the kapazitat
	 */
	public Integer getKapazitat() {
		return kapazitaet;
	}



	/**
	 * @param kapazitat the kapazitat to set
	 */
	public void setKapazitat(Integer kapazitat) {
		this.kapazitaet = kapazitat;
	}



	public Fach(Integer id, String name, Integer reihe, Integer spalte, Integer kapazitaet, Getraenk getraenk,
			Integer menge) {
		super();
		this.id = id;
		this.name = name;
		this.reihe = reihe;
		this.spalte = spalte;
		this.kapazitaet = kapazitaet;
		this.getraenk = getraenk;
	}



	public Fach(Integer spalte, Integer reihe, Getraenk getraenk, Integer anzahlVorhanden) {
		super();
		this.reihe = reihe;
		this.spalte = spalte;
		this.getraenk = getraenk;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fach [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", reihe=");
		builder.append(reihe);
		builder.append(", spalte=");
		builder.append(spalte);
		builder.append(", kapazitaet=");
		builder.append(kapazitaet);
		builder.append(", getraenk=");
		builder.append(getraenk);
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