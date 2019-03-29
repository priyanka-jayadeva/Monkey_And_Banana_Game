
public class Monkey {
	private int gridX, gridY;
	private String imgPath;
	private int width, height;
	
	public Monkey(String imgPath, int gridX, int gridY, int width, int height){
		this.setGridX(gridX);
		this.setGridY(gridY);
		this.setImgPath(imgPath);
		this.setWidth(width);
		this.setHeight(height);
	}


	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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

	public int getGridY() {
		return gridY;
	}

	public void setGridY(int gridY) {
		this.gridY = gridY;
	}

	public int getGridX() {
		return gridX;
	}

	public void setGridX(int gridX) {
		this.gridX = gridX;
	}
}
