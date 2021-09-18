package de.drdboehm.examples.drinkautomat.businesslogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import de.drdboehm.examples.drinkautomat.Befuellung;
import de.drdboehm.examples.drinkautomat.GetraenkUndWechselGeld;
import de.drdboehm.examples.drinkautomat.Wechselgeld;
import de.drdboehm.examples.drinkautomat.entities.Fach;
import de.drdboehm.examples.drinkautomat.entities.Muenze;
import de.drdboehm.examples.drinkautomat.entities.Startgeld;

public class VerkaufController implements Verkaeuflich {
	private Befuellung befuellung;

	public VerkaufController(Befuellung befuellung) {
		super();
		this.befuellung = befuellung;
	}

	public VerkaufController(File befuellungProperties) {
		super();
		this.befuellung = befuelleAutomat(befuellungProperties);
	}

	@Override
	public Optional<GetraenkUndWechselGeld> kaufen(Fach auswahl, Muenze... einzahlung) {
		if (istGetraenkeWunschInFachVorhanden(auswahl)) {
			Integer l_differenzEinzahlungZuPreis = berechneDifferenzEinzahlungZuPreis(auswahl, einzahlung);
			if (l_differenzEinzahlungZuPreis >= 0) {
				Optional<Wechselgeld> wechselGeldFürZurueck = berechneWechselGeldFürZurueck(l_differenzEinzahlungZuPreis);
				GetraenkUndWechselGeld guW = new GetraenkUndWechselGeld(auswahl.getGetraenk(),
						wechselGeldFürZurueck.get());
				return Optional.of(guW);
			} else {
//				Optional<Nachzahlung> wechselGeldFürZurueck = berechneWechselGeldFürZurueck(l_differenzEinzahlungZuPreis);
//				GetraenkUndWechselGeld guW = new GetraenkUndWechselGeld(auswahl.getGetraenk(),
				return Optional.empty();
			}
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
	public Boolean istWechselgeldVorhanden(Wechselgeld wechselGeld) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Wechselgeld> berechneWechselGeldFürZurueck(Integer p_zurAuszahlung) {
		if (p_zurAuszahlung >= 0) {
			Wechselgeld l_wechselgeld = new Wechselgeld();
			Optional<Wechselgeld> vorschlag = berechneWechselgeldVorschlag(l_wechselgeld, p_zurAuszahlung);
			return vorschlag;
		} else {
			Wechselgeld l_Nachzahlung = new Wechselgeld();
			Optional<Wechselgeld> vorschlag = berechneWechselgeldVorschlag(l_Nachzahlung, p_zurAuszahlung);
			return vorschlag;
		}
	}

	private Optional<Wechselgeld> berechneWechselgeldVorschlag(Wechselgeld p_wechselgeld, Integer p_zurAuszahlung) {
		
		List<Startgeld> startgelder = getBefuellung().getStartgeld();
		// erzwingt reverse order by Muenze.value (höchster Wert zuerst)
		Collections.sort(startgelder);
		for (Startgeld startgeld : startgelder) {
			if (startgeld.getMuenze().getValue() <= p_zurAuszahlung) {
				p_wechselgeld.getZurueck().add(startgeld.getMuenze());
				p_zurAuszahlung = p_zurAuszahlung - startgeld.getMuenze().getValue();
//				this.differenzEinzahlungZuPreis = p_zurAuszahlung;
				if (p_zurAuszahlung.equals(Integer.valueOf(0))) {
					return Optional.of(p_wechselgeld);
				} else
					return berechneWechselgeldVorschlag(p_wechselgeld, p_zurAuszahlung);

			}
		}
		return Optional.of(p_wechselgeld);
	}

	@Override
	public Befuellung befuelleAutomat(File befuellungProperties) {
		Gson gson = new Gson();
		try (JsonReader reader = new JsonReader(new FileReader(befuellungProperties))) {
			befuellung = gson.fromJson(reader, Befuellung.class);
		} catch (FileNotFoundException e1) {
			return null;
		} catch (IOException e1) {
			return null;
		}
		return befuellung;
	}

	/**
	 * @return the befuellung
	 */
	public Befuellung getBefuellung() {
		return befuellung;
	}




}
