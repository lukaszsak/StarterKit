package main;

/*	This is only for example purpose class.
 * 	It shows evolution of 'Gospel Glider Gun'
 */
public class Example {


	void gospelGliderGun(GameOfLife game) {
		
	game.setCellsMatrix(new int[60][60]);
	game.setNeighborhoodMatrix(new int[60][60]);

	game.setCellMatrix(1,25);

	game.setCellMatrix(2,23);
	game.setCellMatrix(2,25);

	game.setCellMatrix(3,13);
	game.setCellMatrix(3,14);
	game.setCellMatrix(3,21);
	game.setCellMatrix(3,22);
	game.setCellMatrix(3,35);
	game.setCellMatrix(3,36);

	game.setCellMatrix(4,12);
	game.setCellMatrix(4,16);
	game.setCellMatrix(4,21);
	game.setCellMatrix(4,22);
	game.setCellMatrix(4,35);
	game.setCellMatrix(4,36);

	game.setCellMatrix(5,1);
	game.setCellMatrix(5,2);
	game.setCellMatrix(5,11);
	game.setCellMatrix(5,17);
	game.setCellMatrix(5,21);
	game.setCellMatrix(5,22);

	game.setCellMatrix(6,1);
	game.setCellMatrix(6,2);
	game.setCellMatrix(6,11);
	game.setCellMatrix(6,15);
	game.setCellMatrix(6,17);
	game.setCellMatrix(6,18);
	game.setCellMatrix(6,23);
	game.setCellMatrix(6,25);

	game.setCellMatrix(7,11);
	game.setCellMatrix(7,17);
	game.setCellMatrix(7,25);

	game.setCellMatrix(8,12);
	game.setCellMatrix(8,16);

	game.setCellMatrix(9,13);
	game.setCellMatrix(9,14);
}

	public static void main(String[] args){
		Example example= new Example();
		GameOfLife game = new GameOfLife();	
		example.gospelGliderGun(game);
		game.initFrame();
		game.evolution(100);
	}
	
}
