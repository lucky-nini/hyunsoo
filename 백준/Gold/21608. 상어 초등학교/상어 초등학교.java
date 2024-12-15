import java.util.*;
import java.io.*;

public class Main {
	static class Seat implements Comparable<Seat> {

		int r, c, likeCnt, emptyCnt;

		Seat(int r, int c, int likeCnt, int emptyCnt) {
			this.r = r;
			this.c = c;
			this.likeCnt = likeCnt;
			this.emptyCnt = emptyCnt;
		}

		@Override
		public int compareTo(Seat seat) {
			if (this.likeCnt != seat.likeCnt)
				return seat.likeCnt - this.likeCnt;

			if (this.emptyCnt != seat.emptyCnt)
				return seat.emptyCnt - this.emptyCnt;

			if (this.r != seat.r)
				return this.r - seat.r;

			return this.c - seat.c;
		}
	}

	static int N;
	static Map<Integer, Set<Integer>> like;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		like = new HashMap<>(); // 각 학생이 좋아하는 번호 정보
		int[] students = new int[N * N]; // 학생 주어진 순서
		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			students[i] = Integer.parseInt(st.nextToken());
			like.put(students[i], new HashSet<>());
			for (int l = 0; l < 4; l++) {
				like.get(students[i]).add(Integer.parseInt(st.nextToken()));
			}
		}

		map = new int[N][N]; // 자리 배치도에 어떤 학생 앉아있는지
		for (int s = 0; s < N * N; s++) {
			int student = students[s];
			Seat seat = null;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] > 0)
						continue;
					// r, c에 해당하는 likeCnt, emptyCnt 찾기
					getLikeCnt(r, c, student);
					Seat cur = new Seat(r, c, getLikeCnt(r, c, student), getEmptyCnt(r, c));
					if (seat == null) {
						seat = cur;
						continue;
					}
					if (seat.compareTo(cur) > 0) {
						seat = cur;
					}
				}
			}
			map[seat.r][seat.c] = student;
		}

		// 점수 계산
		int score = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int studentScore = getScore(r, c);
				score += studentScore == 0 ? 0 : Math.pow(10, studentScore - 1);
			}
		}

		System.out.println(score);

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static int getLikeCnt(int r, int c, int idx) {
		int likeCnt = 0;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (like.get(idx).contains(map[nr][nc])) {
					likeCnt++;
				}
			}
		}

		return likeCnt;
	}

	public static int getEmptyCnt(int r, int c) {
		int emptyCnt = 0;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (map[nr][nc] == 0)
					emptyCnt++;
			}
		}

		return emptyCnt;
	}

	public static int getScore(int r, int c) {
		int student = map[r][c];
		int score = 0;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (like.get(student).contains(map[nr][nc])) {
					score++;
				}
			}
		}

		return score;
	}
}