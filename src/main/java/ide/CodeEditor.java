package ide;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CodeEditor extends JScrollPane {
	private static JTextPane tmp;
	final JTextPane p;
	public String path = null;

	public JEditorPane getPane() {
		return p;
	}

	private void init(KeyListener listener) {
		p.addKeyListener(listener);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		setWheelScrollingEnabled(true);
		//setEnabled(false);
		setAutoscrolls(true);

		StyledDocument doc = p.getStyledDocument();
	}

	public CodeEditor(Font font, String path, KeyListener listener) {
		super(tmp = new JTextPane());
		p = tmp;
		this.path = path;
		p.setFont(font);
		try {
			p.setText(read(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init(listener);
	}

	public CodeEditor(Font font, KeyListener listener) {
		super(tmp = new JTextPane());
		p = tmp;
		p.setFont(font);
		init(listener);
	}

	public String getText() {
		return p.getText();
	}
	
	private static String read(String s) throws IOException {
		return Files.readString(Path.of(s));
	}
	
	static void write(String path, String text) throws IOException {
		Files.writeString(Path.of(path), text);
	}
	
	public void save() {
		try {
			path = Ide.getRealPath(path);
			write(path, p.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;

}
