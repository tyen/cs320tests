package test;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import edu.cs320.project.DisplayController;

public class SmartRobot extends Robot {

	public SmartRobot() throws AWTException {
		super();
	}

	public void keyType(int keyCode) {
		keyPress(keyCode);
		delay(50);
		keyRelease(keyCode);
	}

	public void keyType(int keyCode, int keyCodeModifier) {
		keyPress(keyCodeModifier);
		keyPress(keyCode);
		delay(50);
		keyRelease(keyCode);
		keyRelease(keyCodeModifier);
	}


	public void type(String text) {
		String textUpper = text.toUpperCase();

		for (int i=0; i<text.length(); ++i) {
			typeChar(text.charAt(i));
		}		
	}

	private void typeChar(char c) {
		boolean shift = true;
		int keyCode;

		switch (c) {
			case 'A': keyCode = KeyEvent.VK_A; break;
			case 'B': keyCode = KeyEvent.VK_B; break;
			case 'C': keyCode = KeyEvent.VK_C; break;
			case 'D': keyCode = KeyEvent.VK_D; break;
			case 'E': keyCode = KeyEvent.VK_E; break;
			case 'F': keyCode = KeyEvent.VK_F; break;
			case 'G': keyCode = KeyEvent.VK_G; break;
			case 'H': keyCode = KeyEvent.VK_H; break;
			case 'I': keyCode = KeyEvent.VK_I; break;
			case 'J': keyCode = KeyEvent.VK_J; break;
			case 'K': keyCode = KeyEvent.VK_K; break;
			case 'L': keyCode = KeyEvent.VK_L; break;
			case 'M': keyCode = KeyEvent.VK_M; break;
			case 'N': keyCode = KeyEvent.VK_N; break;
			case 'O': keyCode = KeyEvent.VK_O; break;
			case 'P': keyCode = KeyEvent.VK_P; break;
			case 'Q': keyCode = KeyEvent.VK_Q; break;
			case 'R': keyCode = KeyEvent.VK_R; break;
			case 'S': keyCode = KeyEvent.VK_S; break;
			case 'T': keyCode = KeyEvent.VK_T; break;
			case 'U': keyCode = KeyEvent.VK_U; break;
			case 'V': keyCode = KeyEvent.VK_V; break;
			case 'W': keyCode = KeyEvent.VK_W; break;
			case 'X': keyCode = KeyEvent.VK_X; break;
			case 'Y': keyCode = KeyEvent.VK_Y; break;
			case 'Z': keyCode = KeyEvent.VK_Z; break;
			case '\n': shift = false;
			case '\r': keyCode = KeyEvent.VK_ENTER; break;
			case '~': keyCode = (int)'`'; break;
			case '!': keyCode = (int)'1'; break;
			case '@': keyCode = (int)'2'; break;
			case '#': keyCode = (int)'3'; break;
			case '$': keyCode = (int)'4'; break;
			case '%': keyCode = (int)'5'; break;
			case '^': keyCode = (int)'6'; break;
			case '&': keyCode = (int)'7'; break;
			case '*': keyCode = (int)'8'; break;
			case '(': keyCode = (int)'9'; break;
			case ')': keyCode = (int)'0'; break;
			case ':': keyCode = (int)';'; break;
			case '_': keyCode = (int)'-'; break;
			case '+': keyCode = (int)'='; break;
			case '|': keyCode = (int)'\\'; break;
			case '?': keyCode = (int)'/'; break;
			case '{': keyCode = (int)'['; break;
			case '}': keyCode = (int)']'; break;
			case '<': keyCode = (int)','; break;
			case '>': keyCode = (int)'.'; break;
			default:
				keyCode = (int)Character.toUpperCase(c);
				shift = false;
		}
		if (shift)
			keyType(keyCode, KeyEvent.VK_SHIFT);
		else
			keyType(keyCode);
	}

	public void mouseClick(){
		this.mousePress(KeyEvent.BUTTON1_MASK);
		delay(50);
		this.mouseRelease(KeyEvent.BUTTON1_MASK);
		delay(50);
	}
	
	public void mouseClick(int x, int y){
		this.mouseMove(x, y);
		this.mousePress(KeyEvent.BUTTON1_MASK);
		delay(50);
		this.mouseRelease(KeyEvent.BUTTON1_MASK);
		delay(50);
	}
	
	public void mouseClick(JComponent component){
		Point center = this.calculateComponentCenter(component);
		this.mouseMove(center.x, center.y);
		delay(100);
		this.mousePress(KeyEvent.BUTTON1_MASK);
		delay(50);
		this.mouseRelease(KeyEvent.BUTTON1_MASK);
		delay(50);
	}
	
	private Point calculateComponentCenter(JComponent component){
		int x = component.getX() + component.getWidth() / 2 + this.getXOffset();
		int y = component.getY() + component.getHeight() / 2 + this.getYOffset();
		
		return new Point(x, y);
	}
	
	public void mouseDoubleClick(JComponent component) {
		Point center = this.calculateComponentCenter(component);
		this.mouseMove(center.x, center.y);
		this.mouseClick();
		delay(200);
		this.mouseClick();
	}
		
	public void mouseDoubleClick(int x, int y) {
		this.mouseMove(x, y);
		this.mouseClick();
		delay(200);
		this.mouseClick();
	}
	
	public void mouseDoubleClick() {
		this.mouseClick();
		delay(200);
		this.mouseClick();
	}
	
	public void typeEnter() {
		this.typeChar('\n');
	}
	
	private int getXOffset(){
		DisplayController instance = DisplayController.GetInstance();
		return instance.getXOnScreen();
	}
	
	private int getYOffset(){
		DisplayController instance = DisplayController.GetInstance();
		return instance.getYOnScreen() + 25;
	}

}
