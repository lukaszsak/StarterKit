package main;

import java.awt.Color;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOfLife {
	private final static int DELAY = 200;	// one Round of evolution in miliseconds
	private final static int ROUNDS_OF_EVOLUTION = 20;
	private final static int CELL_SIZE = 20;	//cell size in GUI in pixels
	private final static int FRAME_SIZE = 900;	
	
	private int[][] cellsMatrix; // Matrix of Living Cells
	private int[][] neighborhoodMatrix;
	
	public int[][] getNeighborhoodMatrix() {
		return neighborhoodMatrix;
	}

	public void setNeighborhoodMatrix(int[][] neighborhoodMatrix) {
		this.neighborhoodMatrix = neighborhoodMatrix;
	}

	public void setCellsMatrix(int[][] cellsMatrix) {
		this.cellsMatrix = cellsMatrix;
	}
	
	/* this method is only for Example Class purposes */
	public void setCellMatrix(int row,int column){
		cellsMatrix[row][column] = 1;
	}
	
	/*
	 * guiFrame and guiLivingCells fields are used only to present results of
	 * evolution in GUI
	 */
	private JFrame guiFrame;
	JButton[][] guiLivingCells;

	public int[][] getCellsMatrix() {
		return cellsMatrix;
	}

	public GameOfLife() {

	}

	public GameOfLife(int[][] cellsMatrix) {
		this.cellsMatrix = cellsMatrix;
		neighborhoodMatrix = new int[cellsMatrix.length][cellsMatrix[0].length];
	}

	private void nextGeneration() {
		countNeighborhoodMatrix();
		countNewCellsMatrix();
		clearNeighborhoodMatrix();
	}

	/* counts and display next steps of generations of living cells */
	/* I've set visibility to public only for Example and testing classes purposes */
	public void evolution(int rounds) {
		for (int i = 0; i < rounds; i++) {
			nextGeneration();
			drawEvolution();
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void countNeighborhoodMatrix() {
		int maxRow = cellsMatrix.length;
		int maxColumn = cellsMatrix[0].length;

		for (int i = 0; i < maxRow; i++) {

			for (int j = 0; j < maxColumn; j++) {
				if (cellsMatrix[i][j] == 1) {
					increaseNeighborhoodCounter(i, j);
				}
			}
		}
	}

	private void countNewCellsMatrix() {
		int[][] newCellsMatrix = new int[cellsMatrix.length][cellsMatrix[0].length];
		for (int i = 0; i < newCellsMatrix.length; i++) {
			for (int j = 0; j < newCellsMatrix[i].length; j++) {
				newCellsMatrix[i][j] = 0;
			}
		}

		for (int i = 0; i < cellsMatrix.length; i++) {
			for (int j = 0; j < cellsMatrix[i].length; j++) {
				if (neighborhoodMatrix[i][j] == 3) {
					newCellsMatrix[i][j] = 1;
				}
				if (neighborhoodMatrix[i][j] == 2 && cellsMatrix[i][j] == 1) {
					newCellsMatrix[i][j] = 1;
				}
			}
		}
		cellsMatrix = newCellsMatrix;
	}

	private void clearNeighborhoodMatrix() {
		for (int i = 0; i < neighborhoodMatrix.length; i++) {
			for (int j = 0; j < neighborhoodMatrix[i].length; j++) {
				neighborhoodMatrix[i][j] = 0;
			}
		}
	}

	/*
	 * for each living Cell increase value of Neighborhood by 1 for every
	 * neighboring Cell
	 */
	private void increaseNeighborhoodCounter(int row, int column) {
		int maxRow = cellsMatrix.length;
		int maxColumn = cellsMatrix[0].length;
		int fromRow = 0, toRow = maxRow - 1;
		int fromColumn = 0, toColumn = maxColumn - 1;
		if (row > 0) {
			fromRow = row - 1;
		}
		if (row < maxRow - 1) {
			toRow = row + 1;
		}
		if (column > 0) {
			fromColumn = column - 1;
		}
		if (column < maxColumn - 1) {
			toColumn = column + 1;
		}

		for (int i = fromRow; i <= toRow; i++) {
			for (int j = fromColumn; j <= toColumn; j++) {
				neighborhoodMatrix[i][j]++;
			}
		}

		neighborhoodMatrix[row][column]--;
	}

	private void drawEvolution() {
		for (int i = 0; i < cellsMatrix.length; i++) {
			for (int j = 0; j < cellsMatrix[i].length; j++) {

				if (cellsMatrix[i][j] == 1) {
					guiLivingCells[i][j].setBackground(Color.GREEN);

				} else {
					guiLivingCells[i][j].setBackground(null);
				}
			}
		}
	}
	
	/* I've set visibility to public only for Example and testing classes purposes */
	public void initFrame() {
		this.guiFrame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		guiFrame.setSize(FRAME_SIZE, FRAME_SIZE);
		guiFrame.setLocation(200, 200);

		guiLivingCells = new JButton[cellsMatrix.length][cellsMatrix[0].length];
		for (int i = 0; i < cellsMatrix.length; i++) {
			for (int j = 0; j < cellsMatrix[i].length; j++) {
				guiLivingCells[i][j] = new JButton();
				guiLivingCells[i][j].setSize(CELL_SIZE, CELL_SIZE);
				guiLivingCells[i][j].setLocation(j * CELL_SIZE, i * CELL_SIZE);
				guiLivingCells[i][j].setEnabled(false);
				if (cellsMatrix[i][j] == 1) {
					guiLivingCells[i][j].setBackground(Color.GREEN);

				}
				panel.add(guiLivingCells[i][j]);
			}
		}
		guiFrame.add(panel);
		guiFrame.setVisible(true);
		guiFrame.setAlwaysOnTop(true);

	}

	private void initializeCellsMatrix() {
		System.out.println("give number of rows in game of life : ");
		Scanner sc = new Scanner(System.in);
		int rows = Integer.parseInt(sc.nextLine());
		System.out.println("give number of columns in game of life : ");
		int columns = Integer.parseInt(sc.nextLine());
		cellsMatrix = new int[rows][columns];
		neighborhoodMatrix = new int[rows][columns];
		System.out.println("give " + rows + " lines of '0's and '1's without any seperation.");
		System.out.println("Each line should be length of "+columns);
		for (int i = 0; i < rows; i++) {
			String row = sc.nextLine();
			for (int j = 0; j < columns; j++) {
				cellsMatrix[i][j] = Character.getNumericValue(row.charAt(j));
			}
		}
		sc.close();
	}

	public static void main(String[] args) {
		GameOfLife game = new GameOfLife();

		game.initializeCellsMatrix();
		game.initFrame();
		game.evolution(ROUNDS_OF_EVOLUTION);
	}
}