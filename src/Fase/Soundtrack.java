package Fase;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Soundtrack {
	private Sequencer player;
	private String nome;

	public Soundtrack(String nome) {
		this.nome = nome;
	}

	/** 
	 * @param repetir Quantidade de vezes que vai se repetir 
	 *
	 * Inicia o player
	 * Carrega a música
	 * Abre o player
	 * Passa a música para o player
	 * Define quantas vezes vai repetir
	 * E finalmente inicia a música
	 * 
	 */
	public void start(int repetir) {
		try {
			player = MidiSystem.getSequencer();
			Sequence musica = MidiSystem.getSequence(new File(this.nome));
			player.open();
			player.setSequence(musica);
			player.setLoopCount(repetir);
			player.start();
			
		} catch (Exception e) {
			System.out.println("Erro ao tocar: " + nome);
		}
	}

	public void stop() {
		player.stop();
	}
}
