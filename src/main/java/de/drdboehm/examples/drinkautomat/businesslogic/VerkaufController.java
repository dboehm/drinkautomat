package de.drdboehm.examples.drinkautomat.businesslogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import de.drdboehm.examples.drinkautomat.GetraenkeAutomat;
import de.drdboehm.examples.drinkautomat.entities.Fach;
import de.drdboehm.examples.drinkautomat.entities.Getraenk;
import de.drdboehm.examples.drinkautomat.entities.GetraenkUndWechselGeld;
import de.drdboehm.examples.drinkautomat.entities.Muenze;
import de.drdboehm.examples.drinkautomat.entities.Nachzahlung;
import de.drdboehm.examples.drinkautomat.entities.Startgeld;
import de.drdboehm.examples.drinkautomat.entities.Wechselgeld;
import de.drdboehm.examples.drinkautomat.state.Befuellung;
import de.drdboehm.examples.drinkautomat.state.WechselgeldMuenzState;

public class VerkaufController implements VerkaufControlLogic {

	private static final Logger logger = LogManager.getLogger(VerkaufController.class);
	private Befuellung befuellung;

	public VerkaufController(Befuellung befuellung) {
		super();
		this.befuellung = befuellung;
	}

	public VerkaufController() {
		super();
	}

	public VerkaufController(File befuellungProperties) {
		super();
		this.befuellung = befuelleAutomat(befuellungProperties);
	}

	@Override
	public Optional<GetraenkUndWechselGeld> kaufen(Fach p_auswahl, Muenze... p_einzahlung) {
		if (istGetraenkeWunschInFachVorhanden(p_auswahl)) {
			Integer l_differenzEinzahlungZuPreis = berechneDifferenzEinzahlungZuPreis(p_auswahl, p_einzahlung);
			if (l_differenzEinzahlungZuPreis >= 0) {
				Optional<Wechselgeld> l_wechselGeldFürZurueckOpt = berechneWechselGeldFürZurueck(
						l_differenzEinzahlungZuPreis);
				GetraenkUndWechselGeld l_getraenkUndWechselgeld = new GetraenkUndWechselGeld(p_auswahl.getGetraenk(),
						l_wechselGeldFürZurueckOpt.get());
				// vor Rückgabe entnehme Getränk aus Fach
				entnehmeVerkaufteWareAusFach(p_auswahl);
				fuegeMuenzenZuStartgeldHinzu(p_einzahlung);
				entnehmeWechselgeldMuenzenAusStartgeld(l_getraenkUndWechselgeld);

				return Optional.of(l_getraenkUndWechselgeld);
			} else {
				Optional<Wechselgeld> nachzahlungFürNachforderung = berechneWechselGeldFürZurueck(
						-l_differenzEinzahlungZuPreis);
				// TODO: schnelle Lösung nicht sauber OOP
				Nachzahlung nachzahlung = new Nachzahlung();
				nachzahlung.setNachzahlung(nachzahlungFürNachforderung.get().getZurueck());
				GetraenkUndWechselGeld guW = new GetraenkUndWechselGeld(p_auswahl.getGetraenk(), nachzahlung);
				return Optional.of(guW);
			}
		} else
			return Optional.empty();
	}

	@Override
	public void entnehmeWechselgeldMuenzenAusStartgeld(GetraenkUndWechselGeld l_getraenkUndWechselgeld) {
		List<Startgeld> l_startgelder = befuellung.getStartgeld();
		for (WechselgeldMuenzState l_wechselgeldMuenzstatus : l_getraenkUndWechselgeld.getZurück().getMuenzStati()){
			Startgeld l_startgeld = new Startgeld(l_wechselgeldMuenzstatus.getMuenze(), null); 
			if (l_startgelder.contains(l_startgeld)) {
				l_startgeld = l_startgelder.get(l_startgelder.indexOf(l_startgeld));
				l_startgeld.setAnzahl(l_startgeld.getAnzahl()-l_wechselgeldMuenzstatus.getBenoetigt());
			}
			
		}

	}

	@Override
	public void fuegeMuenzenZuStartgeldHinzu(Muenze[] p_einzahlung) {
		List<Startgeld> l_startgelder = befuellung.getStartgeld();

		for (Muenze l_muenze : p_einzahlung) {
			Startgeld l_startgeld = new Startgeld(l_muenze, null);
			if (l_startgelder.contains(l_startgeld)) {
				l_startgeld = l_startgelder.get(l_startgelder.indexOf(l_startgeld));
				l_startgeld.setAnzahl(l_startgeld.getAnzahl()+1);
				logger.info("{}", l_startgeld);
			}

		}

	}

