package Cars;
import java.awt.image.BufferedImage;

public class Car  {
	
	protected BufferedImage[] carro = new BufferedImage[0]; 
	
	private int imgCar = 4;
	private int posX,posY; //Eixo x, y do carro	
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getLargura() {
		return largura;
	}

	public int getAltura() {
		return altura;
	}

	private final int largura = 90, altura = 210;
	
	 public int getImgCar() {
		return imgCar;
	}

	public void setImgCar(int imgCar) {
		this.imgCar = imgCar;
	}

	/**
	 * 
	 * @param posx Posição x do primeiro carro
	 * @param posy Posição y do primeiro carro
	 * @param posL Largura do primeiro carro
	 * @param posA Altura do primeiro carro
	 * @param pos2x Posição x do segundo carro
	 * @param pos2y Posição y do segundo carro
	 * @param pos2L Largura do segundo carro
	 * @param pos2A Altura do segundo carro
	 * @return True se houve a colisão e False se não
	 * 
	 */
	
	public boolean colisao(int posx, int posy, int posL, int posA,
			   int pos2x, int pos2y, int pos2L, int pos2A) {
			  if ((posx >= pos2x && posx <= pos2x + pos2L)
			    && (posy >= pos2y && posy <= pos2y + pos2A)) {
			   return true;
			  } else if ((posx + posL >= pos2x && posx + posL <= pos2x + pos2L)
			    && (posy >= pos2y && posy <= pos2y + pos2A)) {
			   return true;
			  } else if ((posx >= pos2x && posx <= pos2x + pos2L)
			    && (posy + posA >= pos2y && posy + posA <= pos2y + pos2A)) {
			   return true;
			  } else if ((posx + posL >= pos2x && posx + posL <= pos2x + pos2L)
			    && (posy + posA >= pos2y && posy + posA <= pos2y + pos2A)) {
			   return true;
			  } else {
			   return false;
			  }
		}


}