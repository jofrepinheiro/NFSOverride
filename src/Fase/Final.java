package Fase;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Classe somente para a faixa de fim de corrida no modo Race
 */

public class Final {

	
	private BufferedImage[] bg = new BufferedImage[1];
	private int posY = -500;
	
	public Final(){
		try {
			bg[0] = ImageIO.read(new File("Things//Sprites//Road//end.jpg"));
		} catch (IOException e) {  
			e.printStackTrace();
		}
	}
	
	public void update(int velocidade){
		posY+=velocidade;
	}
	
	public void draw( Graphics2D g2d ) { 
		g2d.drawImage(bg[0], 0, posY, null);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Calibri",Font.ITALIC,150));
		g2d.drawString("WIN", 75, 100);
	}
		
	
}
