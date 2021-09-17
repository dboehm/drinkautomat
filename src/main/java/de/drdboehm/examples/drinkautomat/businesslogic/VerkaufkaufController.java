package de.drdboehm.examples.drinkautomat.businesslogic;

import java.util.Optional;

import de.drdboehm.examples.drinkautomat.Befuellung;
import de.drdboehm.examples.drinkautomat.GetraenkUndWechselGeld;
import de.drdboehm.examples.drinkautomat.Wechselgeld;
import de.drdboehm.examples.drinkautomat.entities.Fach;
import de.drdboehm.examples.drinkautomat.entities.Muenze;

public class VerkaufkaufController implements Verkaeuflich {
	private Befuellung befuellung;

	public VerkaufkaufController(Befuellung befuellung) {
		super();
		this.befuellung = befuellung;
	}

	@Override
	public Optional<GetraenkUndWechselGeld> kaufen(Fach auswahl, Muenze... einzahlung) {
		if (this.istGetraenkeWunschInFachVorhanden(auswahl)) {
			Integer differenzEinzahlungZuPreis = berechneDifferenzEinzahlungZuPreis(auswahl, einzahlung);
			if (differenzEinzahlungZuPreis >= 0) {
				Wechselgeld wechselGeldFürZurueck = berechneWechselGeldFürZurueck(differenzEinzahlungZuPreis);
				GetraenkUndWechselGeld guW = new GetraenkUndWechselGeld(auswahl.getGetraenk(), wechselGeldFürZurueck);
				return Optional.of(guW);
			} else {
				// return
			}
			return Optional.empty();

		} else
			return Optional.empty();
	}

	@Override
	public Boolean istGetraenkeWunschInFachVorhanden(Fach auswahl) {
		Optional<Fach> findFirst = befuellung.getFaecher().stream().filter(fach -> fach.equals(auswahl)).findFirst();
		if (findFirst.isPresent()) {
			return findFirst.get().getMenge() > 0;
		} else
			return false;
	}

	@Override
	public Integer berechneDifferenzEinzahlungZuPreis(Fach auswahl, Muenze... einzahlung) {
		int gezahlt = 0;
		for (Muenze muenze : einzahlung) {
			gezahlt += muenze.getValue();
		}
		int preis = auswahl.getGetraenk().getPreis();
		return gezahlt - preis;
	}

	@Override
	public Boolean istWechselVorhanden(Wechselgeld wechselGeld) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Wechselgeld berechneWechselGeldFürZurueck(Integer zurAuszahlung) {
		// TODO Auto-generated method stub
		return null;
	}

}
