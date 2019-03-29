import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class MonkeyBanana extends PApplet{
	
	private static final String LOSING_MESSAGE = "Game Lost";
	private static final String WINNING_MESSAGE = "Game Won!";
	private static final int HEIGHT = 900;
	private static final int WIDTH = 900;
	private static final String MONKEY_IMG_PATH = "res/img/monkey.jpg";
	private static final String BACKGROUND_PATH = "res/img/grassBack.jpg";
	private static final int GRID_NUM_X = 50 + 1;
	private static final int GRID_NUM_Y = 50 + 1;
	private static final int UNIT_STEP_X = WIDTH / GRID_NUM_X;
	private static final int UNIT_STEP_Y = HEIGHT / GRID_NUM_Y;
	private static final int MONKEY_WIDTH = WIDTH/GRID_NUM_X;
	private static final int MONKEY_HEIGHT = HEIGHT/GRID_NUM_Y;
	private static final int MONKEY_START_GRID_X = 0;
	private static final int MONKEY_START_GRID_Y = GRID_NUM_Y / 2;
	private static final String BANANA_IMG_PATH = "res/img/banana.png";
	private static final int BANANA_GRID_X = GRID_NUM_X - 2;
	private static final int BANANA_GRID_Y = GRID_NUM_Y / 2;
	private static final int BANANA_WIDTH = WIDTH/GRID_NUM_X;
	private static final int BANANA_HEIGHT = HEIGHT/GRID_NUM_Y;
	private static final int TIMEOUT_LIMIT = 10;
	private static final int BANANA_TO_WIN = 10;
	private static final int WIN_STATE = 1;
	private static final int PLAYING_STATE = 0;
	private static final int LOST_STATE = -1;
	private static final int GAME_TIME_LIMIT = 30; //time limit for game in seconds

	public static void main(String[] args) {
		PApplet.main("MonkeyBanana"); // should pass in the class name of the 
									  // class which extends PApplet
	}
	
	private GameState state;
	private PImage backImg, monkey, banana;
	private int bananaStartSec;
	private int gameStartSec;
	private int bananaCount;
	private boolean alreadyIntersecting;
	private Random randomGenerator;
	
	public MonkeyBanana() {
		Monkey monkey = new Monkey(MONKEY_IMG_PATH, MONKEY_START_GRID_X, MONKEY_START_GRID_Y, MONKEY_WIDTH, MONKEY_HEIGHT);
		Banana banana = new Banana(BANANA_IMG_PATH, BANANA_GRID_X, BANANA_GRID_Y, BANANA_WIDTH, BANANA_HEIGHT);
		state = new GameState(monkey, banana, PLAYING_STATE);
		state.setHeight(HEIGHT);
		state.setWidth(WIDTH);
		state.setBackground(BACKGROUND_PATH);	
		
		randomGenerator = new Random();
		bananaCount = 0;
		alreadyIntersecting = false;
	}
	
	
	@Override
	public void setup() {
		backImg = loadImage(state.getBackground());
		backImg.resize(state.getWidth(), state.getHeight());
		
		monkey = loadImage(state.getMonkey().getImgPath());
		monkey.resize(state.getMonkey().getWidth(), state.getMonkey().getHeight());
		
		banana = loadImage(state.getBanana().getImgPath());
		banana.resize(state.getBanana().getWidth(), state.getBanana().getHeight());
		
		bananaStartSec = second(); //assign current second
		gameStartSec = bananaStartSec;
	}
	
	@Override
	public void settings() {
		size(state.getWidth(), state.getHeight());
	}
	
	@Override
	public void draw() {
		background(backImg);
		textSize(26);
		text("" + bananaCount, 0, HEIGHT - 20);

		switch(state.getCurrentState()){
		case PLAYING_STATE:
			//playing
			int timeLeft = (GAME_TIME_LIMIT - differenceInSec(second(), gameStartSec));
			text("" + timeLeft , WIDTH - 30, HEIGHT - 20);
			
			image(banana, state.getBanana().getGridX() * UNIT_STEP_X, 
					state.getBanana().getGridY() * UNIT_STEP_Y);
			image(monkey, state.getMonkey().getGridX() * UNIT_STEP_X, 
								state.getMonkey().getGridY() * UNIT_STEP_Y);
			
			if(timeOut(second(), bananaStartSec, TIMEOUT_LIMIT)){
				resetBanana(state);
			}
			
			if(isIntersecting(state)){
				if(!alreadyIntersecting){
					bananaCount++;
					if(bananaCount == BANANA_TO_WIN){
						state.setCurrentState(WIN_STATE);
					}
					resetBanana(state);
					alreadyIntersecting = true;
				}
			}else{
				alreadyIntersecting = false;
			}
			
			if(timeOut(second(), gameStartSec, GAME_TIME_LIMIT))
				state.setCurrentState(LOST_STATE);
			
			break;
		case WIN_STATE:
			// won
			text(WINNING_MESSAGE, 500, 500);
			break;
		case LOST_STATE:
			// lost
			text("" + 0 , WIDTH - 30, HEIGHT - 20);
			text(LOSING_MESSAGE, 500, 500);
			break;
		}
		
	}
	



	private void resetBanana(GameState gameState) {
		int newGridX = randomGenerator.nextInt(BANANA_GRID_X);
		int newGridY = randomGenerator.nextInt(BANANA_GRID_Y);
		
		gameState.getBanana().setGridX(newGridX);
		gameState.getBanana().setGridY(newGridY);
		
		bananaStartSec = second(); //set current second
	}


	private boolean timeOut(int second, int first, int limit) {
		
		if(differenceInSec(second, first) > limit)
			return true;
		
		return false;
	}
	
	private int differenceInSec(int second, int first){
		if(second >= first){
			return second - first;
		}else{
			return (second + (60 - first) ); 
		}
		
	}


	private boolean isIntersecting(GameState gameState) {
		if(state.getMonkey().getGridX() == state.getBanana().getGridX() && 
				state.getMonkey().getGridY() == state.getBanana().getGridY()){
			return true;
		}
		return false;
	}


	@Override
	public void keyPressed() {
		
		//TODO IMPORTANT NOTE : USING W A S D and not the arrows 
			
		int curGridY, curGridX;
		switch(key){
		case 'w':
			curGridY = state.getMonkey().getGridY();
			
			if(curGridY - 1 >= 0)
				state.getMonkey().setGridY(curGridY - 1); 
			break;
			
		case 'a':
			curGridX = state.getMonkey().getGridX();
			if(curGridX - 1 >= 0)
				state.getMonkey().setGridX(curGridX - 1);
			break;
			
		case 's':
			curGridY = state.getMonkey().getGridY();
			if(curGridY + 1 <= GRID_NUM_Y)
				state.getMonkey().setGridY(curGridY + 1);
			break;
			
		case 'd':
			curGridX = state.getMonkey().getGridX();
			if(curGridX + 1 <= GRID_NUM_X)
				state.getMonkey().setGridX(curGridX + 1);
			break;
		}
			
	}

}
