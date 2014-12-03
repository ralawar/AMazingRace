package maze;

import java.util.Arrays;

public class MazeCell {

	public static final int NUM_WALLS = 4;
	public static final int TOP = 0;
	public static final int BOTTOM = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	private int x, y;
	private boolean visited;
	private boolean start;
	private boolean end;

	public boolean[] walls;

	public MazeCell(int x, int y) {

		this.setX(x);
		this.setY(y);

		walls = new boolean[NUM_WALLS];
		Arrays.fill(walls, true);
		visited = false;
		start = false;
		end = false;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setWall(int wall, boolean b) {

		if (wall == TOP) {
			walls[TOP] = b;
		} else if (wall == BOTTOM) {
			walls[BOTTOM] = b;
		} else if (wall == LEFT) {
			walls[LEFT] = b;
		} else if (wall == RIGHT) {
			walls[RIGHT] = b;
		}
	}

	public boolean getWall(int wall) {

		if (wall == TOP) {
			return walls[TOP];
		} else if (wall == BOTTOM) {
			return walls[BOTTOM];
		} else if (wall == LEFT) {
			return walls[LEFT];
		} else if (wall == RIGHT) {
			return walls[RIGHT];
		}

		return false;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
