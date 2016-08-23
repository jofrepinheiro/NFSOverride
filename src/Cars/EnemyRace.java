package Cars;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EnemyRace extends Car {
	/** @Override race */
	private BufferedImage[] race = new BufferedImage[8];
	
	public EnemyRace() { 
		
		setPosX(165);	setPosY(-800);

		setImgCar(7);
		try {
			for( int i = 0; i < race.length; i++ )
				race[i] = ImageIO.read(new File("Things//Sprites//Race//Race" + i +".png"));
		} catch (IOException e) {
			e.printStackTrace();  
		}
	}
	
	/**
	 * 
	 * @param pos É a posição do carro principal, 
	 * a "inteligência artificial"dos inimigos
	 * 
	 */
	public void ChangeCar(int pos){
		if (pos == 0){
			setPosX(55);
		} else if (pos == 1){
			setPosX(165);
		} else {
			setPosX(275);
		}
	}
	
	/**
	 * 
	 * @param velocidade É a velocidade do carro principal, 
	 * então se o carro principal estiver abaixo de 50 Mph
	 * ele nunca vai passar de nenhum carro 
	 * 
	 */
	public void update(int velocidade){
		setPosY(getPosY()+(velocidade - 50));
	}
	
	public void draw( Graphics2D g2d ) { g2d.drawImage(race[getImgCar()],getPosX(),getPosY(), 
			getLargura(), getAltura(),null); }
}
