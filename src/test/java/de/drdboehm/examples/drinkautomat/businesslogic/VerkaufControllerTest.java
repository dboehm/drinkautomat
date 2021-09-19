/**
 * 
 */
package de.drdboehm.examples.drinkautomat.businesslogic;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.drdboehm.examples.drinkautomat.entities.Fach;
import de.drdboehm.examples.drinkautomat.entities.GetraenkUndWechselGeld;
import de.drdboehm.examples.drinkautomat.entities.Muenze;
import de.drdboehm.examples.drinkautomat.entities.Startgeld;
import de.drdboehm.examples.drinkautomat.state.Befuellung;

/**
 * @author dboehm
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VerkaufControllerTest {

	private static VerkaufController controller;

	/**
	 * Die Methode lädt ein Test-Setting mit 4 Fächern in den Controller als
	 * Befuellung Die Tests werden sequentiell mittels {@link FixMethodOrder}
	 * durchgeführt, damit ein kompletter automatischer Test aller Funktionalitäten
	 * gewährleistet ist, inkl. finalem Kassensturz A1: "getraenk": {"name": "Coco
	 * Cola", "preis": 130}, "menge": 2 A2: "getraenk": {"name": "Bier", "preis":
	 * 200}, "menge": 1 B1: "getraenk": {"name": "Wasser medium", "preis": 160},
	 * "menge": 0 B2: "getraenk": {"name": "Wasser still", "preis": 160}, "menge": 1
	 * und Startgeld: "CENTS_10"="anzahl": 5, "CENTS_20"="anzahl": 2, "CENTS_50",
	 * "anzahl": 1, "EURO_1"="anzahl": 1, "EURO_2"= "anzahl": 1 }
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass

	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		controller = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		URL fileToRead = VerkaufControllerTest.class.getResource("test_beschickung.json");
		controller = new VerkaufController(new File(fileToRead.toURI()));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test001ControllerBefüllungNotNull() {
		assertNotNull("Controller darf nicht null sein", controller);
		assertNotNull("Befüllung darf nicht null sein", controller.getBefuellung());
	}

	/**
	 * Es wird * A1: "getraenk": {"name": "Coco Cola", "preis": 130}, "menge": 2
	 * gewählt, aber nur {Muenze.CENTS_50} eingeworfen Es fehlen 80 cents, erwartete
	 * Zerstückelung des Algorithmus für Zahlvorschlag ist {Muenze.CENTS_50,
	 * Muenze.CENTS_20, Muenze_CENTS_10} und Startgeld bleibt unverändert:
	 * "CENTS_10"="anzahl": 5, "CENTS_20"="anzahl": 2, "CENTS_50", "anzahl": 1,
	 * "EURO_1"="anzahl": 1, "EURO_2"= "anzahl": 1 Der Algorithmus ist identisch mit
	 * dem für die Wechselgeldberechnung (derzeit keine Alternativen implementiert)
	 */
	@Test
	public void test002KaufenMitMuenzenNichtAusreichend() {
		Optional<Fach> identifiziereFachUeberName = controller.identifiziereFachUeberName("A1");
		Befuellung befuellung = controller.getBefuellung();
		List<Startgeld> startgelder = befuellung.getStartgeld();
		List<Fach> faecher = befuellung.getFaecher();
		Muenze[] l_muenzen = { Muenze.CENTS_50 };
		Optional<GetraenkUndWechselGeld> kaufen = controller.kaufen(identifiziereFachUeberName.get(), l_muenzen);
		assertNull("Wechselgeld ist null wenn Einzahlung nicht ausreicht", kaufen.get().getWechelgeld());
		assertNotNull("Nachzahlung ist nicht null wenn Einzahlung nicht ausreicht", kaufen.get().getNachzahlung());
		List<Muenze> nachzahlungen = kaufen.get().getNachzahlung().getNachzahlungen();
		assertEquals("Es fehlen drei Münzen", 3, nachzahlungen.size());
		assertTrue(nachzahlungen.contains(Muenze.CENTS_10));
		assertTrue(nachzahlungen.contains(Muenze.CENTS_20));
		assertTrue(nachzahlungen.contains(Muenze.CENTS_50));
		assertEquals(1, nachzahlungen.stream().filter(muenze -> muenze.equals(Muenze.CENTS_10))
				.collect(Collectors.toList()).size());
		assertEquals(1, nachzahlungen.stream().filter(muenze -> muenze.equals(Muenze.CENTS_20))
				.collect(Collectors.toList()).size());
		assertEquals(1, nachzahlungen.stream().filter(muenze -> muenze.equals(Muenze.CENTS_50))
				.collect(Collectors.toList()).size());
		assertEquals(0, nachzahlungen.stream().filter(muenze -> muenze.equals(Muenze.EURO_1))
				.collect(Collectors.toList()).size());
		assertEquals(0, nachzahlungen.stream().filter(muenze -> muenze.equals(Muenze.EURO_2))
				.collect(Collectors.toList()).size());
		// check Getraenk not null
		assertNotNull(kaufen.get().getGetraenk());
		// Getraenkeauswahl noch vorhanden
		assertEquals("Coco Cola", kaufen.get().getGetraenk().getName());
		// Aber Getraenk ist nicht aus Befuellung entnommen
		befuellungCheck(faecher, 2, 1, 0, 1);
		// die Einzahlung ist nicht dem Startgeld zugefügt
		startGeldCheck(startgelder, 5, 1, 1, 1, 1);
	}

	/**
	 * /** Es wird * A1: "getraenk": {"name": "Coco Cola", "preis": 130}, "menge": 2
	 * gewählt, aber nur {Muenze.CENTS_50, Muenze.EURO_1} eingeworfen Es fehlen 20
	 * cents zuveil, erwartete Zerstückelung des Algorithmus für Zahlvorschlag ist
	 * {Muenze.CENTS_20} und Startgeld wird verändert: "CENTS_10"="anzahl": 5,
	 * "CENTS_20"="anzahl": 1, "CENTS_50", "anzahl": 2, "EURO_1"="anzahl": 2,
	 * "EURO_2"= "anzahl": 1 Der Algorithmus ist für die Wechselgeldberechnung und
	 * selectiert immer die erste größte Münze die in die Auszahlungsdifferenz passt
	 * (derzeit keine Alternativen implementiert)
	 */
	@Test
	public void test003KaufenMitMuenzenAusreichendMitWechselGeldUndGetraenk() {
		Optional<Fach> identifiziereFachUeberName = controller.identifiziereFachUeberName("A1");
		Befuellung befuellung = controller.getBefuellung();
		List<Startgeld> startgelder = befuellung.getStartgeld();
		List<Fach> faecher = befuellung.getFaecher();
		Muenze[] l_muenzen = { Muenze.CENTS_50, Muenze.EURO_1 };
		Optional<GetraenkUndWechselGeld> kaufen = controller.kaufen(identifiziereFachUeberName.get(), l_muenzen);
		assertNotNull("Wechselgeld ist nicht null da Einzahlung ausreicht", kaufen.get().getWechelgeld());
		assertNull("Nachzahlung ist null da Einzahlung ausreicht", kaufen.get().getNachzahlung());
		List<Muenze> wechselgeldListe = kaufen.get().getWechelgeld().getZurueck();
		assertEquals("Es wird eine Münze zurückgegeben", 1, wechselgeldListe.size());
		assertTrue(wechselgeldListe.contains(Muenze.CENTS_20));
		assertEquals(0, wechselgeldListe.stream().filter(muenze -> muenze.equals(Muenze.CENTS_10))
				.collect(Collectors.toList()).size());
		assertEquals(1, wechselgeldListe.stream().filter(muenze -> muenze.equals(Muenze.CENTS_20))
				.collect(Collectors.toList()).size());
		assertEquals(0, wechselgeldListe.stream().filter(muenze -> muenze.equals(Muenze.CENTS_50))
				.collect(Collectors.toList()).size());
		assertEquals(0, wechselgeldListe.stream().filter(muenze -> muenze.equals(Muenze.EURO_1))
				.collect(Collectors.toList()).size());
		assertEquals(0, wechselgeldListe.stream().filter(muenze -> muenze.equals(Muenze.EURO_2))
				.collect(Collectors.toList()).size());
		// check Getraenk not null
		assertNotNull(kaufen.get().getGetraenk());
		// Getraenkeauswahl noch vorhanden
		assertEquals("Coco Cola", kaufen.get().getGetraenk().getName());
		// Aber Getraenk ist aus Befuellung entnommen
		befuellungCheck(faecher, 1, 1, 0, 1);
		// die Einzahlung ist dem Startgeld zugefügt und die Auszahl entnommen
		startGeldCheck(startgelder, 5, 0, 2, 2, 1);
	}

	@Test
	public void test004KaufenMitMuenzenAusreichendAberWechselGeldRückgabeNichtMoeglichKeinGetraenk() {
		// Fach B2 enthält: "getraenk": {"name": "Wasser still", "preis": 160}, "menge":
		// 1
		Optional<Fach> identifiziereFachUeberName = controller.identifiziereFachUeberName("B2");
		Befuellung befuellung = controller.getBefuellung();
		List<Startgeld> startgelder = befuellung.getStartgeld();
		List<Fach> faecher = befuellung.getFaecher();
		Muenze[] l_muenzen = { Muenze.EURO_2 };
		Optional<GetraenkUndWechselGeld> kaufen = controller.kaufen(identifiziereFachUeberName.get(), l_muenzen);
		// TODO es werden zur Zeit keine Zahlungsalternativen berechnet, z.B. Vorschlag
		// weitere Einzahlung Muenze.CENTS_10 und Ausgabe Muenze.CENTS_50)
		assertNotNull("Wechselgeld ist nicht null da Einzahlung ausreicht", kaufen.get().getWechelgeld());
		assertNull("Nachzahlung ist null da Einzahlung ausreicht", kaufen.get().getNachzahlung());
		
		List<Muenze> wechselgeldListe = kaufen.get().getWechelgeld().getZurueck();
		assertTrue(wechselgeldListe.contains(Muenze.CENTS_20));
		assertEquals(0, wechselgeldListe.stream().filter(muenze -> muenze.equals(Muenze.CENTS_10))
				.collect(Collectors.toList()).size());
		assertEquals(2, wechselgeldListe.stream().filter(muenze -> muenze.equals(Muenze.CENTS_20))
				.collect(Collectors.toList()).size());
		assertEquals(0, wechselgeldListe.stream().filter(muenze -> muenze.equals(Muenze.CENTS_50))
				.collect(Collectors.toList()).size());
		assertEquals(0, wechselgeldListe.stream().filter(muenze -> muenze.equals(Muenze.EURO_1))
				.collect(Collectors.toList()).size());
		assertEquals(0, wechselgeldListe.stream().filter(muenze -> muenze.equals(Muenze.EURO_2))
				.collect(Collectors.toList()).size());
		// check Getraenk not null
		assertNotNull(kaufen.get().getGetraenk());
		// Getraenkeauswahl noch vorhanden
		assertEquals("Wasser still", kaufen.get().getGetraenk().getName());
		// Aber Getraenk ist nicht aus Befuellung entnommen
		befuellungCheck(faecher, 2, 1, 0, 1);
		// die Einzahlung ist nicht dem Startgeld zugefügt und Wechselgeld nicht entnommen
		startGeldCheck(startgelder, 5, 1, 1, 1, 1);
	}
	
	@Test
	public void test005KaufenEgalMitWievielMuenzenAberGetraenkIstNichtInBefuellung() {
		Optional<Fach> identifiziereFachUeberName = controller.identifiziereFachUeberName("B1");
		Befuellung befuellung = controller.getBefuellung();
		List<Startgeld> startgelder = befuellung.getStartgeld();
		List<Fach> faecher = befuellung.getFaecher();
		Muenze[] l_muenzen = { Muenze.CENTS_10 };
		Optional<GetraenkUndWechselGeld> kaufen = controller.kaufen(identifiziereFachUeberName.get(), l_muenzen);
		assertTrue(kaufen.isEmpty());
		assertFalse(controller.istGetraenkeWunschInFachVorhanden(identifiziereFachUeberName.get()));
		befuellungCheck(faecher, 2, 1, 0, 1);
		// die Einzahlung ist nicht dem Startgeld zugefügt und Wechselgeld nicht entnommen
		startGeldCheck(startgelder, 5, 1, 1, 1, 1);
	}

	private void befuellungCheck(List<Fach> faecher, Integer a1, Integer a2, Integer b1, Integer b2) {
		assertEquals(a1.intValue(), faecher.stream().filter(fach -> fach.getName().equals("A1"))
				.collect(Collectors.toList()).get(0).getMenge());
		assertEquals(a2.intValue(), faecher.stream().filter(fach -> fach.getName().equals("A2"))
				.collect(Collectors.toList()).get(0).getMenge());
		assertEquals(b1.intValue(), faecher.stream().filter(fach -> fach.getName().equals("B1"))
				.collect(Collectors.toList()).get(0).getMenge());
		assertEquals(b2.intValue(), faecher.stream().filter(fach -> fach.getName().equals("B2"))
				.collect(Collectors.toList()).get(0).getMenge());
	}

	private void startGeldCheck(List<Startgeld> startgelder, Integer cents10, Integer cents20, Integer cents50,
			Integer euro1, Integer euro2) {
		assertEquals(cents10, startgelder.stream().filter(startgeld -> startgeld.getMuenze().equals(Muenze.CENTS_10))
				.collect(Collectors.toList()).get(0).getAnzahl());
		assertEquals(cents20, startgelder.stream().filter(startgeld -> startgeld.getMuenze().equals(Muenze.CENTS_20))
				.collect(Collectors.toList()).get(0).getAnzahl());
		assertEquals(cents50, startgelder.stream().filter(startgeld -> startgeld.getMuenze().equals(Muenze.CENTS_50))
				.collect(Collectors.toList()).get(0).getAnzahl());
		assertEquals(euro1, startgelder.stream().filter(startgeld -> startgeld.getMuenze().equals(Muenze.EURO_1))
				.collect(Collectors.toList()).get(0).getAnzahl());
		assertEquals(euro2, startgelder.stream().filter(startgeld -> startgeld.getMuenze().equals(Muenze.EURO_2))
				.collect(Collectors.toList()).get(0).getAnzahl());

	}
}
