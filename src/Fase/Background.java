package Fase;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background{
	
	private BufferedImage[] bg = new BufferedImage[1];
	private int posA=0;
	private int posB;
	private boolean stop = false;
		
	public boolean stop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	
	public Background(){
		try {
			bg[0] = ImageIO.read(new File("Things//Sprites//Road//Background.png"));
		} catch (IOException e) {  
			e.printStackTrace();
		}
	}
	
	/**
	 * Esse update vai passando pixel a pixel da imagem da pista
	 */
	public void update(){
		if (!stop){
			posA++;
			posB = posA-1196;
			if(posB==1){
				posA=0;
			}
		}
	}
	
	/** São desenhados(draw) 2 imagens para uma ficar em cima da outra	 */
	public void draw( Graphics2D g2d ) { 
		g2d.drawImage(bg[0], 0, posA, null);
		g2d.drawImage(bg[0], 0, posB, null);
	}

	
}