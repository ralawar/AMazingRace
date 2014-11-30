package maze;

public class Maze {

	private int width, height;
	private MazeCell[][] maze;

	public Maze(int width, int height) {

		this.width = width;
		this.height = height;

		generateMaze();
	}

	private void generateMaze() {
		maze = new MazeCell[height][width];

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				maze[i][j] = new MazeCell(j, i);
			}
		}

		maze[height / 2 - 1][0].setStart(true);
		maze[height / 2 - 1][0].setWall(MazeCell.LEFT, false);
		maze[height - 1][width - 1].setEnd(true);

	}

	public MazeCell[][] getMaze() {
		return maze;
	}

	public void printMaze() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				// System.out.print("(" + maze[i][j].x + ", " + maze[i][j].y + ")\t");
				if (maze[i][j].getWall(MazeCell.TOP)) {
					System.out.print("-------");
				} else {
					System.out.print("       ");
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
					System.out.print("S  ");
				} else if (maze[i][j].isEnd()) {
					System.out.print("E  ");
				} else if (maze[i][j].isVisited()) {
					System.out.print("V  ");
				} else {
					System.out.print("   ");
				}

				if (maze[i][j].getWall(MazeCell.RIGHT)) {
					System.out.print("|");
				}
			}

			System.out.println();

			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j].getWall(MazeCell.BOTTOM)) {
					System.out.print("-------");
				} else {
					System.out.print("       ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Maze test = new Maze(10, 10);
		test.printMaze();

	}

}
