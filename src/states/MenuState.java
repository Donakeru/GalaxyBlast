package states;

import java.awt.Graphics;
import java.util.ArrayList;
import graphics.Assets;
import graphics.Sound;
import singleton.ConfiguracionSingleton;
import ui.Action;
import ui.Button;

public class MenuState extends State{
	
	private ArrayList<Button> buttons;
	private Sound backgroundMusic;
	private ConfiguracionSingleton conf;
	
	public MenuState() {

		this.conf = ConfiguracionSingleton.obtenerInstancia();
		Integer screenWidth = Integer.parseInt(this.conf.obtenerParametro("WIDTH"));
		Integer screenHeight = Integer.parseInt(this.conf.obtenerParametro("HEIGHT"));

		buttons = new ArrayList<Button>();
		
		buttons.add(
			new Button(
				Assets.greyButton,
				Assets.redButton,
				screenWidth / 2 - Assets.greyButton.getWidth()/2,
				screenHeight / 2 - Assets.greyButton.getHeight() * 2,
				this.conf.obtenerParametro("PLAY"),
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new GameState());
						backgroundMusic.stop();
					}
				}
			)
		);
		
		buttons.add(
			new Button(
				Assets.greyButton,
				Assets.redButton,
				screenWidth / 2 - Assets.greyButton.getWidth()/2,
				screenHeight / 2 + Assets.greyButton.getHeight() * 2 ,
				this.conf.obtenerParametro("EXIT"),
				new Action() {
					@Override
					public void doAction() {
						System.exit(0);
					}
				}
			)
		);
		
		buttons.add(
			new Button(
				Assets.greyButton,
				Assets.redButton, 
				screenWidth / 2 - Assets.greyButton.getWidth()/2, 
				screenHeight / 2, 
				this.conf.obtenerParametro("HIGH_SCORES"), 
				new Action() {
					
					@Override
					public void doAction() {
						State.changeState(new ScoreState());
						backgroundMusic.stop();
					}
				}
			)
		);
		
		backgroundMusic = new Sound(Assets.introClip);
		backgroundMusic.loop();
		backgroundMusic.changeVolume(-10f);
	}

	@Override
	public void update(float dt) {
		for (Button b : buttons) {
			b.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		for (Button b : buttons) {
			b.draw(g);
		}
	}

}
