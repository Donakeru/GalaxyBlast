package io;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ScoreData {
	

	private String date;
	private int score;
	
	public ScoreData (int score) {
		this.score = score;
		
		Date today = new Date(System.currentTimeMillis());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		date = format.format(today);
	}

	public ScoreData() {
		
	}

	public String getDate() {
		return date;
	}

	public int getScore() {
		return score;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
