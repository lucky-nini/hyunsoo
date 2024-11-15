import java.util.*;
class Solution {
    static int maxScoreDiff = 0; // 최대 점수 차이
    static int[] result = new int[11]; // 가능한 결과
    static int finalArrow; // 남아있는 화살 수
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        
        int peachScore = 0;
        int lionScore = 0;

        finalArrow = n; 
        int[] shot = new int[11];
        
        shoot(0, peachScore, lionScore, finalArrow, shot, info); // 비교할 점수대 인덱스, 어피치 점수, 라이온 점수, 남아있는 화살 수, 점수대별 쏜 화살 개수, 어피치가 쏜 화살
        if (finalArrow == n) {
            return new int[]{-1};
        }    
        if (finalArrow>0) {
            result[10] +=finalArrow;
        }
        
        return result;
    }
    
    public void shoot(int idx, int peach, int lion, int leftArrow, int[] shot, int[] info) {
        if (idx == 11) {
            if (lion<=peach) return;
            if (lion - peach > maxScoreDiff) {
                maxScoreDiff = lion - peach;
                for (int i=0; i<11; i++) {
                    result[i] = shot[i];
                }
                finalArrow = leftArrow;
            } else if (lion-peach == maxScoreDiff) {
                if (changeArray(result, shot)) {
                    for (int i=0; i<11; i++) {
                        result[i] = shot[i];
                    }
                    finalArrow = leftArrow;
                }
            }
            return;
        }
        
        // 그냥...! 둘다 0 하자
        if (info[idx]==0) {
            shoot(idx+1, peach, lion, leftArrow, shot, info);
        }
        // 라이온이 점수 가져갈꺼야
        if (leftArrow >= (info[idx] + 1)) {
            shot[idx] = info[idx]+1;
            leftArrow -= shot[idx];
            shoot(idx+1, peach, lion+(10-idx), leftArrow, shot, info);
            leftArrow += shot[idx];
            shot[idx] = 0;
        }
        // 안쏴! 어피치가 점수 가져갈꺼야
        shoot(idx+1, peach+(10-idx), lion, leftArrow, shot, info);
    }
    
    public boolean changeArray(int[] originArr, int[] compareArr) {
        for (int i=10; i>=0; i--) {
            if (originArr[i] > compareArr[i]) return false;
            else if (originArr[i] < compareArr[i]) return true;
        }
        return false;
    }
}