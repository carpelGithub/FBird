package FlappyBird;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Rectangle;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener{

	public static FlappyBird flappyBird;
	
	public Renderer renderer = new Renderer();
	
	//Crea un ogetto di tipo rettangolo(Bird)
	public Rectangle bird; 

	public ArrayList<Rectangle> columns; 
	
	public Random rand; 
	
	public int ticks;
	public int yMotion;
	
	//Larghezza dell'applicazione
	public final int WIDTH = 800;
	//Altezza dell'applicazione
	public final int HEIGHT = 800;
	
	public FlappyBird() {
		
		JFrame frame = new JFrame();
		Timer timer = new Timer(20, this);
		
		renderer = new Renderer();
		rand = new Random();
		
		//Aggiungo al frame l'ogetto renderer
		frame.add(renderer);	
		//Titolo del programma
		frame.setTitle("Flappy Bird");
		//Termina il programma quando viene premuta la "X" del frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Setta la grandezza
		frame.setSize(WIDTH, HEIGHT);
		//Rende visibile il frame
		frame.setVisible(true);
		//Rende non regolabile il frame
		frame.setResizable(false);
		
		//Setta la posizione centrale e la grandezza
		bird = new Rectangle((WIDTH/2)-10, (HEIGHT/2)-10, 20, 20);
		columns = new ArrayList<Rectangle>();
		
		addColumns(true);
		addColumns(true);
		addColumns(true);
		addColumns(true);
		
		timer.start();
		
	}	
	
	public void addColumns(boolean start) {
		int space = 300;
		int width = 100;
		int height = 50	+ rand.nextInt(300);
		
		if(start)
		{
			columns.add(new Rectangle(WIDTH + width + columns.size()*300, HEIGHT - height - 120,
					width, height));
			columns.add(new Rectangle(WIDTH + width + (columns.size()-1)*300, 0, width, 
					HEIGHT - height - space));			
		}
		else 
		{
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120,
					width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, 
					HEIGHT - height - space));
		}

	}
	
	//Estetica Sfondo
	public void repaint(Graphics g) {
		
		//Sfondo
		//Setta il colore 
		g.setColor(Color.CYAN);
		//Grandezza di quanto deve ricoprire
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Terreno
		g.setColor(Color.ORANGE.darker());
		//(x, y, width, height): x=0(bordo a sinistra) y=0(bordo in alto) 
		g.fillRect(0, HEIGHT-120, WIDTH, 120);		
		//Prato
		g.setColor(Color.GREEN);
		g.fillRect(0, HEIGHT-120, WIDTH, 20);
		//Bird
		g.setColor(Color.RED);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
		
	}
	//Estetica Tubi
	public void paintColumn(Graphics g, Rectangle column) {
			
		g.setColor(Color.GREEN.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
		
		
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		ticks++;
		
		if(ticks % 2 == 0 && yMotion<15) 
		{
			yMotion += 2;	
		}
		
		bird.y += yMotion; 
		renderer.repaint();		
	}
	
	public static void main(String[] args) {
		flappyBird = new FlappyBird();
	}




	
	
	
	
}
