package test;

import main.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameOfLifeTest {

	boolean equalArrays(int[][] array1, int[][] array2) {
		int rows1 = array1.length;
		int columns1 = array1[0].length;
		int rows2 = array2.length;
		int columns2 = array2[0].length;

		if (rows1 != rows2) {
			return false;
		}
		if (columns1 != columns2) {
			return false;
		}

		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < columns1; j++) {
				if (array1[i][j] != array2[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	int numberOfLivingCells(int[][] livingCells){
		int livingCellsCounter =0;
		for(int i=0;i<livingCells.length;i++){
			for(int j=0;j<livingCells[0].length;j++){
				if(livingCells[i][j] == 1 ){
					livingCellsCounter++;
				}
			}
		}
		return livingCellsCounter;
	}
	
	@Test
	public void numberOfLivingCellsTest(){
		int[][] test = {{0,1,0,0,1},
						{1,0,0,1,0},
						{0,0,1,0,1}};
		
		assertEquals(6, numberOfLivingCells(test));
		
	}

	@Test
	public void test1() {
		int[][] cells = { { 0, 0, 0 },
						  { 1, 1, 1 }, 
						  { 0, 0, 0 } };
		int[][] evolutionCells = { { 0, 1, 0 },
								   { 0, 1, 0 },
								   { 0, 1, 0 } };
		GameOfLife game = new GameOfLife(cells);
		game.initFrame();
		game.evolution(3);

		assertTrue(equalArrays(evolutionCells, game.getCellsMatrix()));
	}

	@Test
	public void test2() {
		int[][] cells = { { 0, 0, 0, },
						  { 1, 1, 1 },
						  { 0, 0, 0 } };
		int[][] evolutionCells = { { 0, 1, 0 },
								   { 0, 1, 0 },
								   { 0, 1, 0 } };
		GameOfLife game = new GameOfLife(cells);
		game.initFrame();
		game.evolution(3);
		
		assertTrue(equalArrays(evolutionCells, game.getCellsMatrix()));

	}
	
	@Test
	public void test3(){
		int[][] before = { {0,0,0,0,0},
						   {0,1,1,1,0},
						   {0,1,1,1,0},
						   {0,1,1,1,0},
						   {0,0,0,0,0} };
		
		int[][] after = {{0,0,1,0,0},
				   		 {0,1,0,1,0},
				   		 {1,0,0,0,1},
				   		 {0,1,0,1,0},
				   		 {0,0,01,0,0} };
		
		GameOfLife game = new GameOfLife(before);
		game.initFrame();
		game.evolution(1);
		
		assertTrue(equalArrays(after, game.getCellsMatrix()));
	}
	
}
