package com.sert.controler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LeitorTeclas implements KeyListener {
	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case (KeyEvent.VK_F1):
			System.out.println("Você pressionou a tecla F1");
			break;
		case (KeyEvent.VK_F2):
			System.out.println("Você pressionou a tecla F2");
			break;
		case (KeyEvent.VK_F3):
			System.out.println("Você pressionou a tecla F3");
			break;
		case (KeyEvent.VK_MULTIPLY):
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}