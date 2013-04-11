import static org.junit.Assert.*;

import maze.cli.JogoLabirinto;

import org.junit.Test;


public class teste {

	@Test
	public void posHeroi1() {
		
		JogoLabirinto jogo = new JogoLabirinto();
		
		jogo.moverHeroi('s');
		
		assertEquals(2, jogo.myHero.getX());
		assertEquals(1, jogo.myHero.getY());
		
		
		
	}
	
	/*@Test
	public void posHeroi() {
		
	}*/
	

}
