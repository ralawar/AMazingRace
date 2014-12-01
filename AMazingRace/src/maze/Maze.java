package maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import main.GamePanel;

public class Maze {

	private int LocationX, locationY, width, height, cellsVisited, totalCells;
	private MazeCell[][] maze;
	MazeCell startCell, endCell;

	public Maze(int LocationX, int locationY, int width, int height) {

		this.LocationX = LocationX;
		this.locationY = locationY;
		this.width = width;
		this.height = height;
		cellsVisited = 0;
		totalCells = this.width * this.height;

		maze = new MazeCell[height][width];

		startCell = new MazeCell(0, height / 2 - 1);
		maze[height / 2 - 1][0] = startCell;
		startCell.setStart(true);
		startCell.setVisited(true);
		cellsVisited++;
		startCell.setWall(MazeCell.LEFT, false);

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == null) {
					maze[i][j] = new MazeCell(j, i);
				}
			}
		}

		endCell = maze[height - 1][width - 1];
		endCell.setEnd(true);

		generateMaze();
	}

	private void generateMaze() {

		MazeCell currentCell = startCell;
		MazeCell nextCell;
		Random rand = new Random(System.currentTimeMillis());
		ArrayList<MazeCell> unvisitedNeighbors;
		Stack<MazeCell> stack = new Stack<MazeCell>();

		while (cellsVisited < totalCells) {
			unvisitedNeighbors = getUnvisitedNeighbors(currentCell);
			if (unvisitedNeighbors.size() > 0) {
				nextCell = unvisitedNeighbors.get((rand.nextInt(unvisitedNeighbors.size())));
				destroyCellWalls(currentCell, nextCell);
				stack.push(currentCell);
				currentCell = nextCell;
				currentCell.setVisited(true);
				cellsVisited++;
			} else if (stack.size() > 0) {
				currentCell = stack.pop();
			} else {
				currentCell = getUnvisitedCell();
				currentCell.setVisited(true);
				cellsVisited++;
			}

		}

	}

	private MazeCell getUnvisitedCell() {

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (!maze[i][j].isVisited()) {
					return maze[i][j];
				}
			}
		}
		return null;
	}

	private void destroyCellWalls(MazeCell cell1, MazeCell cell2) {

		if (cell1 == cell2 || cell1 == null || cell2 == null) {
			return;
		}

		if (cell1.getY() < cell2.getY()) {
			cell1.setWall(MazeCell.BOTTOM, false);
			cell2.setWall(MazeCell.TOP, false);
		} else if (cell1.getY() > cell2.getY()) {
			cell1.setWall(MazeCell.TOP, false);
			cell2.setWall(MazeCell.BOTTOM, false);
		} else if (cell1.getX() < cell2.getX()) {
			cell1.setWall(MazeCell.RIGHT, false);
			cell2.setWall(MazeCell.LEFT, false);
		} else {
			cell1.setWall(MazeCell.LEFT, false);
			cell2.setWall(MazeCell.RIGHT, false);
		}

	}

	private ArrayList<MazeCell> getUnvisitedNeighbors(MazeCell cell) {

		int x = cell.getX();
		int y = cell.getY();
		ArrayList<MazeCell> unvisitedNeighbors = new ArrayList<MazeCell>();

		if (y > 0 && !maze[y - 1][x].isVisited()) {
			unvisitedNeighbors.add(maze[y - 1][x]);
		}
		if (y < height - 1 && !maze[y + 1][x].isVisited()) {
			unvisitedNeighbors.add(maze[y + 1][x]);
		}
		if (x > 0 && !maze[y][x - 1].isVisited()) {
			unvisitedNeighbors.add(maze[y][x - 1]);
		}
		if (x < width - 1 && !maze[y][x + 1].isVisited()) {
			unvisitedNeighbors.add(maze[y][x + 1]);
		}

		return unvisitedNeighbors;
	}

	/*
	 * private MazeCell getCellNeighbor(MazeCell cell, int neighbor) {
	 * 
	 * if (neighbor == MazeCell.TOP) { return maze[cell.getY() - 1][cell.getX()]; } else if (neighbor ==
	 * MazeCell.BOTTOM) { return maze[cell.getY() + 1][cell.getX()]; } else if (neighbor == MazeCell.LEFT) { return
	 * maze[cell.getY()][cell.getX() - 1]; } else if (neighbor == MazeCell.RIGHT) { return maze[cell.getY()][cell.getX()
	 * + 1]; }
	 * 
	 * return null; }
	 */

	public MazeCell[][] getMaze() {
		return maze;
	}

	// print maze to console (for testing purposes)
	public void printMaze() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j].getWall(MazeCell.TOP)) {
					System.out.print("-------");
				} else {
					System.out.print("|     |");
				}
			}
			System.out.println();

			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j].getWall(MazeCell.LEFT)) {
					System.out.print("|  ");
				} else {
					System.out.print("   ");
				}

				if (maze[i][j].isStart()) {
					System.out.print("S");
				} else if (maze[i][j].isEnd()) {
					System.out.print("E");
				} // else if (maze[i][j].isVisited()) { System.out.print("V"); }
				else {
					System.out.print(" ");
				}

				if (maze[i][j].getWall(MazeCell.RIGHT)) {
					System.out.print("  |");
				} else {

					System.out.print("   ");

				}
			}

			System.out.println();

			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j].getWall(MazeCell.BOTTOM)) {
					System.out.print("-------");
				} else {
					System.out.print("|     |");
				}
			}
			System.out.println();
		}
	}

	/*
	 * public static void main(String[] args) { Maze test = new Maze(0, 0, 20, 20); test.printMaze();
	 * 
	 * }
	 */

	public void draw(Graphics2D g) {

		g.setColor(Color.BLACK);
		float heightScale = GamePanel.HEIGHT / height;
		float widthScale = GamePanel.WIDTH / width;
		float wallScale = 0.2f;
		int horizontalWallHeight = (int) (wallScale * heightScale);
		int horizontalWallWidth = (int) widthScale;
		int verticalWallHeight = (int) heightScale;
		int verticalWallWidth = (int) (wallScale * widthScale);
		int rightWallOffset = horizontalWallWidth - verticalWallWidth;
		int bottomWallOffset = verticalWallHeight - horizontalWallHeight;

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				MazeCell cell = maze[i][j];
				if (cell.getWall(MazeCell.TOP)) {
					g.fillRect((int) (j * widthScale), (int) (i * heightScale), horizontalWallWidth, horizontalWallHeight);
				}
				if (cell.getWall(MazeCell.BOTTOM)) {
					g.fillRect((int) (j * widthScale), (int) (i * heightScale) + bottomWallOffset, horizontalWallWidth, horizontalWallHeight);
				}
				if (cell.getWall(MazeCell.LEFT)) {
					g.fillRect((int) (j * widthScale), (int) (i * heightScale), verticalWallWidth, verticalWallHeight);
				}
				if (cell.getWall(MazeCell.RIGHT)) {
					g.fillRect((int) (j * widthScale) + rightWallOffset, (int) (i * heightScale), verticalWallWidth, verticalWallHeight);
				}
			}
		}

		g.setColor(Color.RED);
		g.drawString("S", (int) (startCell.getX() * widthScale + verticalWallWidth), (int) (startCell.getY() * heightScale + heightScale / 2));
		g.drawString("E", (int) (endCell.getX() * widthScale + verticalWallWidth), (int) (endCell.getY() * heightScale + heightScale / 2));

	}
}
