package Fase;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Cars.EnemyArcade;
import Cars.PrincipalCar;

@SuppressWarnings("serial")
public class ArcadeMode extends JPanel {

	/**
	 * Os objetos usados no modo Arcade
	 * 1 carro principal (principal)
	 * 1 pista (road)
	 * 2 inimigos para dificultar (enemy, enemy2)
	 * Quantidade de pontos feitos
	 * 
	 */
	private PrincipalCar principal = new PrincipalCar();
	private Background road = new Background();
	private EnemyArcade enemy = new EnemyArcade(-800);
	private EnemyArcade enemy2 = new EnemyArcade(-1100);
	private long pontos = 0;
	
	/** O construtor inicia a janela e a música */
	
	public ArcadeMode() {
		
		this.setVisible(true);
		this.addKeyListener(principal);
		this.setFocusable(true);
		
		JFrame frame = new JFrame("Arcade");
		frame.add(this);
		frame.setSize(400, 750);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		
		Soundtrack s = new Soundtrack("Things//Soundtrack//heyho.mid");
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
		enemy.draw(g2d);
		enemy2.draw(g2d);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Calibri",Font.ITALIC,30));
		principal.draw(g2d);
		g2d.drawString(String.valueOf(principal.getVelocidade())+" MPh", 0, 710);
		g2d.drawString(String.valueOf(pontos), 0, 20);
		if ((principal.colisao(principal.getPosX(), principal.getPosY(), principal.getLargura(), principal.getAltura(), 
				enemy.getPosX(), enemy.getPosY(), enemy.getLargura(), enemy.getAltura()) || 
				(principal.colisao(principal.getPosX(), principal.getPosY(), principal.getLargura(), principal.getAltura(),
						enemy2.getPosX(), enemy2.getPosY(), enemy2.getLargura(), enemy2.getAltura())))){
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Calibri",Font.ITALIC,100));
			g2d.drawString("LOSER", 100, 100);
			road.setStop(true);
			principal.setVelocidade(0);
			this.removeKeyListener(principal);
		}
		g2d.dispose();
	}

	/**
	 * O método update, atualiza a posição dos carros inimigos
	 * e atualiza a quantidade de pontos que o jogador fez
	 * 
	 */
	
	public void update() {
		int FPS = 30;
		while (true) {
			this.repaint();
			if (enemy.getPosY() < 800){
				enemy.update(principal.getVelocidade());
			} else {
				enemy.ChangeCar(principal.getPosition());
				pontos+=100;
				enemy.setPosY(enemy.getPosYInicial());	
			}
			if (enemy2.getPosY() < 800){
				enemy2.update(principal.getVelocidade());
			} else {
				enemy2.ChangeCar(principal.getPosition());
				pontos+=100;
				enemy2.setPosY(enemy2.getPosYInicial());
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
