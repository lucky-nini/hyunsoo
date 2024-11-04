import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        int[] filter1 = {-1, -1};
        int[] filter2 = {-1, -1};

        // 공기청정기 위치 초기화
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == -1) {
                    if (filter1[0] == -1) {
                        filter1[0] = r;
                        filter1[1] = c;
                    } else {
                        filter2[0] = r;
                        filter2[1] = c;
                    }
                }
            }
        }

        while (T-- > 0) {
            // 먼지 확산
            int[][] change = new int[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (map[r][c] > 0) {
                        int spreadAmount = map[r][c] / 5;
                        int spreadCount = 0;
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != -1) {
                                change[nr][nc] += spreadAmount;
                                spreadCount++;
                            }
                        }
                        change[r][c] -= spreadAmount * spreadCount;
                    }
                }
            }

            // 확산된 먼지 반영
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    map[r][c] += change[r][c];
                }
            }

            // 공기청정기 위쪽 순환
            for (int r = filter1[0] - 1; r > 0; r--) map[r][0] = map[r - 1][0];
            for (int c = 0; c < C - 1; c++) map[0][c] = map[0][c + 1];
            for (int r = 0; r < filter1[0]; r++) map[r][C - 1] = map[r + 1][C - 1];
            for (int c = C - 1; c > 1; c--) map[filter1[0]][c] = map[filter1[0]][c - 1];
            map[filter1[0]][1] = 0;

            // 공기청정기 아래쪽 순환
            for (int r = filter2[0] + 1; r < R - 1; r++) map[r][0] = map[r + 1][0];
            for (int c = 0; c < C - 1; c++) map[R - 1][c] = map[R - 1][c + 1];
            for (int r = R - 1; r > filter2[0]; r--) map[r][C - 1] = map[r - 1][C - 1];
            for (int c = C - 1; c > 1; c--) map[filter2[0]][c] = map[filter2[0]][c - 1];
            map[filter2[0]][1] = 0;
        }

        // 남은 미세먼지 합산
        int cnt = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] > 0)
                    cnt += map[r][c];
            }
        }
        System.out.println(cnt);
    }
}