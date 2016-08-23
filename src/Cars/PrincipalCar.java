package Cars;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PrincipalCar extends Car implements KeyListener {
	/** @Override carro */
	private BufferedImage[] carro = new BufferedImage[1];
	/** Vai ser usado para o "velocímetro" */
	private int velocidade = 0; 
	
	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	/** Controla a posição do carro na pista, varia de 0 há 2 */
	private int position = 1;
	

	/** Construtor direciona onde o carro vai aparecer e 
	 *  carrega a imagem do carro no array para ser colocada na tela 
	 */
	
	public PrincipalCar() { 
		
		setPosX(165);	setPosY(500);
		
		try {
			carro[0] = ImageIO.read(new File("Things//Sprites//Car//Car" + ( 0 ) +".png"));
		} catch (IOException e) {  
			e.printStackTrace();  
		}
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	/** Desenha a imagem (carro) na posição posX e posY	 */
	public void draw( Graphics2D g2d ) { g2d.drawImage(carro[0],getPosX(), 
			getPosY(), getLargura(), getAltura(),null); } 
	

	/**
	 *  32 é o código do Espaço
	 *  39 é o código da seta direita
	 *  37 é o código da seta esquerda
	 *  38 é o código para a tecla para cima
	 *  37 é o código para a tecla para baixo
	 */
	public void keyPressed(KeyEvent e) {		
		
		if (e.getKeyCode() == 32) {
			velocidade = 150;
		}
		
		if ( e.getKeyCode() == 39 || e.getKeyCode() == 68){
			if (getPosition() >= 0 && getPosition() < 2){
				setPosX(getPosX()+110);
				setPosition(getPosition() + 1);
			}
		}

		if ( e.getKeyCode() == 37 || e.getKeyCode() == 65){
			if (getPosition() > 0 && getPosition() < 3){
				setPosX(getPosX()-110);
				setPosition(getPosition() - 1);
			}
		}
	
		if (e.getKeyCode() == 38 || e.getKeyCode() == 87){
			if (velocidade < 80){
				velocidade += 2;
			}
		}

		if (e.getKeyCode() == 40 || e.getKeyCode() == 83){
			if (velocidade > 40){
				velocidade -= 1.5;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (velocidade == 150){
			velocidade = 40;
		}
	}

	public void keyTyped(KeyEvent e) {}

	public int getVelocidade() { return velocidade; }
}

