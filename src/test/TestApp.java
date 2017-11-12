package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;
import planet.detail.*;

import org.junit.Test;

public class TestApp {
	
	private static String expectedOutput;
	private static Planet planet;
	private static PlanetGatewayTXT gateway;
	
	public String getExpected(String fileName)  throws Exception {
		StringBuffer fileContents = new StringBuffer();
		Scanner fileInput = new Scanner(new File(fileName));
		while(fileInput.hasNextLine())
			fileContents.append(fileInput.nextLine() + "\n");
		expectedOutput = fileContents.toString();
		fileInput.close();
		return expectedOutput;
	}
	
	/* KEEP AS EXAMPLE OF TEST CASE USING getExpected() */
//	@Test
//	public void testVariation1Player1Wins() throws Exception {
//		Menu.setVariation(1);
//		gameOutput = new GameOutput();
//		
//		deck = new Deck();
//		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
//		deck.addCard(new Card(Rank.TWO, Suit.CLUBS));
//		
//		ArrayList<Player> players = new ArrayList<Player>();
//		players.add(new Player("player1", deck, 1));
//		players.add(new Player("player2", deck, 1));
//		
//		turn = new Turn(players, gameOutput);
//		gameOutput.appendEvent(turn.runTurn());
//		assertEquals(getExpected("Variation1Player1Wins.txt"), gameOutput.getStringBuffer().toString());
//	}
	
	@Test
	public void testCase1() throws Exception {		
		assertEquals(PlanetValidator.validatePlanetName("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"),
				false);
	}
	
	@Test
	public void testCase2() throws Exception {
		assertEquals(PlanetValidator.validatePlanetName("Ke$ha"), false);
	}
	
	@Test
	public void testCase3() throws Exception {
		assertEquals(PlanetValidator.validateNumberOfMoons("-6969"), false);
	}
	
	@Test
	public void testCase4() throws Exception {
		assertEquals(PlanetValidator.validateNumberOfMoons("0"), true);
	}
	
	@Test
	public void testCase5() throws Exception {
		assertEquals(PlanetValidator.validateDiameter("-3"), false);
	}
	
	@Test
	public void testCase6() throws Exception {
		assertEquals(PlanetValidator.validateDiameter("999999999"), false);
	}
	
	@Test
	public void testCase7() throws Exception {
		assertEquals(PlanetValidator.validateSurfaceTemperature("-50000000"), false);
	}
	
	@Test
	public void testCase8() throws Exception {
		assertEquals(PlanetValidator.validateSurfaceTemperature("500000000"), false);
	}
	
	@Test
	public void testCase9() throws Exception {
		
	}
	
	@Test
	public void testCase10() throws Exception {
		
	}
}