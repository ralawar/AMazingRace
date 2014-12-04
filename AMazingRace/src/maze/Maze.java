package maze;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import main.GamePanel;

/* Class: Maze.java
 * Author: Raid Alawar
 * Purpose: To generate and draw randomly generated mazes.
 */

public class Maze {

	private int width, height, cellsVisited, totalCells;
	private MazeCell[][] maze;
	private MazeCell startCell, endCell;
	private ArrayList<Rectangle> wallGraphics;
	private float heightScale;
	private float widthScale;
	private float wallScale;
	private int horizontalWallHeight;
	private int horizontalWallWidth;
	private int verticalWallHeight;
	private int verticalWallWidth;
	private int rightWallOffset;
	private int bottomWallOffset;

	public Maze(int width, int height) {

		this.width = width;
		this.height = height;
		cellsVisited = 0;

		// variables for drawing walls
		totalCells = this.width * this.height;
		heightScale = (GamePanel.HEIGHT) / height;
		widthScale = (GamePanel.WIDTH) / width;
		wallScale = 0.1f;
		horizontalWallHeight = (int) (wallScale * heightScale);
		horizontalWallWidth = (int) widthScale;
		verticalWallHeight = (int) heightScale;
		verticalWallWidth = (int) (wallScale * widthScale);
		rightWallOffset = horizontalWallWidth - verticalWallWidth;
		bottomWallOffset = verticalWallHeight - horizontalWallHeight;
		wallGraphics = new ArrayList<Rectangle>();

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
		generateWallGraphics();
	}

	private void generateWallGraphics() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				MazeCell cell = maze[i][j];
				if (cell.getWall(MazeCell.TOP)) {
					wallGraphics.add(new Rectangle((int) (j * widthScale), (int) (i * heightScale),
							horizontalWallWidth, horizontalWallHeight));
				}
				if (cell.getWall(MazeCell.BOTTOM)) {
					wallGraphics.add(new Rectangle((int) (j * widthScale), (int) (i * heightScale)
							+ bottomWallOffset, horizontalWallWidth, horizontalWallHeight));
				}
				if (cell.getWall(MazeCell.LEFT)) {
					wallGraphics.add(new Rectangle((int) (j * widthScale), (int) (i * heightScale),
							verticalWallWidth, verticalWallHeight));
				}
				if (cell.getWall(MazeCell.RIGHT)) {
					wallGraphics.add(new Rectangle((int) (j * widthScale) + rightWallOffset,
							(int) (i * heightScale), verticalWallWidth, verticalWallHeight));
				}
			}
		}
	}

	private void generateMaze() {

		MazeCell currentCell = startCell;
		MazeCell nextCell;
		Random rand = new Random(System.currentTimeMillis());
		ArrayList<MazeCell> unvisitedNeighbors;
		Stack<MazeCell> stack = new Stack<MazeCell>();

		// randomly generate paths in the maze until entire maze is generated
		while (cellsVisited < totalCells) {
			unvisitedNeighbors = getUnvisitedNeighbors(currentCell);
			if (unvisitedNeighbors.size() > 0) {
				nextCell = unvisitedNeighbors.get(rand.nextInt(unvisitedNeighbors.size()));
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

		// destroy walls between two cells
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

	public MazeCell[][] getMaze() {
		return maze;
	}

	public MazeCell getStartCell() {
		return startCell;
	}

	public MazeCell getEndCell() {
		return endCell;
	}

	public float getHeightScale() {
		return heightScale;
	}

	public float getWidthScale() {
		return widthScale;
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

	public ArrayList<Rectangle> getWallGraphics() {
		return wallGraphics;
	}

	public void draw(Graphics2D g) {

		// draw end zone
		g.setColor(Color.BLUE);
		g.fillRect((int) (endCell.getX() * widthScale), (int) (endCell.getY() * heightScale),
				(int) widthScale, (int) heightScale);

		// draw walls
		g.setColor(Color.BLACK);
		for (Rectangle rect : wallGraphics) {
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
		}

		// draw start and end zone text
		g.setFont(new Font("Arial", Font.PLAIN, 8));
		g.setColor(Color.ORANGE);
		g.drawString("START", 0, (int) (startCell.getY() * heightScale + heightScale / 2));
		g.drawString("END", (int) (endCell.getX() * widthScale + verticalWallWidth * 2),
				(int) (endCell.getY() * heightScale + heightScale / 2));

	}
}
