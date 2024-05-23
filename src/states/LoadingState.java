package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import graphics.Assets;
import graphics.Loader;
import graphics.Text;
import math.Vector2D;
import singleton.ConfiguracionSingleton;

public class LoadingState extends State{

	private Thread loadingThread;
	
	private Font font;
	private ConfiguracionSingleton conf;
	
	public LoadingState(Thread loadingThread) {
		this.loadingThread = loadingThread;
		this.loadingThread.start();
		font = Loader.loadFont("/fonts/futureFont.ttf", 38);
		this.conf = ConfiguracionSingleton.obtenerInstancia();
	}
	
	@Override
	public void update(float dt) {
		if(Assets.loaded) {
			State.changeState(new MenuState());
			try {
				loadingThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void draw(Graphics g) {

		Integer screenWidth = Integer.parseInt(this.conf.obtenerParametro("WIDTH"));
		Integer screenHeight = Integer.parseInt(this.conf.obtenerParametro("HEIGHT"));
		Integer loadingBarWidth = Integer.parseInt(this.conf.obtenerParametro("LOADING_BAR_WIDTH"));
		Integer loadingBarHeight = Integer.parseInt(this.conf.obtenerParametro("LOADING_BAR_HEIGHT"));

		GradientPaint gp = new GradientPaint(
			screenWidth / 2 - loadingBarWidth / 2,
			screenHeight / 2 - loadingBarHeight / 2,
			Color.WHITE,
			screenWidth / 2 + loadingBarWidth / 2,
			screenHeight / 2 + loadingBarHeight / 2,
			Color.RED
		);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setPaint(gp);
		
		float percentage = (Assets.count / Assets.MAX_COUNT);
		
		g2d.fillRect(screenWidth / 2 - loadingBarWidth / 2,
				screenHeight / 2 - loadingBarHeight / 2,
				(int)(loadingBarWidth * percentage),
				loadingBarHeight);
		
		g2d.drawRect(screenWidth / 2 - loadingBarWidth / 2,
				screenHeight / 2 - loadingBarHeight / 2,
				loadingBarWidth,
				loadingBarHeight);
		
		Text.drawText(g2d, "Galaxy Blast", new Vector2D(screenWidth / 2, screenHeight / 2 - 50),
				true, Color.WHITE, font);
		
		
		Text.drawText(g2d, "LOADING...", new Vector2D(screenWidth / 2, screenHeight / 2 + 40),
				true, Color.WHITE, font);
		
	}

}