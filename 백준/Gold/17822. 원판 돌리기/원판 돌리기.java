import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static boolean flag = false;

    public static class Plate {
        int[] numbers;

        Plate(int len, int[] numbers) {
            this.numbers = new int[len];
            for (int i=0; i<len; i++) {
            	this.numbers[i] = numbers[i];
            }
        }

        void rotateClock(int k) {
            int[] temp = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                temp[(i + k) % numbers.length] = numbers[i];
            }
            numbers = temp;
        }

        void rotateCounterClock(int k) {
            rotateClock(numbers.length - k); 
        }

        @Override
        public String toString() {
            return Arrays.toString(this.numbers);
        }
    }

    static int N, M, T;
    static Plate[] plates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        plates = new Plate[N];

        for (int p = 0; p < N; p++) {
            int[] plateNumber = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                plateNumber[i] = Integer.parseInt(st.nextToken());
            }
            plates[p] = new Plate(M, plateNumber);
        }

        for (int turn = 0; turn < T; turn++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for (int p = 0; p < N; p++) {
                if ((p + 1) % x == 0) {
                    if (d == 0) plates[p].rotateClock(k);
                    else plates[p].rotateCounterClock(k);
                }
            }

            flag = false;
            boolean[][] visited = new boolean[N][M];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (!visited[r][c] && plates[r].numbers[c] != 0) {
                        dfs(r, c, visited, plates[r].numbers[c]);
                    }
                }
            }

            if (!flag) {
                adjustByAverage();
            }
        }
        int finalSum = 0;
        for (int p = 0; p < N; p++) {
            for (int c = 0; c < M; c++) {
                finalSum += plates[p].numbers[c];
            }
        }
        System.out.println(finalSum);
    }

    public static void dfs(int r, int c, boolean[][] visited, int val) {
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = (c + dc[d] + M) % M;
            if (nr >= 0 && nr < N && !visited[nr][nc] && plates[nr].numbers[nc] == val) {
                visited[nr][nc] = true;
                plates[nr].numbers[nc] = 0; 
                plates[r].numbers[c] = 0; 
                dfs(nr, nc, visited, val);
                flag = true;
            }
        }
    }

    public static void adjustByAverage() {
        int cnt = 0;
        int sum = 0;

        for (int p = 0; p < N; p++) {
            for (int c = 0; c < M; c++) {
                if (plates[p].numbers[c] != 0) {
                    cnt++;
                    sum += plates[p].numbers[c];
                }
            }
        }

        if (cnt == 0) return; // 모든 숫자가 0인 경우 처리

        double avg = (double) sum / cnt; 
        for (int p = 0; p < N; p++) {
            for (int c = 0; c < M; c++) {
                if (plates[p].numbers[c] != 0) {
                    if (plates[p].numbers[c] > avg) plates[p].numbers[c]--;
                    else if (plates[p].numbers[c] < avg) plates[p].numbers[c]++;
                }
            }
        }
    }
}