package de.drdboehm.examples.drinkautomat.businesslogic;

import java.util.Optional;

import de.drdboehm.examples.drinkautomat.GetraenkUndWechselGeld;
import de.drdboehm.examples.drinkautomat.Wechselgeld;
import de.drdboehm.examples.drinkautomat.entities.Fach;
import de.drdboehm.examples.drinkautomat.entities.Muenze;

public interface Verkaeuflich {
	Optional<GetraenkUndWechselGeld> kaufen(Fach auswahl, Muenze... einzahlung);
	
	Boolean istGetraenkeWunschInFachVorhanden(Fach fach);
	
	Integer berechneDifferenzEinzahlungZuPreis(Fach auswahl, Muenze... einzahlung);
	
	Boolean istWechselVorhanden(Wechselgeld wechselGeld);
	
	Wechselgeld berechneWechselGeldFürZurueck(Integer zurAuszahlung);

}
