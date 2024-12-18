import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			int[] start = new int[2];
			int[] end = new int[2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(br.readLine());

			int count = 0;
			
			for (int n=0; n<N; n++) {
				st = new StringTokenizer(br.readLine());
				int cx = Integer.parseInt(st.nextToken());
				int cy = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				// 시작점이 원 내부에 있는지
                boolean startInside = Math.pow(start[0] - cx, 2) + Math.pow(start[1] - cy, 2) < r * r;
                // 끝점이 원 내부에 있는지
                boolean endInside = Math.pow(end[0] - cx, 2) + Math.pow(end[1] - cy, 2) < r * r;

                // 한 점만 원 내부에 있는 경우 카운트 증가
                if (startInside != endInside) {
                    count++;
                }
			}

			sb.append(count).append("\n");
		}
		
		System.out.println(sb);
	}
}