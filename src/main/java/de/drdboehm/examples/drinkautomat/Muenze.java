package de.drdboehm.examples.drinkautomat;

public enum Muenze {
	CENTS_10(10),
	CENTS_20(20),
	CENTS_50(50),
	EURO_1(100),
	EURO_2(200);
	
	Muenze(int p_value) {
		this.value = p_value;
	}

	public int getValue() {
		return value;
	}

	private final int value;
}
