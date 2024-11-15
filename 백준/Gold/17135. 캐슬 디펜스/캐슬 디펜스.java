import java.io.*;
import java.util.*;

public class Main {
	static int N, M, D;
	static int[][] map;
	static int maxAttack = 0;

	static int[] dr = { 0, -1, 0 }; // 좌 상 우
	static int[] dc = { -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		boolean[] chosenM = new boolean[M];
		chooseM(chosenM, 0, 0);
		System.out.println(maxAttack);
	}

	public static void chooseM(boolean[] chosen, int idx, int cnt) {
		if (cnt == 3) {
			int[][] nMap = new int[N][M];
			for (int r = 0; r < N; r++) {
				System.arraycopy(map[r], 0, nMap[r], 0, M);
			}
			attack(chosen, N, 0, nMap);
			return;
		}
		if (idx == M)
			return;

		chosen[idx] = true;
		chooseM(chosen, idx + 1, cnt + 1);
		chosen[idx] = false;
		chooseM(chosen, idx + 1, cnt);
	}

	public static void attack(boolean[] chosen, int rPos, int cnt, int[][] nMap) {
		if (rPos == 0) {
			maxAttack = Math.max(maxAttack, cnt);
			return;
		}

		Set<String> attacked = new HashSet<>();
		for (int c = 0; c < M; c++) {
			if (chosen[c]) {
				boolean[][] visited = new boolean[N][M];
				Queue<int[]> queue = new LinkedList<>();
				queue.add(new int[] { rPos - 1, c, 1 });

				while (!queue.isEmpty()) {
					int[] top = queue.poll();
					int tr = top[0], tc = top[1], dist = top[2];
					if (dist > D)
						break;

					if (tr >= 0 && tr < N && nMap[tr][tc] == 1) {
						attacked.add(tr + " " + tc);
						break;
					}

					for (int d = 0; d < 3; d++) {
						int nr = tr + dr[d];
						int nc = tc + dc[d];
						if (nr >= 0 && nr < rPos && nc >= 0 && nc < M && !visited[nr][nc]) {
							queue.add(new int[] { nr, nc, dist + 1 });
							visited[nr][nc] = true;
						}
					}
				}
			}
		}
		int[][] nextMap = new int[N][M];
		for (int r = 0; r < N; r++) {
			System.arraycopy(nMap[r], 0, nextMap[r], 0, M);
		}

		for (String pos : attacked) {
			int r = Integer.parseInt(pos.split(" ")[0]);
			int c = Integer.parseInt(pos.split(" ")[1]);
			nextMap[r][c] = 0;
		}

		attack(chosen, rPos - 1, cnt + attacked.size(), nextMap);
	}
}