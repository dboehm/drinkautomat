package de.drdboehm.examples.drinkautomat;

public interface KaufbaresGut {
	GetraenkUndWechselGeld kaufen(GetraenkeWunsch auswahl, Muenze... einzahlung);

}
