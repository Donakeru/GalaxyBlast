package graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

public class Assets {
	
	
	//carga de recursos
	public static boolean loaded = false;
	public static float count = 0;
	public static float MAX_COUNT = 56;
	
	// effects
	public static BufferedImage player, doubleGunPlayer;
	public static BufferedImage speed;
	public static BufferedImage[] shieldEffect = new BufferedImage[3];
	
	
	// explosion
	
	public static BufferedImage[] exp = new BufferedImage[9];
	
	// lasers
	
	public static BufferedImage blueLaser, greenLaser, redLaser,  orb;
	public static BufferedImage doubleScore, doubleGun, fastFire, shield, star;
	// Meteors
	
	public static BufferedImage[] bigs = new BufferedImage[4];
	public static BufferedImage[] meds = new BufferedImage[2];
	public static BufferedImage[] smalls = new BufferedImage[2];
	public static BufferedImage[] tinies = new BufferedImage[2];
	
	// ufo
	
	public static BufferedImage ufo;
	
	// numbers
	
	public static BufferedImage[] numbers = new BufferedImage[11];
	
	public static BufferedImage life;
	
	// fonts
	
	public static Font fontBig;
	public static Font fontMed;
	
	// sounds
	public static Clip introClip, ovni, explosion, finalExplosion, playerShoot, ufoShoot, wave, scoreSound, powerUp; 
	
	//buttons 
	public static BufferedImage greyButton, redButton;
	
	public static void init()
	{
		player = loadImage("/ships/player.png");
		doubleGunPlayer = loadImage("/ships/doubleGunPlayer.png");
		
		speed = loadImage("/effects/fire08.png");
		
		blueLaser = loadImage("/lasers/laserBlue01.png");
		
		greenLaser = loadImage("/lasers/laserGreen11.png");
		
		redLaser = loadImage("/lasers/laserRed01.png");
		
		ufo = loadImage("/ships/ufo.png");
		
		life = loadImage("/others/life.png");
		
		fontBig = loadFont("/fonts/futureFont.ttf", 42);
		
		fontMed = loadFont("/fonts/futureFont.ttf", 20);
		
		for(int i = 0; i<3; i++)
			shieldEffect[i]= loadImage("/effects/shield" + (i+1)+".png");
		
		for(int i = 0; i < bigs.length; i++)
			bigs[i] = loadImage("/meteors/big"+(i+1)+".png");
		
		for(int i = 0; i < meds.length; i++)
			meds[i] = loadImage("/meteors/med"+(i+1)+".png");
		
		for(int i = 0; i < smalls.length; i++)
			smalls[i] = loadImage("/meteors/small"+(i+1)+".png");
		
		for(int i = 0; i < tinies.length; i++)
			tinies[i] = loadImage("/meteors/tiny"+(i+1)+".png");
		
		for(int i = 0; i < exp.length; i++)
			exp[i] = loadImage("/explosion/"+i+".png");
		
		for(int i = 0; i < numbers.length; i++)
			numbers[i] = loadImage("/numbers/"+i+".png");
		
		//Music and effects
		
		introClip = loadSound("/sounds/introClip.wav");
		
		ovni = loadSound("/sounds/ovni.wav");
		
		explosion = loadSound("/sounds/explosion.wav");
		
		finalExplosion = loadSound("/sounds/finalExplosion.wav");
		
		playerShoot = loadSound("/sounds/shoot1.wav");
		
		ufoShoot = loadSound("/sounds/shoot2.wav");
		
		wave = loadSound("/sounds/wavePosible1.wav");
		
		scoreSound = loadSound("/sounds/inteplanetaryOdisey.wav");
		
		powerUp =loadSound("/sounds/inteplanetaryOdisey.wav");
				
		// buttons
		greyButton = loadImage("/ui/greyButton.png");
		
		redButton = loadImage("/ui/redButton.png");
		
		//powers Up
		
		orb = loadImage("/powers/orb.png");
		doubleScore = loadImage("/powers/doubleScore.png");
		doubleGun = loadImage("/powers/doubleGun.png");
		fastFire = loadImage("/powers/fastFire.png");
		star = loadImage("/powers/star.png");
		shield = loadImage("/powers/shield.png");
		
		loaded = true;
		
	}
	public static BufferedImage loadImage(String path) {
		count ++;
		return Loader.ImageLoader(path);
	}
	public static Font loadFont(String path, int size) {
		count ++;
		return Loader.loadFont(path, size);
	}
	
	public static Clip loadSound(String path) {
		count ++;
		return Loader.loadSound(path);
	}
	
	
	
	
	
	
	
	
}