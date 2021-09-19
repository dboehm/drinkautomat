package de.drdboehm.examples.drinkautomat.businesslogic;

import java.io.File;
import java.util.Optional;

import de.drdboehm.examples.drinkautomat.entities.Fach;
import de.drdboehm.examples.drinkautomat.entities.Getraenk;
import de.drdboehm.examples.drinkautomat.entities.GetraenkUndWechselGeld;
import de.drdboehm.examples.drinkautomat.entities.Muenze;
import de.drdboehm.examples.drinkautomat.entities.Nachzahlung;
import de.drdboehm.examples.drinkautomat.entities.Wechselgeld;
import de.drdboehm.examples.drinkautomat.state.Befuellung;

/**
 * @author dboehm
 *
 */
public interface VerkaufControlLogic {

	/**
	 * @param auswahl    das gewählte {@link Fach} des Automaten mit der Kaufabsicht
	 * @param einzahlung die getätigte einzahlung an {@link Muenze}n als Array
	 * @return Optional<GetraenkUndWechselGeld> das Optional des
	 *         {@link GetraenkUndWechselGeld} objects ist ein Wrapper um
	 *         {@link Getraenk}, {@link Wechselgeld} und {@link Nachzahlung}
	 */

	Optional<GetraenkUndWechselGeld> kaufen(Fach auswahl, Muenze... einzahlung);

	/**
	 * @param fach das gewählte {@link Fach} des Automaten das abgefragt werden soll
	 * @return Boolean ob das Fach noch ein getraenk enthaelt
	 */
	Boolean istGetraenkeWunschInFachVorhanden(Fach fach);

	/**
	 * Methode berechnet eine Stückelung der übergebenen Cents zurAuszahlung mit den möglichen
	 * {@link Muenze)n der Befuellung
	 * 
	 * @param zurAuszahlung Anzahl der Cents, die zur Auszahlung gebracht werden
	 * @return 
	 */
	Optional<Wechselgeld> berechneWechselGeldFürZurueck(Integer zurAuszahlung);

	Befuellung befuelleAutomat(File befuellungProperties);

	Kassensturz entleereAutomatMitKassensturz();

	void entnehmeVerkaufteWareAusFach(Fach verkauft);

	Optional<Wechselgeld> checkWechselgeldVorhanden(Optional<Wechselgeld> p_wechselGeld);

	public void fuegeMuenzenZuStartgeldHinzu(Muenze[] p_einzahlung);

	void entnehmeWechselgeldMuenzenAusStartgeld(GetraenkUndWechselGeld l_getraenkUndWechselgeld);
	
	public Optional<Fach> identifiziereFachUeberName(String next); 

}
