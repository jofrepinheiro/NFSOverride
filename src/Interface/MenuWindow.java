package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Fase.ArcadeMode;
import Fase.RaceMode;
import Fase.Soundtrack;

public class MenuWindow extends JFrame implements KeyListener {

	private BufferedImage backBuffer;
	private BufferedImage[] img = new BufferedImage[1];
	private int FPS = 30;
	private int janelaW = 400;
	private int janelaH = 500;
	private Menu menuPrincipal = new Menu(5, 280, 320, true);
	private Soundtrack s = new Soundtrack("Things//Soundtrack//sweethome.mid");

	
	/** Construtor carrega a imagem no array para ser colocada na tela */
	
	public MenuWindow() {
		try {
			img[0] = ImageIO.read(new File("Things//Sprites//Road//menu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Método para ação de cada item do menu */
	
	public void cenarios() {
		Graphics bbg = backBuffer.getGraphics();
		bbg.setFont(new Font("Arial", Font.BOLD, 20));
		if (menuPrincipal.getCenario() == 0) {
			this.dispose();
			s.stop();
			ArcadeMode arcade = new ArcadeMode();
			arcade.update();
		}
		if (menuPrincipal.getCenario() == 1) {
			this.dispose();
			s.stop();
			RaceMode race = new RaceMode();
			race.update();
		}
		if (menuPrincipal.getCenario() == 2) {
			bbg.setColor(new Color(0, 0, 0));
			bbg.fillRect(0, 0, janelaW, janelaH);
			bbg.setColor(Color.WHITE);
			bbg.drawString("CONTROLES", 100, 170);
			
			bbg.drawString("W, UP: Acelerar", 100, 200);
			bbg.drawString("S, DOWN: Frear", 100, 230);
			bbg.drawString("Espaço: NITRO", 100, 260);
		}
		if (menuPrincipal.getCenario() == 3) {
			bbg.setColor(new Color(0, 0, 0));
			bbg.fillRect(0, 0, janelaW, janelaH);
			bbg.setColor(Color.WHITE);
			bbg.drawString("CREDITS", 100, 170);
			bbg.drawString("Giusepe Chagas", 100, 230);
			bbg.drawString("Jofre Pinheiro", 100, 260);
		}

		if (menuPrincipal.getCenario() == 4) {
			System.exit(0);
		}
	}
	
	public void desenharGraficos() {
		Graphics g = getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		bbg.drawImage(img[0], 0, 20, null);
		menuPrincipal.desenharMenu();
		cenarios();
		g.drawImage(backBuffer, 0, 0, this);
	}

	/** Método para iniciar a janela do menu e os itens do menu */
	
	public void inicializar() {
		setTitle("Titulo do Jogo!");
		setSize(janelaW, janelaH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		s.start(1);
		backBuffer = new BufferedImage(janelaW, janelaH,
				BufferedImage.TYPE_INT_RGB);
		addKeyListener(this);
		menuPrincipal.setItens("ARCADE", 0);
		menuPrincipal.setItens("RACE", 1);
		menuPrincipal.setItens("HELP", 2);
		menuPrincipal.setItens("CREDITS", 3);
		menuPrincipal.setItens("EXIT", 4);
		menuPrincipal.setBbg(backBuffer.getGraphics());

	}

	public void run() {
		inicializar();
		while (true) {
			desenharGraficos();
			try {
				Thread.sleep(1000 / FPS);
			} catch (Exception e) {
				System.out.println("Thread interrompida!");
			}
		}
	}

	/** Interface KeyListener do menu */
	public void keyPressed(KeyEvent e) {
		menuPrincipal.controlar(e);
		menuPrincipal.voltarAoMenu(e);
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}