/**
 * 
 */
package de.drdboehm.examples.drinkautomat.businesslogic;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.util.List;
import java.util.Optional;

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

/**
 * @author dboehm
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VerkaufControllerTest {

	private static VerkaufController controller;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		URL fileToRead = VerkaufControllerTest.class.getResource("test_beschickung.json");
		controller = new VerkaufController(new File(fileToRead.toURI()));
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

	@Test
	public void test002KaufenMitMuenzenNichtAusreichend() {
		Optional<Fach> identifiziereFachUeberName = controller.getBefuellung().identifiziereFachUeberName("A1");
		Muenze[] l_muenzen = {Muenze.CENTS_50};
		Optional<GetraenkUndWechselGeld> kaufen = controller.kaufen(identifiziereFachUeberName.get(), l_muenzen);
		assertNull("Wechselgeld ist null wenn Einzahlung nicht ausreicht",kaufen.get().getZurück());
		assertNotNull("Nachzahlung ist nicht null wenn Einzahlung nicht ausreicht",kaufen.get().getNachzahlung());
		List<Muenze> nachzahlungen = kaufen.get().getNachzahlung().getNachzahlungen();
		assertEquals("Es fehlen drei Münzen", 1, nachzahlungen.size());
		
	}
}
