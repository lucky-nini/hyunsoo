import java.io.*;
import java.util.*;

public class Main {
  static int[][] board;
  static int[][] emptyArr;
  static boolean keepGoing = true;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    board = new int[9][9];
    List<int[]> empty = new ArrayList<>();
    
    for (int r=0; r<9; r++) {
      String[] strArr = br.readLine().split("");
      for (int c=0; c<9; c++) {
        board[r][c] = Integer.parseInt(strArr[c]);
        if (board[r][c]==0) {
          empty.add(new int[]{r, c});
        }
      }
    }

    emptyArr = empty.toArray(new int[empty.size()][2]);

    fillBoard(0);
    
  }

  public static void fillBoard(int idx) {
    if (idx==emptyArr.length) {
      StringBuilder sb = new StringBuilder();
      for (int r=0; r<9; r++) {
        for (int c=0; c<9; c++) {
          sb.append(board[r][c]);
        }
        sb.append("\n");
      }
      System.out.println(sb);
      keepGoing = false;
    }
    if (!keepGoing) return;
    int curR = emptyArr[idx][0];
    int curC = emptyArr[idx][1];
    boolean[] canFill = checkCanFill(curR, curC);
    for (int i=1; i<10; i++) {
      if (canFill[i] && keepGoing) {
        board[curR][curC] = i;
        fillBoard(idx+1);
      }
    }
    board[curR][curC] = 0;
  }

  public static boolean[] checkCanFill(int curR, int curC) {
    boolean[] canFill = new boolean[10];
    Arrays.fill(canFill, true);

    // r에 있는 수 제외
    for (int c=0; c<9; c++) {
      if (board[curR][c]!=0 && c!=curC) canFill[board[curR][c]] = false;
    }

    // c에 있는 수 제외
    for (int r=0; r<9; r++) {
      if (board[r][curC]!=0 && r!=curR) canFill[board[r][curC]] = false;
    }

    // 작은 네모
    int squareR = curR/3;
    int squareC = curC/3;

    for (int r=3*squareR; r<3*squareR+3; r++) {
      for (int c=3*squareC; c<3*squareC+3; c++) {
        if (board[r][c]!=0 && !(r==curR && c==curC)) canFill[board[r][c]] = false;
      }
    }

    return canFill;
  }
}