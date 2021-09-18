package de.drdboehm.examples.drinkautomat;

import java.util.Objects;

import de.drdboehm.examples.drinkautomat.entities.Muenze;

public class WechselgeldMuenzstatus {
	
	public WechselgeldMuenzstatus(Muenze muenze, Integer benoetigt) {
		super();
		this.muenze = muenze;
		this.benoetigt = benoetigt;
	}

	private Muenze muenze;
	
	private Integer benoetigt;
	
	private Integer vorhanden;

	
	
	/**
	 * @return the muenze
	 */
	public Muenze getMuenze() {
		return muenze;
	}

	/**
	 * @param muenze the muenze to set
	 */
	public void setMuenze(Muenze muenze) {
		this.muenze = muenze;
	}

	/**
	 * @return the benoetigt
	 */
	public Integer getBenoetigt() {
		return benoetigt;
	}

	/**
	 * @param benoetigt the benoetigt to set
	 */
	public void setBenoetigt(Integer benoetigt) {
		this.benoetigt = benoetigt;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(muenze);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WechselgeldMuenzstatus other = (WechselgeldMuenzstatus) obj;
		return muenze == other.muenze;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WechselgeldMuenzstatus [muenze=");
		builder.append(muenze);
		builder.append(", benoetigt=");
		builder.append(benoetigt);
		builder.append(", vorhanden=");
		builder.append(vorhanden);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the vorhanden
	 */
	public Integer getVorhanden() {
		return vorhanden;
	}

	/**
	 * @param vorhanden the vorhanden to set
	 */
	public void setVorhanden(Integer vorhanden) {
		this.vorhanden = vorhanden;
	}

}
