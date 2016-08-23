package Cars;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EnemyArcade extends Car {
	/** @Override enemy */
	private BufferedImage[] enemy = new BufferedImage[4];
	/** posYInicial serve para salvar a posição  */
	private int posYInicial;
	
	public EnemyArcade(int pos) { 
		setPosX(165);	setPosY(pos); posYInicial = pos;
		
		//O try/catch a seguir serve para passar as imagens para o array  
		try {
			int n = (int) (java.lang.Math.random() * 4 + 1);  
			enemy[0] = ImageIO.read(new File("Things//Sprites//Enemy//Enemy" + n +".png"));
		} catch (IOException e) {
			e.printStackTrace();  
		}
	}
	
	public int getPosYInicial() {
		return posYInicial;
	}
	
	/**
	 * 
	 * @param pos É a posição do carro principal, 
	 * a "inteligência artificial"dos inimigos
	 * 
	 * E a mudança de imagem que representa o inimigo
	 * é feita com um random simples 
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
		try {
			int n = (int) (java.lang.Math.random() * 4 + 1);  
			enemy[0] = ImageIO.read(new File("Things//Sprites//Enemy//Enemy" + n +".png"));
		} catch (IOException e) {
			e.printStackTrace();  
		}
	}
	
	/**
	 * @param velocidade É a velocidade do carro principal
	 */
	public void update(int velocidade){
		setPosY(getPosY()+velocidade);
	}
	
	public void draw( Graphics2D g2d ) { g2d.drawImage(enemy[0],getPosX(),getPosY(), 
			getLargura(), getAltura(),null); }
	
	
}