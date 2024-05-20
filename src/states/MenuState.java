package states;

import java.awt.Graphics;
import java.util.ArrayList;
import gameObjects.Constants;
import graphics.Assets;
import graphics.Sound;
import ui.Action;
import ui.Button;

public class MenuState extends State{
	
	private ArrayList<Button> buttons;
	private Sound backgroundMusic;
	
	public MenuState() {
		buttons = new ArrayList<Button>();
		
		buttons.add(new Button(
				Assets.greyButton,
				Assets.redButton,
				Constants.WIDTH / 2 - Assets.greyButton.getWidth()/2,
				Constants.HEIGHT / 2 - Assets.greyButton.getHeight() * 2,
				Constants.PLAY,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new GameState());
						backgroundMusic.stop();
					}
				}
				));
		
		buttons.add(new Button(
				Assets.greyButton,
				Assets.redButton,
				Constants.WIDTH / 2 - Assets.greyButton.getWidth()/2,
				Constants.HEIGHT / 2 + Assets.greyButton.getHeight() * 2 ,
				Constants.EXIT,
				new Action() {
					@Override
					public void doAction() {
						System.exit(0);
					}
				}
				));
		
		buttons.add(new Button(Assets.greyButton,
				Assets.redButton, 
				Constants.WIDTH / 2 - Assets.greyButton.getWidth()/2, 
				Constants.HEIGHT / 2, 
				Constants.HIGH_SCORES, 
				new Action() {
					
					@Override
					public void doAction() {
						State.changeState(new ScoreState());
						backgroundMusic.stop();
					}
				}
				
				));
		
		
		
		
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
