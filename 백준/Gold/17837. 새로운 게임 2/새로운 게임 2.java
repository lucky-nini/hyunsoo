import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[][] board;

	static int[] dr = { 0, 0, 0, -1, 1 };
	static int[] dc = { 0, 1, -1, 0, 0 };

	static class Horse {
		int r, c, dir;

		Horse(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken()); // 0: 흰색, 1: 빨간색, 2: 파란색
			}
		}

		Deque<Integer>[][] chess = new ArrayDeque[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				chess[r][c] = new ArrayDeque<>();
			}
		}

		Horse[] horses = new Horse[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			horses[i] = new Horse(r, c, dir);
			chess[r][c].add(i);
		}

		int turn = 1;
		outer: while (turn <= 1000) {
			for (int horseIdx = 0; horseIdx < K; horseIdx++) {
				Horse curHorse = horses[horseIdx];

				int pr = curHorse.r;
				int pc = curHorse.c;
				int dir = curHorse.dir;

				int nr = pr + dr[dir];
				int nc = pc + dc[dir];

				// 파란 또는 벗어나는 경우
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 2) {
					switch (dir) {
					case 1:
						dir = 2;
						break;
					case 2:
						dir = 1;
						break;
					case 3:
						dir = 4;
						break;
					case 4:
						dir = 3;
						break;
					}
					horses[horseIdx].dir = dir;
					nr = pr + dr[dir];
					nc = pc + dc[dir];
				}

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] != 2) {
					if (board[nr][nc] == 1) {
						while (!chess[pr][pc].isEmpty()) {
							int idx = chess[pr][pc].pollLast();
							chess[nr][nc].add(idx);
							horses[idx].r = nr;
							horses[idx].c = nc;
							if (idx == horseIdx)
								break;
						}
					} else if (board[nr][nc] == 0) {
						Stack<Integer> stack = new Stack<>();
						while (!chess[pr][pc].isEmpty()) {
							int idx = chess[pr][pc].pollLast();
							stack.push(idx);
							horses[idx].r = nr;
							horses[idx].c = nc;
							if (idx==horseIdx) break;
						}
						while (!stack.isEmpty()) {
							chess[nr][nc].add(stack.pop());
						}
					}
					if (chess[nr][nc].size() >= 4) {
						break outer;
					}
				}
			}

			turn++;
		}

		System.out.println(turn >= 1000 ? -1 : turn);
	}
}