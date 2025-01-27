import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[][] map = new int[H][W];
		st = new StringTokenizer(br.readLine());
		for (int c = 0; c < W; c++) {
			int h = Integer.parseInt(st.nextToken());
			for (int r = 0; r < h; r++) {
				map[r][c] = 1;
			}
		}

		int cnt = 0;
		for (int r = 0; r < H; r++) {
			int sc = -1;
			int rCnt = 0;
			for (int c = 0; c < W; c++) {
				if (map[r][c] == 1 && sc == -1) {
					sc = c;
				} else if (map[r][c] == 1) {
					cnt += rCnt;
					sc = c;
					rCnt = 0;
				} else if (sc != -1) {
					rCnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}
}