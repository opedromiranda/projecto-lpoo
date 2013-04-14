import static org.junit.Assert.*;

import maze.cli.JogoLabirinto;
import maze.logic.Dragon;

import org.junit.Test;


public class teste {

	
	@Test
	public void morrerPeloDragao() {
		JogoLabirinto jogo = new JogoLabirinto();

		jogo.myHero.setHasSword(false);
		Dragon myDragon = new Dragon();
		myDragon.setX(2);
		myDragon.setY(1);
		
		jogo.addDragon(myDragon);
		
		jogo.processOpSemMoveDragon("d");

		assertEquals(false, jogo.myHero.getVida());
	}
	
	@Test
	public void matarDragao() {
		JogoLabirinto jogo = new JogoLabirinto();

		jogo.myHero.setHasSword(true);
		Dragon dragonTestes = new Dragon();
		dragonTestes.setX(1);
		dragonTestes.setY(1);
		
		jogo.addDragon(dragonTestes);
		
		jogo.processOpSemMoveDragon("d");

		assertEquals(true, jogo.dragonTestes.dragonIsInHell);
	}
	
	@Test
	public void moverHeroiCaminho() {

		JogoLabirinto jogo = new JogoLabirinto();

		jogo.processOp("s");


		assertEquals(1, jogo.myHero.getX());
		assertEquals(2, jogo.myHero.getY());
	}

	@Test
	public void moverHeroiParede() {
		JogoLabirinto jogo = new JogoLabirinto();

		jogo.processOp("a");


		assertEquals(1, jogo.myHero.getX());
		assertEquals(1, jogo.myHero.getY());
	}
	
	
	
	@Test
	public void heroiApanhaEspada() {
		JogoLabirinto jogo = new JogoLabirinto();

		jogo.processOp("s");
		jogo.processOp("s");
		jogo.processOp("s");
		jogo.processOp("s");
		jogo.processOp("s");
		jogo.processOp("s");
		jogo.processOp("s");
		
		

		assertEquals('A', jogo.myHero.getDrawing());
	}
	
	@Test
	public void ganhar() {
		JogoLabirinto jogo = new JogoLabirinto();

		jogo.myHero.setHasSword(true);
		Dragon dragonTestes = new Dragon();
		dragonTestes.setX(2);
		dragonTestes.setY(1);
		
		jogo.addDragon(dragonTestes);
		
		
		jogo.processOpSemMoveDragon("d");		
		
		jogo.myHero.setX(8);
		jogo.myHero.setY(5);
		jogo.processOpSemMoveDragon("d");

		assertEquals(9,jogo.myHero.getX());
		assertEquals(5,jogo.myHero.getY());
	}
	
	@Test
	public void naoSair() {
		JogoLabirinto jogo = new JogoLabirinto();

		jogo.myHero.setHasSword(true);
		Dragon dragonTestes = new Dragon();
		dragonTestes.setX(3);
		dragonTestes.setY(1);
		
		jogo.addDragon(dragonTestes);
			
		
		jogo.myHero.setX(8);
		jogo.myHero.setY(5);
		jogo.processOpSemMoveDragon("d");

		assertEquals(8,jogo.myHero.getX());
		assertEquals(5,jogo.myHero.getY());
	}
	
	
}
