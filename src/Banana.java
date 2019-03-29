public class Banana {
	private int width, height, gridX, gridY;
	private String imgPath;
	
	public Banana(String imgPath, int gridX, int gridY, int width, int height) {
		this.setWidth(width);
		this.setHeight(height);
		this.setGridX(gridX);
		this.setGridY(gridY);
		this.setImgPath(imgPath);
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



	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getGridX() {
		return gridX;
	}

	public void setGridX(int gridX) {
		this.gridX = gridX;
	}

	public int getGridY() {
		return gridY;
	}

	public void setGridY(int gridY) {
		this.gridY = gridY;
	}


}
