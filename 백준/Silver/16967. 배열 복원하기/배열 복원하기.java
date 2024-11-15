import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int[][] result = new int[H + X][W + Y];
		for (int r = 0; r < H + X; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < W + Y; c++) {
				result[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] init = new int[H][W];
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < Y; c++) {
				init[r][c] = result[r][c];
			}
		}
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < X; r++) {
				init[r][c] = result[r][c];
			}
		}

		for (int r = X; r < H; r++) {
			for (int c = Y; c < W; c++) {
				init[r][c] = result[r][c] - init[r - X][c - Y];
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				sb.append(init[r][c] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}