package de.drdboehm.examples.drinkautomat.businesslogic;

import java.io.File;
import java.util.Optional;

import de.drdboehm.examples.drinkautomat.Befuellung;
import de.drdboehm.examples.drinkautomat.GetraenkUndWechselGeld;
import de.drdboehm.examples.drinkautomat.Wechselgeld;
import de.drdboehm.examples.drinkautomat.entities.Fach;
import de.drdboehm.examples.drinkautomat.entities.Muenze;

/**
 * @author dboehm
 *
 */
public interface Verkaeuflich {
		
	/**
	 * @param auswahl  das gewählte {@link Fach} des Automaten mit der Kaufabsicht
	 * @param einzahlung die getätigte einzahlung an {@link Muenze}n als Array oder List 
	 * @return Optional<GetraenkUndWechselGeld> 
	 */
	
	Optional<GetraenkUndWechselGeld> kaufen(Fach auswahl, Muenze... einzahlung);
	
	Boolean istGetraenkeWunschInFachVorhanden(Fach fach);
	
	Optional<Wechselgeld> berechneWechselGeldFürZurueck(Integer zurAuszahlung);
	
		
	Befuellung befuelleAutomat(File befuellungProperties);
	
	void entnehmeVerkaufteWareAusFach(Fach verkauft);

	Optional<Wechselgeld> checkWechselgeldVorhanden(Optional<Wechselgeld> p_wechselGeld);
	
	// Boolean aktualisiereStartgeldNachEinnahmeUndAusgabeMuenzen(GetraenkUndWechselGeld verkauft);
	
	

}
