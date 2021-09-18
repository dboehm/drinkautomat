package de.drdboehm.examples.drinkautomat.businesslogic;

import java.io.File;
import java.util.Optional;

import de.drdboehm.examples.drinkautomat.entities.Fach;
import de.drdboehm.examples.drinkautomat.entities.GetraenkUndWechselGeld;
import de.drdboehm.examples.drinkautomat.entities.Muenze;
import de.drdboehm.examples.drinkautomat.entities.Wechselgeld;
import de.drdboehm.examples.drinkautomat.state.Befuellung;

/**
 * @author dboehm
 *
 */
public interface VerkaufControlLogic {
		
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
	
	public void fuegeMuenzenZuStartgeldHinzu(Muenze[] p_einzahlung);

	void entnehmeWechselgeldMuenzenAusStartgeld(GetraenkUndWechselGeld l_getraenkUndWechselgeld);

}
