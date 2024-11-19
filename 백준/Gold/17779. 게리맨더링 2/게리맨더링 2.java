import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] A;

	static int popDiff = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int d1 = 1; d1 <= c + 1; d1++) {
					for (int d2 = 1; d2 <= N - c; d2++) {
						if (canMake(r, c, d1, d2)) {
							getDistrictPopulation(r, c, d1, d2);
						}
					}
				}
			}
		}

		System.out.println(popDiff);
	}

	public static boolean canMake(int sr, int sc, int d1, int d2) {
		if (sr >= 0 && sr < N && sc >= 0 && sc < N && sr + d1 + d2 < N && sc - d1 >= 0 && sc + d2 < N)
			return true;
		return false;
	}

	public static void getDistrictPopulation(int sr, int sc, int d1, int d2) {
		int[][] result = new int[N][N];
		int[] population = new int[5];

		boolean[][] border = new boolean[N][N];

		for (int d = 0; d <= d1; d++) {
			border[sr + d][sc - d] = true;
		}
		for (int d = 0; d <= d2; d++) {
			border[sr + d][sc + d] = true;
		}

		for (int d = 0; d <= d1; d++) {
			border[sr + d2 + d][sc + d2 - d] = true;
		}

		for (int d = 0; d <= d2; d++) {
			border[sr + d1 + d][sc - d1 + d] = true;
		}

		for (int c = 0; c <= sc; c++) {
			for (int r = 0; r < sr + d1; r++) {
				if (border[r][c])
					break;
				result[r][c] = 1;
			}
		}

		for (int c = sc + 1; c < N; c++) {
			for (int r = 0; r <= sr + d2; r++) {
				if (border[r][c])
					break;
				result[r][c] = 2;
			}
		}

		for (int c = 0; c < sc - d1 + d2; c++) {
			for (int r = N - 1; r >= sr + d1; r--) {
				if (border[r][c])
					break;
				result[r][c] = 3;
			}
		}

		for (int c = sc - d1 + d2; c < N; c++) {
			for (int r = N-1; r > sr + d2; r--) {
				if (border[r][c])
					break;
				result[r][c] = 4;
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				population[result[r][c] == 0 ? 4 : result[r][c] - 1] += A[r][c];
			}
		}

		int minPop = population[0];
		int maxPop = population[0];

		for (int i = 1; i < 5; i++) {
			minPop = Math.min(minPop, population[i]);
			maxPop = Math.max(maxPop, population[i]);
		}

		popDiff = Math.min(popDiff, maxPop - minPop);
	}
}