
public class GameState {
	private int width, height;
	private int moveTo; 
	// if -1, stay where you are
	// 0   move left
	// 1   move right
	// 2   move up
	// 3   move down
	private Monkey monkey;
	private String background;
	private Banana banana;
	private int currentState; // if currentState == 0, game is on, if -1 game lost, if 1 game won
	
	public GameState(Monkey monkey, Banana banana, int currentState){
		this.setMoveTo(-1); //stay where you are initially 
		this.setMonkey(monkey);
		this.setBanana(banana);
		this.setCurrentState(currentState);
	}
	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	public int getMoveTo() {
		return moveTo;
	}


	public void setMoveTo(int moveTo) {
		this.moveTo = moveTo;
	}


	public Monkey getMonkey() {
		return monkey;
	}


	public void setMonkey(Monkey monkey) {
		this.monkey = monkey;
	}


	public String getBackground() {
		return background;
	}


	public void setBackground(String background) {
		this.background = background;
	}


	public Banana getBanana() {
		return banana;
	}


	public void setBanana(Banana banana) {
		this.banana = banana;
	}


	public int getCurrentState() {
		return currentState;
	}


	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}
	
	
}
