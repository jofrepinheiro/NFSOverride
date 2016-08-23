package Fase;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Cars.EnemyRace;
import Cars.PrincipalCar;

public class RaceMode extends JPanel {
	
	/**
	 * Os objetos usados no modo Arcade
	 * 1 carro principal (principal)
	 * 1 pista (road)
	 * 2 inimigos para dificultar (enemy, enemy2)
	 * Posição do player
	 * 
	 */
	public PrincipalCar principal = new PrincipalCar();
	public Background road = new Background();
	public EnemyRace enemyrace = new EnemyRace();
	public Final f = new Final();
	private int posicao = 9; 

	/** O construtor inicia a janela e a música */

	public RaceMode() {

		this.setVisible(true);
		this.addKeyListener(principal);
		this.setFocusable(true);

		JFrame frame = new JFrame("Race");
		frame.add(this);
		frame.setSize(400, 750);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);		

		Soundtrack s = new Soundtrack("Things//Soundtrack//highway.mid");
		s.start(0); // Começa a música
		this.repaint();
	}

	/** 
	 * 
	 *Nesse método paint, colocamos as imagens na tela com o draw de cada objeto
	 *depois verificaremos se houve a colisão e se colidir desabilitaremos o 
	 *KeyListener do carro principal e mostraremos que o player perdeu
	 * 
	*/
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		road.draw(g2d);
		enemyrace.draw(g2d);
		if (posicao == 1){
			f.draw(g2d);
			this.removeKeyListener(principal);
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Calibri",Font.ITALIC,300));
			g2d.drawString(String.valueOf(posicao), 125, 450 );
		}
		
		principal.draw(g2d);
		

		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Calibri",Font.ITALIC,30));
		g2d.drawString(String.valueOf(principal.getVelocidade())+" MPh", 0, 710);
		g2d.drawString(String.valueOf(posicao), 375, 710);

		
		
		if (principal.colisao(principal.getPosX(), principal.getPosY(), principal.getLargura(), principal.getAltura(), 
				enemyrace.getPosX(), enemyrace.getPosY(), enemyrace.getLargura(), enemyrace.getAltura())){
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Calibri",Font.ITALIC,100));
			g2d.drawString("LOSER", 100, 100);
			road.setStop(true);
			principal.setVelocidade(50);
			this.removeKeyListener(principal);
		}
		g2d.dispose();
	}
	
	/**
	 * O método update, atualiza a posição dos carros inimigos
	 * e atualiza a posição do player
	 * 
	 */
	
	public void update() {
		int FPS = 30;
		while (true) {
			this.repaint();
			if (posicao == 1) {
				f.update(principal.getVelocidade());
			}

			if (enemyrace.getPosY() < -900){
				if (enemyrace.getImgCar() < 7){
					posicao++;
					enemyrace.setPosY(700);
					enemyrace.setImgCar(enemyrace.getImgCar()+1);
				}
				if (principal.getPosition() == 2){
					enemyrace.ChangeCar(1);
				} else {
					enemyrace.ChangeCar(principal.getPosition()+1);
				}
			}

			if (enemyrace.getPosY() < 800){
				enemyrace.update(principal.getVelocidade());
			} else {
	
				if (posicao == 2)
					posicao--;
				if (enemyrace.getImgCar() >= 1){
					posicao--;
					enemyrace.setImgCar(enemyrace.getImgCar()-1);
					enemyrace.ChangeCar(principal.getPosition());
					enemyrace.setPosY(-800);
				}
			}

			for (int i = 0; i < principal.getVelocidade(); i++)
				road.update();
			try {
				Thread.sleep(1000 / FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Erro");
			}


		}
	}	
}
