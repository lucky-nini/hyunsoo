import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] connections;
	static int[][] canGo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		connections = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				connections[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		canGo = new int[N][N];
		for (int r = 0; r < N; r++) {
			boolean[][] visited = new boolean[N][N];
			for (int c = 0; c < N; c++) {
				if (connections[r][c] == 1 && !visited[r][c]) {
					visited[r][c] = true;
					canGo[r][c] = 1;
					connect(r, c, visited);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < N; r++) {
			for (int c=0; c<N; c++) {
				sb.append(canGo[r][c]+" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	public static void connect(int ir, int r, boolean[][] visited) {
		for (int c = 0; c < N; c++) {
			if (connections[r][c] == 1 && !visited[r][c]) {
				canGo[ir][c] = 1;
				canGo[r][c] = 1;
				visited[r][c] = true;
				visited[ir][c] = true;
				connect(ir, c, visited);
			}
		}
	}
}