import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    static class Coor {
        int r, c;

        Coor(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 격자 크기
        int M = Integer.parseInt(st.nextToken()); // 이동 횟수
        int[][] A = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dir = new int[M][2];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            dir[m][0] = Integer.parseInt(st.nextToken()) - 1;
            dir[m][1] = Integer.parseInt(st.nextToken());
        }

        List<Coor> cloudList = new ArrayList<>();
        cloudList.add(new Coor(N - 1, 0));
        cloudList.add(new Coor(N - 1, 1));
        cloudList.add(new Coor(N - 2, 0));
        cloudList.add(new Coor(N - 2, 1));

        for (int move = 0; move < M; move++) {
            // 1. 구름 이동
            List<Coor> newClouds = new ArrayList<>();
            for (Coor cloud : cloudList) {
                int nr = (cloud.r + dir[move][1] * dr[dir[move][0]] + N * 50) % N;
                int nc = (cloud.c + dir[move][1] * dc[dir[move][0]] + N * 50) % N;
                newClouds.add(new Coor(nr, nc));
                A[nr][nc]++; // 2. 비 내리기
            }
            cloudList.clear(); // 3. 구름 사라짐

            // 4. 물복사 버그
            int[] diagR = {-1, -1, 1, 1};
            int[] diagC = {-1, 1, -1, 1};
            for (Coor cloud : newClouds) {
                int copy = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = cloud.r + diagR[d];
                    int nc = cloud.c + diagC[d];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N && A[nr][nc] > 0) {
                        copy++;
                    }
                }
                A[cloud.r][cloud.c] += copy;
            }

            // 5. 새 구름 생성
            boolean[][] visited = new boolean[N][N];
            for (Coor cloud : newClouds) {
                visited[cloud.r][cloud.c] = true;
            }
            
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (A[r][c] >= 2 && !visited[r][c]) {
                        cloudList.add(new Coor(r, c));
                        A[r][c] -= 2;
                    }
                }
            }
        }

        // 물 계산
        int result = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                result += A[r][c];
            }
        }
        System.out.println(result);
    }
}