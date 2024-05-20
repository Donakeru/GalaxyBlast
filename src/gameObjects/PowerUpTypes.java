package gameObjects;

import java.awt.image.BufferedImage;

import graphics.Assets;

public enum PowerUpTypes {
	
	SHIELD("SHIELD", Assets.shield),
	LIFE("ONE MORE", Assets.life),
	SCORE_X2("2X SCORE",Assets.doubleScore),
	FASTER_FIRE("MORE SHOOTS",Assets.fastFire),
	SCORE_STACK("FREE SCORE",Assets.star),
	DOUBLE_GUN("UPDATE WEAPON", Assets.doubleGun);
	
	public String text;
	public BufferedImage texture;
	
	
	private PowerUpTypes(String text, BufferedImage texture) {
		this.text = text;
		this.texture = texture;
	}
	
	

}
