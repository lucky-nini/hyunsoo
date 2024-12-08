class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int len = 1;
        while (true) {
            int min = 0;
            for (int i=0; i<len; i++) {
                min+=i+1;
            }
            if (n<min) break;

            int k = (n-len*(len-1)/2)/len;
            if (((n-(len*(len-1)/2)) % len)==0 && k>=1) {
                answer++;
            }
            len++;
        }
        
        return answer;
    }
}