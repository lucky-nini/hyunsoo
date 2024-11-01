import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    
    static int[] dr = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dc = {0, 1, 0, -1};
    
    static int totCnt = 0;
    
    public static class Robot {
        int r, c, dir;
        Robot(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
        
        void moveBack() {
            this.r -= dr[dir];
            this.c -= dc[dir];
        }
        
        void rotate() {
            this.dir = (this.dir + 3) % 4;
        }
        
        void moveForward() {
            this.r += dr[dir];
            this.c += dc[dir];
        }
    }
        
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        Robot ro = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        
        map = new int[N][M];
        
        for (int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0; c<M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        visited = new boolean[N][M];
        
        
        moveRobot(ro);
        
        System.out.println(totCnt);
    }

    public static void moveRobot(Robot ro) {
        if (map[ro.r][ro.c]==0 && !visited[ro.r][ro.c]) {
            visited[ro.r][ro.c] = true;
            totCnt++;
        }
        
        for (int i=0; i<4; i++) {
            ro.rotate();
            int nr = ro.r + dr[ro.dir];
            int nc = ro.c + dc[ro.dir];
            if (nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc]==0 && !visited[nr][nc]) {
                ro.moveForward();
                moveRobot(ro);
                return;
            }
        }
        
        int nr = ro.r - dr[ro.dir];
        int nc = ro.c - dc[ro.dir];
        if (nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc]==0) {
            ro.moveBack();
            moveRobot(ro);
            return;
        } else {
            return;
        }
    }
}
