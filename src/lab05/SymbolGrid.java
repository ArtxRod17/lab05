/* Arturo Rodriguez
 * CS 2013
 * Section: 81
 * Description: This class is designed to find find a solution of a symbol grid using backtracking.
 * Other Comments: I was no able to join a group as I had missed the wednesday class session.
 */
package lab05;

import java.util.ArrayList;
import java.util.List;

public class SymbolGrid {

	private static char[] SYMBOLS = { '~', '!', '@', '#', '$', '^', '&', '*' };

	// NOTE:
	// Do not change this method signature.
	// This is method calls another recursive method, but it should
	// not call itself.
	public static void findAllPaths(char[][] grid, char[] sequence) {

		// TO DO:
		// Add code to traverse the grid and search for paths
		// starting at each cell using the findPathsAt() method.
		//
		//
		// USE two for loops to traverse the grid
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {

				// This method will call the findPathAt() method

				if (grid[i][j] == sequence[0]) {

					Cell cellInfo = new Cell(i, j);
					boolean[][] hasSeen = new boolean[grid.length][grid[0].length];
					hasSeen[i][j] = true;
					// Add cell info to new path
					List<Cell> path = new ArrayList<>();
					path.add(cellInfo);
					// End with Recursive method call
					findPathsAt(grid, cellInfo, path, sequence, 1, hasSeen);
				}
			}
		}

		System.out.println("\n--- finished searching");
	}

	// TO DO:
	// Implement recursive method with backtracking
	//
	// NOTE: You may change the list of parameters here
	private static void findPathsAt(char[][] grid, Cell cell, List<Cell> path, char[] sequence, int index,
			boolean[][] hasSeen) {
		// Restrictions
		if (index == sequence.length) {
			for (int i = 0; i < path.size(); i++) {
				// USE GIVEN TO STRING TO PRINT OUT SEQUENCE PATHS
				System.out.print(sequence[i] + path.get(i).toString() + "  ");
			}
			// Create a new row of values to print out so it is formatted
			System.out.println();
			return;

		}
		// RECURSION METHOD
		else {
			int[] tempArr = { -1, 0, 1 };
			for (int i = 0; i < tempArr.length; i++) {
				for (int j = 0; j < tempArr.length; j++) {
					// USE THE CELL CLASS TO ASSIGN THE VALUES

					int row = cell.r + tempArr[i];
					int col = cell.c + tempArr[j];
					//
					if (col >= 0 && col < grid[0].length && row >= 0 && row < grid.length) {
						// if cell hasnt been visited, assign grid value to sequence
						if (hasSeen[row][col] == false && grid[row][col] == sequence[index]) {
							// Following cell creation
							Cell followingCell = new Cell(row, col);
							// Add the following cell to the path
							path.add(followingCell);
							// Set has seen true because cell has been visited
							hasSeen[row][col] = true;
							// Call the recursive method
							findPathsAt(grid, followingCell, path, sequence, index + 1, hasSeen);
							// Remove seen path and reset has Seen to false
							path.remove(path.size() - 1);
							hasSeen[row][col] = false;

						}
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		char[][] grid = randomSymbolGrid(7);
		displaySymbolGrid(grid);

		System.out.println();

		char[] seq = randomSymbolSequence(4);
		System.out.print("sequence: ");
		System.out.println(seq);

		System.out.println("\npaths:");
		findAllPaths(grid, seq);

	}

	/* Helper methods below -- you shouldn't need to alter them */

	private static char[] randomSymbolSequence(int length) {
		char[] sequence = new char[length];
		for (int i = 0; i < length; i++) {
			sequence[i] = SYMBOLS[(int) (Math.random() * SYMBOLS.length)];
		}
		return sequence;
	}

	private static char[][] randomSymbolGrid(int size) {
		char[][] grid = new char[size][size];
		for (int r = 0; r < size; r++) {
			grid[r] = randomSymbolSequence(size);
		}
		return grid;
	}

	private static void displaySymbolGrid(char[][] grid) {
		// Display column indices
		System.out.print("\n    ");
		for (int i = 0; i < grid.length; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();

		// Display grid
		for (int r = 0; r < grid.length; r++) {
			// Display row index
			System.out.print("  " + r + " ");
			for (int c = 0; c < grid[r].length; c++) {
				System.out.print(grid[r][c] + "  ");
			}
			System.out.println();
		}
	}

}

/*
 * Represents a cell on a grid -- just a convenient way of packaging a pair of
 * indices
 */
class Cell {
	int r, c;

	Cell(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public boolean equals(Object o) {
		Cell cell = (Cell) o;
		return this.r == cell.r && this.c == cell.c;
	}

	@Override
	public String toString() {
		return "(" + r + ", " + c + ")";
	}
}
