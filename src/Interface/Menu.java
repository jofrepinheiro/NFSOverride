package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Menu {
	private int cenario = -1;
	private int itemSelecionado = 0;
	private String itens[];
	private Graphics bbg;
	private boolean ativo;
	private int x;
	private int y;

	private int normFSize = 20;
	private int selFSize = 25; 
	private int distanciaEntreItens = 15;
	private Font normFont = new Font("Arial", Font.BOLD, normFSize);
	private Font selFont = new Font("Arial", Font.BOLD, selFSize);
	private Color corSelecionado = new Color(255, 0, 0);
	private Color corNaoSelecionado = new Color(255, 255, 255);

	/**
	 * 
	 * @param numeroDeItens Número de itens do menu
	 * @param x Posição x onde o menu vai começar
	 * @param y Posição y onde o menu vai começar
	 * @param ativo Se o menu estiver ativo  
	 * 
	 */
	public Menu(int numeroDeItens, int x, int y, boolean ativo) {
		itens = new String[numeroDeItens];
		this.x = x;
		this.y = y;
		this.ativo = ativo;
	}

	
	public void controlar(KeyEvent e) {
		if (ativo) {
			controlarMenu(e);
		}
	}

	public void voltarAoMenu(KeyEvent e) {
		if (e.getKeyCode() == e.VK_ESCAPE) {
			cenario = -1;
			ativo = true;
		}
	}

	/** Método para movimentar os itens do menu */
	
	private void controlarMenu(KeyEvent e) {

		if (e.getKeyCode() == e.VK_UP) {
			itemSelecionado -= 1;
		}

		if (e.getKeyCode() == e.VK_DOWN) {
			itemSelecionado += 1;
		}
		if (itemSelecionado >= itens.length) {
			itemSelecionado = 0;
		}
		if (itemSelecionado < 0) {
			itemSelecionado = itens.length - 1;
		}
		if (e.getKeyCode() == e.VK_ENTER) {
			cenario = itemSelecionado;
			ativo = false;
		}
	}

	/**  Método para ajustar o menu na tela */
	
	public void desenharMenu() {
		for (int i = 0; i < itens.length; i++) {
			if (itemSelecionado == i) {
				bbg.setColor(corSelecionado);
				bbg.setFont(selFont);
				bbg.drawString(itens[i], x, y
						+ (i * (normFSize + distanciaEntreItens)));
			} else {
				bbg.setColor(corNaoSelecionado);
				bbg.setFont(normFont);
				bbg.drawString(itens[i], x, y
						+ (i * (normFSize + distanciaEntreItens)));
			}
		}
	}

	public int getCenario() {
		return this.cenario;

	}

	public String[] getItens() {
		return this.itens;
	}

	public Graphics getBbg() {
		return this.bbg;
	}

	public void setBbg(Graphics bbg) {
		this.bbg = bbg;
	}

	public void setItens(String itens, int indice) {
		this.itens[indice] = itens;
	}
}
