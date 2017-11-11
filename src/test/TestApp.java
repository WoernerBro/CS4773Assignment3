package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;
import planet.detail.*;

import org.junit.Test;

public class TestApp {
	
	private static String expectedOutput;
	
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
		assertEquals("", "");
	}
}