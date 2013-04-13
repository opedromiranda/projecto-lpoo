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
	
	Elemento(String path){
		bg = new ImageIcon(path).getImage();
		this.path = path;
	}
	
	@Override
	public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        this.g = g;
    }
    public void setImage(String path){
    	System.out.println(bg.toString());
    	bg = new ImageIcon(path).getImage();
    	this.path = path;
    }
    
    boolean foiClicado(){
    	return clicado;
    }
    void setfoiClicado(boolean b){
    	clicado = b;
    }
    
    public String getPath(){
    	return path;
    }
    

}

class caminho extends JPanel{
	Image bg = new ImageIcon("caminho.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

class dragao extends JPanel{
	Image bg = new ImageIcon("dragao.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

class aguia extends JPanel{
	Image bg = new ImageIcon("aguia.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

class ash extends JPanel{
	Image ash = new ImageIcon("ash.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(ash, 0, 0, getWidth(), getHeight(), this);
    }
}

class heroi extends JPanel{
	Image bg = new ImageIcon("heroi.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
    

}

class espada extends JPanel{
	Image bg = new ImageIcon("espada.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

class parede extends JPanel{
	Image bg = new ImageIcon("parede.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}