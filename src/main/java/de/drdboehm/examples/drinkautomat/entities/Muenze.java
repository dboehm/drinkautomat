package de.drdboehm.examples.drinkautomat.entities;

public enum Muenze {
	CENTS_10(10),
	CENTS_20(20),
	CENTS_50(50),
	EURO_1(100),
	EURO_2(200);
	
	Muenze(Integer p_value) {
		this.value = p_value;
	}

	public Integer getValue() {
		return value;
	}

	private final Integer value;
	
}
