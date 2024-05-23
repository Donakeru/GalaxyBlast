package states;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import graphics.Assets;
import graphics.Sound;
import graphics.Text;
import io.JSONParser;
import io.ScoreData;
import math.Vector2D;
import singleton.ConfiguracionSingleton;
import ui.Action;
import ui.Button;

public class ScoreState extends State{
	
	private Button returnButton;
	private PriorityQueue<ScoreData> highScores;
	private Comparator<ScoreData> scoreComparator;
	private ScoreData[] auxArray;
	private Sound scoreSound;
	private ConfiguracionSingleton conf;
	
	public ScoreState() {
		
		this.conf = ConfiguracionSingleton.obtenerInstancia();
		Integer screenHeight = Integer.parseInt(this.conf.obtenerParametro("HEIGHT"));
		
		returnButton = new Button(
			Assets.greyButton,
			Assets.redButton,
			Assets.greyButton.getHeight(),
			screenHeight - Assets.greyButton.getHeight() * 2,
			this.conf.obtenerParametro("RETURN"),
			new Action() {
				@Override
				public void doAction() {
					State.changeState(new MenuState());
					scoreSound.stop();
				}
			}
		);
		
		scoreSound = new Sound(Assets.scoreSound);
		scoreSound.loop();
		scoreSound.changeVolume(-10f);
		
		scoreComparator = new Comparator<ScoreData>() {
			@Override
			public int compare(ScoreData e1, ScoreData e2) {
				return e1.getScore() < e2.getScore() ? -1: e1.getScore() > e2.getScore() ? 1: 0;
			}
		};
		
		highScores = new PriorityQueue<ScoreData>(10, scoreComparator);
		
		try {
			ArrayList<ScoreData> dataList = JSONParser.readFiles();
			for (ScoreData d : dataList) {
				highScores.add(d);
			}
			
			while(highScores.size() > 10) {
				highScores.poll();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(float dt) {
		returnButton.update();
	}

	@Override
	public void draw(Graphics g) {

		Integer screenWidth = Integer.parseInt(this.conf.obtenerParametro("WIDTH"));

		returnButton.draw(g);
		auxArray = highScores.toArray(new ScoreData[highScores.size()]);
		
		Arrays.sort(auxArray, scoreComparator);
		
		
		Vector2D scorePos = new Vector2D(
				screenWidth / 2 - 200,
				100
				);
		Vector2D datePos = new Vector2D(
				screenWidth / 2 + 200,
				100
				);
		
		Text.drawText(g, this.conf.obtenerParametro("SCORE"), scorePos, true, Color.BLUE, Assets.fontBig);
		Text.drawText(g, this.conf.obtenerParametro("DATE"), datePos, true, Color.BLUE, Assets.fontBig);
		
		scorePos.setY(scorePos.getY() + 40);
		datePos.setY(datePos.getY() + 40);
		
		for(int i = auxArray.length - 1; i > -1; i--) {
			
			ScoreData d = auxArray[i];
			
			Text.drawText(g, Integer.toString(d.getScore()), scorePos, true, Color.WHITE, Assets.fontMed);
			Text.drawText(g, d.getDate(), datePos, true, Color.WHITE, Assets.fontMed);
			
			scorePos.setY(scorePos.getY() + 40);
			datePos.setY(datePos.getY() + 40);
			
		}
		
	}
	
}