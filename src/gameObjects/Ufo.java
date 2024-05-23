package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import graphics.Assets;
import graphics.Sound;
import math.Vector2D;
import singleton.ConfiguracionSingleton;
import states.GameState;

public class Ufo extends MovingObject{
	
	private ArrayList<Vector2D> path;
	
	private Vector2D currentNode;
	private int index;
	private boolean following;
	private long fireRate;
	private Sound shoot;
	private ConfiguracionSingleton conf;
	
	public Ufo(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture,
		ArrayList<Vector2D> path, GameState gameState ) {
		super(position, velocity, maxVel, texture, gameState);

		this.path = path;
		index = 0;
		following = true;
		fireRate = 0;
		this.conf = ConfiguracionSingleton.obtenerInstancia();
		shoot = new Sound(Assets.ufoShoot);
	}
	
	private Vector2D pathFollowing() {
		currentNode = path.get(index);
		
		double distanceToNode = currentNode.subtract(getCenter()).getMagnitude();
		
		if(distanceToNode < Integer.parseInt(this.conf.obtenerParametro("NODE_RADIUS"))) {
			index ++;
			if(index >= path.size()) {
				following = false;
			}
		}
		
		return seekForce(currentNode);
		
	}
	
	private Vector2D seekForce(Vector2D target) {
		Vector2D desiredVelocity = target.subtract(getCenter());
		desiredVelocity = desiredVelocity.normalize().scale(maxVel);
		return desiredVelocity.subtract(velocity);
	}
	
	@Override
	public void update(float dt) {
		
		fireRate += dt;
		
		Vector2D pathFollowing;
		
		if(following)
			pathFollowing = pathFollowing();
		else
			pathFollowing = new Vector2D();
		
		pathFollowing = pathFollowing.scale(1/Double.parseDouble(this.conf.obtenerParametro("UFO_MASS")));
		
		velocity = velocity.add(pathFollowing);
		
		velocity = velocity.limit(maxVel);
		
		position = position.add(velocity);
		
		if(position.getX() > Integer.parseInt(this.conf.obtenerParametro("WIDTH")) || position.getY() > Integer.parseInt(this.conf.obtenerParametro("HEIGHT"))
			|| position.getX() < -width || position.getY() < -height)
			Destroy();
		
		// shoot
		
		if(fireRate > Long.parseLong(this.conf.obtenerParametro("UFO_FIRE_RATE"))) {
			
			Vector2D toPlayer = gameState.getPlayer().getCenter().subtract(getCenter());
			
			toPlayer = toPlayer.normalize();
			
			double currentAngle = toPlayer.getAngle();
			
			currentAngle += Math.random()*Double.parseDouble(this.conf.obtenerParametro("UFO_ANGLE_RANGE")) - Double.parseDouble(this.conf.obtenerParametro("UFO_ANGLE_RANGE")) / 2;
			
			if(toPlayer.getX() < 0)
				currentAngle = -currentAngle + Math.PI;
			
			toPlayer = toPlayer.setDirection(currentAngle);
			
			Laser laser = new Laser(
					getCenter().add(toPlayer.scale(width)),
					toPlayer,
					Double.parseDouble(this.conf.obtenerParametro("LASER_VEL")),
					currentAngle + Math.PI/2,
					Assets.redLaser,
					gameState
					);
			
			gameState.getMovingObjects().add(0, laser); 
			
			fireRate = 0;
			
			shoot.play();
			
		}
		
		if(shoot.getFramePosition() > 8500) {
			shoot.stop();
		}
		
		angle += 0.05;
		
		collidesWith();
		
	}
	@Override
	public void Destroy() {
		gameState.addScore(Integer.parseInt(this.conf.obtenerParametro("UFO_SCORE")), position);
		gameState.playExplosion(position);
		super.Destroy();
	}
	
	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		
		at.rotate(angle, width/2, height/2);
		
		g2d.drawImage(texture, at, null);
		
	}
	
}