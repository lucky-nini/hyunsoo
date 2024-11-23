import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] synergy;
	static int minDiff = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		synergy = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				synergy[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// 일단 0~N중에 N/2를 고르기~
		boolean[] startTeam = new boolean[N];
		chooseStartTeam(0, 0, startTeam);

		System.out.println(minDiff);
	}

	public static void chooseStartTeam(int idx, int cnt, boolean[] startTeam) {
		if (cnt == N / 2) {
			calculate(startTeam);
			return;
		}
		if (idx == N)
			return;

		startTeam[idx] = true;
		chooseStartTeam(idx + 1, cnt + 1, startTeam);
		startTeam[idx] = false;
		chooseStartTeam(idx + 1, cnt, startTeam);
	}

	public static void calculate(boolean[] startTeam) {
		// start팀 계산
		int start = 0;
		for (int i = 0; i < N - 1; i++) {
			if (startTeam[i]) {
				for (int j = i + 1; j < N; j++) {
					if (startTeam[j]) {
						start += synergy[i][j];
						start += synergy[j][i];
					}
				}
			}
		}
		// link팀 계산
		int link = 0;
		for (int i = 0; i < N - 1; i++) {
			if (!startTeam[i]) {
				for (int j = i + 1; j < N; j++) {
					if (!startTeam[j]) {
						link += synergy[i][j];
						link += synergy[j][i];
					}
				}
			}
		}
		minDiff = Math.min(minDiff, Math.abs(start - link));
	}
}