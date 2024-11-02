import java.io.*;
import java.util.*;

public class Main {

	public static class Side {
		char[][] cube;

		Side() {
			cube = new char[3][3];
		}

		void turnClock() {
			char[][] tmp = new char[3][3];
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					tmp[c][2 - r] = cube[r][c];
				}
			}
			cube = tmp;
//		    System.out.println("turnClock: " + Arrays.deepToString(cube));
		}

		void turnCounterClock() {
			char[][] tmp = new char[3][3];
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					tmp[2 - c][r] = cube[r][c];
				}
			}
			cube = tmp;
//		    System.out.println("turnClock: " + Arrays.deepToString(cube));
		}

//		void turnCounterClock() {
//			char[][] tmp = new char[3][3];
//			for (int r = 0; r < 3; r++) {
//				for (int c = 0; c < 3; c++) {
//					tmp[r][c] = cube[r][c];
//				}
//			}
//			for (int r = 0; r < 3; r++) {
//				for (int c = 0; c < 3; c++) {
//					this.cube[r][c] = tmp[c][2 - r];
//				}
//			}
//		}

		int[] getSpinDir(char dir) {
			return new int[] {};
		}

		char[] getWillSpinColors(char dir) {
			char[] result = returnTurnVal(this.getSpinDir(dir));
			return result;
		}

		char[] returnTurnVal(int[] dir) {
			char[] result = new char[3];

			for (int i = 0; i < 3; i++) {
				result[i] = this.cube[dir[0] == 1 ? i : dir[0] == -1 ? 2 - i : dir[0]][dir[1] == 1 ? i
						: dir[1] == -1 ? 2 - i : dir[1]];
			}

			return result;
		}

		void changeTurnVal(int[] dir, char[] changed) {
			for (int i = 0; i < 3; i++) {
				this.cube[dir[0] == 1 ? i : dir[0] == -1 ? 2 - i : dir[0]][dir[1] == 1 ? i
						: dir[1] == -1 ? 2 - i : dir[1]] = changed[i];
			}
		}
	}

	public static class USide extends Side {
		USide() {
			super();
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					this.cube[r][c] = 'w';
				}
			}
		}

		@Override
		int[] getSpinDir(char dir) {
			int[] spinDir = new int[2];
			if (dir == 'F') {
				spinDir[0] = 2;
				spinDir[1] = 1;
			} else if (dir == 'B') {
				spinDir[0] = 0;
				spinDir[1] = -1;
			} else if (dir == 'L') {
				spinDir[0] = 1;
				spinDir[1] = 0;
			} else if (dir == 'R') {
				spinDir[0] = -1;
				spinDir[1] = 2;
			}
			return spinDir;
		}
	}

	public static class DSide extends Side {
		DSide() {
			super();
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					this.cube[r][c] = 'y';
				}
			}
		}

		@Override
		int[] getSpinDir(char dir) {
			int[] spinDir = new int[2];
			if (dir == 'F') {
				spinDir[0] = 0;
				spinDir[1] = -1;
			} else if (dir == 'B') {
				spinDir[0] = 2;
				spinDir[1] = 1;
			} else if (dir == 'L') {
				spinDir[0] = 1;
				spinDir[1] = 0;
			} else if (dir == 'R') {
				spinDir[0] = -1;
				spinDir[1] = 2;
			}
			return spinDir;
		}
	}

	public static class FSide extends Side {
		FSide() {
			super();
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					this.cube[r][c] = 'r';
				}
			}
		}

		@Override
		int[] getSpinDir(char dir) {
			int[] spinDir = new int[2];
			if (dir == 'U') {
				spinDir[0] = 0;
				spinDir[1] = -1;
			} else if (dir == 'D') {
				spinDir[0] = 2;
				spinDir[1] = 1;
			} else if (dir == 'L') {
				spinDir[0] = 1;
				spinDir[1] = 0;
			} else if (dir == 'R') {
				spinDir[0] = -1;
				spinDir[1] = 2;
			}
			return spinDir;
		}
	}

	public static class BSide extends Side {
		BSide() {
			super();
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					this.cube[r][c] = 'o';
				}
			}
		}

		@Override
		int[] getSpinDir(char dir) {
			int[] spinDir = new int[2];
			if (dir == 'U') {
				spinDir[0] = 0;
				spinDir[1] = -1;
			} else if (dir == 'D') {
				spinDir[0] = 2;
				spinDir[1] = 1;
			} else if (dir == 'L') {
				spinDir[0] = -1;
				spinDir[1] = 2;
			} else if (dir == 'R') {
				spinDir[0] = 1;
				spinDir[1] = 0;
			}
			return spinDir;
		}
	}

	public static class LSide extends Side {
		LSide() {
			super();
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					this.cube[r][c] = 'g';
				}
			}
		}

		@Override
		int[] getSpinDir(char dir) {
			int[] spinDir = new int[2];
			if (dir == 'U') {
				spinDir[0] = 0;
				spinDir[1] = -1;
			} else if (dir == 'D') {
				spinDir[0] = 2;
				spinDir[1] = 1;
			} else if (dir == 'F') {
				spinDir[0] = -1;
				spinDir[1] = 2;
			} else if (dir == 'B') {
				spinDir[0] = 1;
				spinDir[1] = 0;
			}
			return spinDir;
		}
	}

	public static class RSide extends Side {
		RSide() {
			super();
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					this.cube[r][c] = 'b';
				}
			}
		}

		@Override
		int[] getSpinDir(char dir) {
			int[] spinDir = new int[2];
			if (dir == 'U') {
				spinDir[0] = 0;
				spinDir[1] = -1;
			} else if (dir == 'D') {
				spinDir[0] = 2;
				spinDir[1] = 1;
			} else if (dir == 'F') {
				spinDir[0] = 1;
				spinDir[1] = 0;
			} else if (dir == 'B') {
				spinDir[0] = -1;
				spinDir[1] = 2;
			}
			return spinDir;
		}
	}

	public static class Rubix {
		// 위(U, 0, w), 아래(D, 1, y), 앞(F, 2, r), 뒤(B, 3, o), 왼(L, 4, g), 오(R, 5, b)
		Side[] sides = new Side[6];

		Rubix() {
			sides[0] = new USide();
			sides[1] = new DSide();
			sides[2] = new FSide();
			sides[3] = new BSide();
			sides[4] = new LSide();
			sides[5] = new RSide();
		}

		int[] turnDir(char side) { // 시계방향일 때 기준
			switch (side) {
			case 'U':
				return new int[] { 3, 5, 2, 4 };
			case 'D':
				return new int[] { 4, 2, 5, 3 };
			case 'F': //
				return new int[] { 0, 5, 1, 4 };
			case 'B': //
				return new int[] { 4, 1, 5, 0 };
			case 'L': //
				return new int[] { 0, 2, 1, 3 };
			case 'R':
				return new int[] { 3, 1, 2, 0 };
			}
			return new int[] { 0 };
		}

		int getIdx(char side) {
			switch (side) {
			case 'U':
				return 0;
			case 'D':
				return 1;
			case 'F':
				return 2;
			case 'B':
				return 3;
			case 'L':
				return 4;
			case 'R':
				return 5;
			}
			return -1;
		}

		void turn(String dir) {
			char side = dir.charAt(0); // 어느 면 돌리는지
			char cC = dir.charAt(1); // clockwise or counterclockwise

			if (cC == '+') {
//				System.out.println("side: " + side + ", " + this.getIdx(side));
				this.sides[this.getIdx(side)].turnClock();
			} else {
				this.sides[this.getIdx(side)].turnCounterClock();
			}

			int[] turnOrder = this.turnDir(side);
			if (cC == '-') {
				for (int i = 0; i < 2; i++) {
					int tmp = turnOrder[i];
					turnOrder[i] = turnOrder[3 - i];
					turnOrder[3 - i] = tmp;
				}
			}

			char[] tmp = this.sides[turnOrder[3]].getWillSpinColors(side);
			for (int i = 3; i >= 1; i--) {
				// i 자리에다가 i-1 값을 넣을꺼임
				char[] before = this.sides[turnOrder[i - 1]].getWillSpinColors(side);
				this.sides[turnOrder[i]].changeTurnVal(this.sides[turnOrder[i]].getSpinDir(side), before);
			}
			this.sides[turnOrder[0]].changeTurnVal(this.sides[turnOrder[0]].getSpinDir(side), tmp);
//			System.out.println("Top: " + Arrays.deepToString(this.sides[0].cube));
//			System.out.println("Under: " + Arrays.deepToString(this.sides[1].cube));
//			System.out.println("Front: " + Arrays.deepToString(this.sides[2].cube));
//			System.out.println("Back: " + Arrays.deepToString(this.sides[3].cube));
//			System.out.println("Left: " + Arrays.deepToString(this.sides[4].cube));
//			System.out.println("Right: " + Arrays.deepToString(this.sides[5].cube));
//			System.out.println("----------------");

		}

		String returnTop() {
			String top = "";
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					top += this.sides[0].cube[r][c];
				}
				if (r != 3)
					top += "\n";
			}
			return top;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			Rubix rubix = new Rubix();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				rubix.turn(st.nextToken());
			}
			sb.append(rubix.returnTop());
		}
		System.out.println(sb);
	}
}