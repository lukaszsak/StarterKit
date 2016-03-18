package test;
import main.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerGameTest {
	
	@Test public void emptyGameTest(){
		PlayerGame pg = new PlayerGame();
		assertEquals(0,pg.score());
	}
	
	@Test public void maxPointsTest(){
		PlayerGame pg = new PlayerGame();
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		assertEquals(300,pg.score());
	}
	
	@Test
	public void pointsSpareTest() {
		PlayerGame pg = new PlayerGame();
		pg.roll(9);
		pg.roll(1);		//spare
		
		pg.roll(3);
		pg.roll(1);
		assertEquals(17,pg.score() );
	}
	
	@Test
	public void isGameFinishedTest1(){
		PlayerGame pg = new PlayerGame();
		pg.roll(10);
		pg.roll(5);
		pg.roll(4);
		pg.roll(3);
		pg.roll(7);
		assertEquals(false, pg.isFinished());
	}
	
	@Test
	public void isGameFinishedTest2(){
		PlayerGame pg = new PlayerGame();
		pg.roll(4);
		pg.roll(5);
		
		pg.roll(4);
		pg.roll(6);
		
		pg.roll(10);
		
		pg.roll(9);
		pg.roll(1);
		
		pg.roll(10);
		
		pg.roll(9);
		pg.roll(1);
		
		pg.roll(10);
		
		pg.roll(9);
		pg.roll(1);
		
		pg.roll(10);

		pg.roll(8);		//last round begin
		pg.roll(2);
		pg.roll(8);
		
		assertEquals(true,pg.isFinished());	
	}
	
	@Test public void isGameFinishedTest3(){
		PlayerGame pg = new PlayerGame();
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		
		pg.roll(10);	//last round begin
		pg.roll(10);
		assertEquals(false,pg.isFinished());
	}
	
	@Test public void isGameFinishedTest4(){
		PlayerGame pg = new PlayerGame();
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		
		pg.roll(10);	//last round begin
		pg.roll(10);
		pg.roll(10);
		assertEquals(true,pg.isFinished());
	}
	
	@Test public void isGameFinishedTest5(){
		PlayerGame pg = new PlayerGame();
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		pg.roll(10);
		
		pg.roll(3);	//last round begin
		pg.roll(7);
		assertEquals(false,pg.isFinished());
	}
	
}
