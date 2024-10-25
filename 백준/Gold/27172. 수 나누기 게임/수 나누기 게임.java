import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] card = new int[N];
    String[] strArr = br.readLine().split(" ");

    int maxVal = 0;
    for (int c=0; c<N; c++) {
      card[c] = Integer.parseInt(strArr[c]);
      maxVal = Math.max(card[c], maxVal);
    }

    int[] check = new int[maxVal+1];
    for (int c=0; c<N; c++) {
      check[card[c]] = c+1;
    }

    int[] score = new int[N];

    for (int i=0; i<N; i++) {
      int val = card[i];
      int m = 2;
      while (val*m<=maxVal) {
        if (check[val*m]!=0) {
          score[i]+=1;
          score[check[val*m]-1]-=1;
        }
        m++;
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i=0; i<N; i++) {
      sb.append(score[i]+" ");
    }

    System.out.println(sb);
  }
}