import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 굴다리 길이
        int M = Integer.parseInt(br.readLine()); // 가로등 개수
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] lamps = new int[M];
        for (int i = 0; i < M; i++) {
            lamps[i] = Integer.parseInt(st.nextToken());
        }

        // 이분 탐색 범위 설정
        int left = 1;
        int right = N;
        int answer = N;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canLightAll(N, lamps, mid)) {
                answer = mid;
                right = mid - 1;  // 더 작은 높이가 가능한지 확인
            } else {
                left = mid + 1;   // 현재 높이로는 부족하므로 증가
            }
        }

        System.out.println(answer);
    }

    // 높이 h로 모든 길을 밝힐 수 있는지 체크
    static boolean canLightAll(int N, int[] lamps, int h) {
        int lastLit = 0; // 밝힌 마지막 위치

        for (int lamp : lamps) {
            if (lamp - h > lastLit) {
                return false; // 어두운 구간 발생
            }
            lastLit = lamp + h;
        }

        return lastLit >= N; // 끝까지 밝힐 수 있어야 함
    }
}