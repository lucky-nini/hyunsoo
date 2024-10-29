import java.io.*;
import java.util.*;

public class Main {
	static int N, L;
	static int[][] map;

	static class Judge {
		int prev, cur;
		int v = 0;
		int vc = 1;

		Judge(int val) {
			this.cur = val;
			this.v = val;
		}

		boolean newVal(int val) {
			this.prev = this.cur;
			this.cur = val;

			if (this.v == this.prev && this.prev == this.cur) {
				this.vc += 1;
				return true;
			}
			if (this.prev != this.cur) {
				if (this.prev == this.cur + 1) {
					if (this.v == this.prev) {
						this.vc = 1;
						putRamp();
						return true;
					}
					return false;
				} else if (this.prev == this.cur - 1) {
					if (this.vc >= L) {
						this.vc = 1;
						this.v = this.cur;
						return true;
					} else
						return false;
				}
				return false;
			}
			if (this.v != this.prev && this.prev == this.cur) {
				this.vc += 1;
				putRamp();
				return true;
			}
			return false;
		}

		void putRamp() {
			if (this.vc >= L) {
				this.vc = 0;
				this.v = this.cur;
			}
		}

		boolean canPass() {
			if (this.v != this.cur && this.vc < L)
				return false;
			return true;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int totCnt = 0;

		outer: for (int r = 0; r < N; r++) {
			Judge j = new Judge(map[r][0]);
			for (int c = 1; c < N; c++) {
				boolean flag = j.newVal(map[r][c]);
				if (!flag)
					continue outer;
			}
			if (j.canPass()) {
				totCnt += 1;
			}
		}

		outer: for (int c = 0; c < N; c++) {
			Judge j = new Judge(map[0][c]);
			for (int r = 1; r < N; r++) {
				boolean flag = j.newVal(map[r][c]);
				if (!flag)
					continue outer;
			}
			if (j.canPass()) {
				totCnt += 1;
			}
		}

		System.out.println(totCnt);
	}
}