	@Override
	public Boolean istGetraenkeWunschInFachVorhanden(Fach p_auswahl) {
		Optional<Fach> l_fachOpt = befuellung.getFaecher().stream().filter(fach -> fach.equals(p_auswahl)).findFirst();
		if (l_fachOpt.isPresent()) {
			return l_fachOpt.get().getMenge() > 0;
		} else
			return false;
	}

	@Override
	public Optional<Wechselgeld> checkWechselgeldVorhanden(Optional<Wechselgeld> p_wechselGeld) {
		List<Startgeld> l_startgelder = befuellung.getStartgeld();
		for (WechselgeldMuenzState l_wechWechselgeldMuenzstatus : p_wechselGeld.get().getMuenzStati()) {
			Muenze l_muenze = l_wechWechselgeldMuenzstatus.getMuenze();
			Startgeld l_temp = new Startgeld(l_muenze, null);
			if (l_startgelder.contains(l_temp)) {
				Startgeld l_startgeld = l_startgelder.get(l_startgelder.indexOf(l_temp));
				if (l_startgeld.getAnzahl() >= l_wechWechselgeldMuenzstatus.getBenoetigt()) {
					l_wechWechselgeldMuenzstatus.setVorhanden(l_wechWechselgeldMuenzstatus.getBenoetigt());
				} else {
					l_wechWechselgeldMuenzstatus.setVorhanden(l_startgeld.getAnzahl());
				}
			}
		}
		return p_wechselGeld;
	}

	@Override
	public Optional<Wechselgeld> berechneWechselGeldFürZurueck(Integer p_zurAuszahlung) {
		if (p_zurAuszahlung >= 0) {
			Wechselgeld l_wechselgeld = new Wechselgeld();
			Optional<Wechselgeld> vorschlag = berechneWechselgeldVorschlag(l_wechselgeld, p_zurAuszahlung);
			return checkWechselgeldVorhanden(vorschlag);
		} else {
			Wechselgeld l_Nachzahlung = new Wechselgeld();
			Optional<Wechselgeld> vorschlag = berechneWechselgeldVorschlag(l_Nachzahlung, p_zurAuszahlung);
			return vorschlag;
		}
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

	@Override
	public void entnehmeVerkaufteWareAusFach(Fach p_verkauft) {
		p_verkauft.setMenge(p_verkauft.getMenge() - 1);
	}

	/// private Methoden

	private Optional<Wechselgeld> berechneWechselgeldVorschlag(Wechselgeld p_wechselgeld, Integer p_zurAuszahlung) {

		List<Startgeld> startgelder = getBefuellung().getStartgeld();
		// erzwingt reverse order by Muenze.value (höchster Wert zuerst)
		Collections.sort(startgelder);
		for (Startgeld startgeld : startgelder) {
			Muenze l_muenze = startgeld.getMuenze();
			if (l_muenze.getValue() <= p_zurAuszahlung) {
				p_wechselgeld.getZurueck().add(l_muenze);
				WechselgeldMuenzState l_wechWechselgeldMuenzstatus = new WechselgeldMuenzState(l_muenze, 1);
				// check if there is a MuenzStatus already
				// if not
				if (!p_wechselgeld.getMuenzStati().contains(l_wechWechselgeldMuenzstatus)) {
					// add it
					p_wechselgeld.getMuenzStati().add(l_wechWechselgeldMuenzstatus);
				} else {
					// get it
					WechselgeldMuenzState l_wechselgeldMuenzstatus = p_wechselgeld.getMuenzStati()
							.get(p_wechselgeld.getMuenzStati().indexOf(l_wechWechselgeldMuenzstatus));
					l_wechselgeldMuenzstatus.setBenoetigt(l_wechselgeldMuenzstatus.getBenoetigt() + 1);
				}
				p_zurAuszahlung = p_zurAuszahlung - startgeld.getMuenze().getValue();
				if (p_zurAuszahlung.equals(Integer.valueOf(0))) {
					return Optional.of(p_wechselgeld);
				} else
					return berechneWechselgeldVorschlag(p_wechselgeld, p_zurAuszahlung);

			}
		}
		return Optional.of(p_wechselgeld);
	}

	private Integer berechneDifferenzEinzahlungZuPreis(Fach p_auswahl, Muenze... p_einzahlung) {
		int gezahlt = 0;
		for (Muenze muenze : p_einzahlung) {
			gezahlt += muenze.getValue();
		}
		int preis = p_auswahl.getGetraenk().getPreis();
		return gezahlt - preis;
	}
}
