package test.system;

import java.awt.AWTException;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.Robot;
import java.util.ArrayList;
import javax.swing.JComponent;

import edu.cs320.project.DisplayController;

import test.SmartRobot;
import test.Utility;

public class HelpSR extends Robot
{

	public HelpSR() throws AWTException 
	{
		super();
	}
	public void login()
	{
		mouseMove(470,260);
		mouseClick();
		type("cs320");
		mouseMove(470,310);
		mouseClick();
		type("cs320");
		mouseMove(470,370);
		mouseClick();
	}
	public void login2()
	{
		Utility.login("cs320", "cs320");
	}	

	public void searchForNewPatient2(String x, String y, String z)
	{
		Utility.searchForNewPatient(x, y, z);
	}
	public void searchForNewPatient(String x, String y, String z)
	{
		mouseMove(420,230);
		mouseClick();
		type(x);
		mouseMove(420,260);
		mouseClick();
		type(y);
		mouseMove(420,290);
		mouseClick();
		type(z);
		mouseMove(420,360);
		mouseClick();
		
		delay(1000);
		//Hits the "ok" button for new patient prompt
		mouseMove(550,340);
		mouseDoubleClick();
		mouseDoubleClick();
	}
	public void searchForManyPatient(String x, String y, String z)
	{
		mouseMove(420,230);
		mouseClick();
		type(x);
		mouseMove(420,260);
		mouseClick();
		type(y);
		mouseMove(420,290);
		mouseClick();
		type(z);
		mouseMove(420,360);
		mouseClick();
		//Picks first patient in list
		mouseMove(200,110);
		mouseDoubleClick();
		mouseMove(430,450);
		mouseDoubleClick();
	}
	public void searchForOldPatient(String x, String y, String z)
	{
		mouseMove(420,230);
		mouseClick();
		type(x);
		mouseMove(420,260);
		mouseClick();
		type(y);
		mouseMove(420,290);
		mouseClick();
		type(z);
		mouseMove(420,360);
		mouseClick();
	}
	
	public void fillNewPatientOut(ArrayList<String> patient, ArrayList<String> allergy, ArrayList<String> drug, int question)
	{
		//PatientID box
		mouseMove(470,70);
		mouseClick();
		type(patient.get(0));
		
		//weight
		mouseMove(290,120);
		mouseClick();
		mousePress(KeyEvent.BUTTON1_MASK);
		mouseMove(300,120);
		mouseRelease(KeyEvent.BUTTON1_MASK);	
		type(patient.get(1));

		//height
		mouseMove(200,120);
		mouseClick();
		mousePress(KeyEvent.BUTTON1_MASK);
		mouseMove(210,120);
		mouseRelease(KeyEvent.BUTTON1_MASK);	
		type(patient.get(2));
		
		//address
		mouseMove(50,160);
		mouseClick();
		type(patient.get(3));
		

		
		addAllergy(allergy);
		
		addDrug(drug,question);
	
		//notes
		mouseMove(10,530);
		mouseClick();	
		mousePress(KeyEvent.BUTTON1_MASK);
		mouseMove(50,530);
		mouseRelease(KeyEvent.BUTTON1_MASK);		
		type(patient.get(4));		
		
		//submit
		mouseMove(710,570);
		mouseDoubleClick();
		
		mouseMove(710,585);
		mouseDoubleClick();		
		
	}
	
	public void addAllergy(ArrayList<String> allergy)
	{
		//Click button
		mouseMove(520,300);
		mouseClick();
		
		
		//allergy field
		mouseMove(50,222);
		mouseClick();
		type(allergy.get(0));
		
		//reaction field
		mouseMove(225,222);
		mouseClick();
		type(allergy.get(1));
		
		//Click submit button
		mouseMove(520,260);
		mouseClick();
	}
	
	public void addDrug(ArrayList<String> drug, int y)
	{
		
		int questionable = y;
		//click button
		mouseMove(520,500);
		mouseClick();
		
		//name
		mouseMove(70,335);
		mouseClick();
		type(drug.get(0));
		
		//Dosage
		mouseMove(200,335);
		mouseClick();
		mousePress(KeyEvent.BUTTON1_MASK);
		mouseMove(230,335);
		mouseRelease(KeyEvent.BUTTON1_MASK);
		type(drug.get(1));
		
		//Unit
		mouseMove(280,335);
		mouseClick();
		type(drug.get(2));
		
		//Route
		mouseMove(330,335);
		mouseClick();
		type(drug.get(3));

		//Frequency
		mouseMove(450,335);
		mouseClick();
		type(drug.get(4));
		
		//Start
		mouseMove(55,380);
		mouseClick();
		mousePress(KeyEvent.BUTTON1_MASK);
		mouseMove(130,380);
		mouseRelease(KeyEvent.BUTTON1_MASK);
		type(drug.get(5));
		
		//Stop
		mouseMove(160,380);
		mouseClick();
		mousePress(KeyEvent.BUTTON1_MASK);
		mouseMove(225,380);
		mouseRelease(KeyEvent.BUTTON1_MASK);
		type(drug.get(6));
		
		//Prescriber
		mouseMove(280,385);
		mouseClick();
		type(drug.get(7));
		
		//Notes
		mouseMove(55,425);
		mouseClick();
		type(drug.get(8));
		
		
		//IsQuestionable
		if(questionable==1)
		{	
			mouseMove(75,465);
			mouseClick();
		}
		
		//save button
		mouseMove(460,465);
		mouseClick();
		
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
	
	public void mouseClick(JComponent component, Container parent){
		Point center = this.calculateComponentCenter(component);
		this.mouseMove(center.x + parent.getX(), center.y + parent.getY());
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
		this.mouseMove(x + this.getXOffset(), y + this.getYOffset());
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
