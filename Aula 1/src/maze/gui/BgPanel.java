package maze.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class BgPanel extends JPanel {
	Image bg = new ImageIcon("labyrinth.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

class Elemento extends JPanel{
	Image bg;
	boolean clicado = false;
	String path;
	
	Graphics g;
	/**
	 * 
	 * 
	 * @param path caminho para a imagem
	 */
	Elemento(String path){
		bg = new ImageIcon(path).getImage();
		this.path = path;
	}
	
	@Override
	public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        this.g = g;
    }
	/**
	 * 
	 * @param path caminho para a imagem
	 * altera caminho da imagem
	 */
    public void setImage(String path){
    	System.out.println(bg.toString());
    	bg = new ImageIcon(path).getImage();
    	this.path = path;
    }
    /**
     * 
     * @return o caminho da imagem
     */
    public String getPath(){
    	return path;
    }
    

}