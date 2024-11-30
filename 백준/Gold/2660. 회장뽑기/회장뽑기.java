import java.util.*;
import java.io.*;

public class Main {

//	static List<Integer>[] graph;
	static int[][] distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 회원의 수
		int N = Integer.parseInt(br.readLine());

//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < N; c++) {
//				if (r != c) {
//					graph[r][c] = -1;
//				}
//			}
//		}

//		graph = new ArrayList[N];
//		for (int i = 0; i < N; i++) {
//			graph[i] = new ArrayList<>();
//		}

		distance = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (r != c) {
					distance[r][c] = Integer.MAX_VALUE;
				}
			}
		}

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			if (a == -2 && b == -2)
				break;
			distance[a][b] = 1;
			distance[b][a] = 1;
		}

		for (int k = 0; k < N; k++) {
			for (int a = 0; a < N; a++) {
				for (int b = 0; b < N; b++) {
					if (a == b || b == k || k == a)
						continue;
					if (distance[a][k] != Integer.MAX_VALUE && distance[k][b] != Integer.MAX_VALUE) {
						distance[a][b] = Math.min(distance[a][b], distance[a][k] + distance[k][b]);
					}
				}
			}
		}
		
		int minMax = Integer.MAX_VALUE;
		List<Integer> president = new ArrayList<>();
		
		for (int r=0; r<N; r++) {
			int maxScore = 0;
			for (int c=0; c<N; c++) {
				maxScore = Math.max(maxScore, distance[r][c]);
			}
			if (minMax==maxScore) president.add(r);
			else if (minMax>maxScore) {
				president.clear();
				president.add(r);
			}
			minMax = Math.min(maxScore, minMax);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(minMax+" "+president.size()+"\n");
		for (Integer i: president) {
			sb.append((i+1)+" ");
		}
		
		System.out.println(sb);
	}
}