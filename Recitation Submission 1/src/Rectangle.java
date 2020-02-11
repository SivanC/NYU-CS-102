public class Rectangle {
	private int length;
	private int width;
	
	public Rectangle() {
		this.length = 1;
		this.width = 1;
	}
	public Rectangle(int length, int width) {
		this.length = length;
		this.width = width;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int calculateArea() {
		int area = this.length * this.width;
		for (int i = 0; i < 10; i++) {
			System.out.println(area);
		}
		
		return area;
	}
	public boolean checkSquare() {
		return this.length == this.width;
	}
}
